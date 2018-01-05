package net.dubbo.spring.boot;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.config.spring.ReferenceBean;
import net.dubbo.spring.boot.annotation.DubboConsumer;
import net.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import net.dubbo.spring.boot.domain.ClassIdBean;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lishulong
 * @version 1.0
 * @email lishulong.never@gmail.com
 * @date 2017/12/29
 */
@Configuration
@ConditionalOnMissingBean(Service.class)
@ConditionalOnBean(annotation = EnableDubboConfiguration.class)
@AutoConfigureAfter(DubboAutoConfiguration.class)
@EnableConfigurationProperties(DubboProperties.class)
public class DubboConsumerAutoCongiguration {

    public static final Map<ClassIdBean, Object> Dubbo_references_map = new ConcurrentHashMap<ClassIdBean, Object>();

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private DubboProperties properties;

    @Autowired
    private ApplicationConfig applicationConfig;

    @Autowired
    private RegistryConfig registryConfig;

    @Bean
    public BeanPostProcessor beanPostProcessor() {

        return new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                Class<?> aClass = bean.getClass();

                if (AopUtils.isAopProxy(bean))
                    aClass = AopUtils.getTargetClass(bean);
                try {
                    for (Field field : aClass.getDeclaredFields()) {
                        DubboConsumer dubboConsumer = field.getAnnotation(DubboConsumer.class);
                        if (null != dubboConsumer) {
                            Class<?> type = field.getType();
                            ReferenceBean<?> consumerBean = DubboConsumerAutoCongiguration.this.getConsumerBean(type, dubboConsumer);
                            String group = consumerBean.getGroup();
                            String version = consumerBean.getVersion();
                            ClassIdBean classIdBean = new ClassIdBean(type, group, version);

                            Object o = DubboConsumerAutoCongiguration.Dubbo_references_map.get(classIdBean);

                            if (o == null) {
                                synchronized (this) {
                                    Object o1 = DubboConsumerAutoCongiguration.Dubbo_references_map.get(classIdBean);
                                    if (o1 == null) {
                                        consumerBean.setApplicationContext(DubboConsumerAutoCongiguration.this.applicationContext);
                                        consumerBean.setApplication(DubboConsumerAutoCongiguration.this.applicationConfig);
                                        RegistryConfig registry = consumerBean.getRegistry();

                                        if(registry==null){
                                            consumerBean.setRegistry(DubboConsumerAutoCongiguration.this.registryConfig);
                                        }

                                        consumerBean.setApplication(DubboConsumerAutoCongiguration.this.applicationConfig);

                                        consumerBean.afterPropertiesSet();
                                        o = consumerBean.getObject();

                                        DubboConsumerAutoCongiguration.Dubbo_references_map.put(classIdBean,o);

                                    }
                                }
                            }

                            field.setAccessible(true);
                            field.set(bean,o);
                            field.setAccessible(false);
                        }

                    }
                }catch (Exception e){
                    throw new BeanCreationException(beanName,e);
                }

                return bean;
            }

            @Override
            public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
                return null;
            }
        };
    }


    private <T> ReferenceBean<T> getConsumerBean(Class<T> interfaceClazz, DubboConsumer dubboConsumer) {
        ReferenceBean<T> consumerBean = new ReferenceBean<>();

        consumerBean.setInterface(interfaceClazz);

        String canonicalName = interfaceClazz.getCanonicalName();

        consumerBean.setId(canonicalName);

        String registry = dubboConsumer.registry();

        if (registry != null && registry.length() > 0) {
            RegistryConfig registryConfig = new RegistryConfig();
            registryConfig.setAddress(registry);
            consumerBean.setRegistry(registryConfig);
        }

        String group = dubboConsumer.group();
        consumerBean.setGroup(StringUtils.hasText(group) ? group : this.properties.getGroup());

        String version = dubboConsumer.version();
        consumerBean.setVersion(StringUtils.hasText(version) ? version : this.properties.getVersion());

        consumerBean.setTimeout(dubboConsumer.timeout());
        consumerBean.setClient(dubboConsumer.client());
        consumerBean.setUrl(dubboConsumer.url());
        consumerBean.setCheck(dubboConsumer.check());
        consumerBean.setLazy(dubboConsumer.lazy());
        consumerBean.setRetries(dubboConsumer.retries());

        consumerBean.setActives(dubboConsumer.actives());
        consumerBean.setLoadbalance(dubboConsumer.loadbalance());
        consumerBean.setAsync(dubboConsumer.async());
        consumerBean.setSent(dubboConsumer.sent());

        return consumerBean;


    }


}
