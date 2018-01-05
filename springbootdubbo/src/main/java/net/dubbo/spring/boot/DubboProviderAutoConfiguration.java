package net.dubbo.spring.boot;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.config.spring.ServiceBean;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import net.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author lishulong
 * @version 1.0
 * @email lishulong.never@gmail.com
 * @date 2017/12/29
 */
@Configuration
@ConditionalOnClass(Service.class)
@ConditionalOnBean(annotation = EnableDubboConfiguration.class)
@AutoConfigureAfter(DubboAutoConfiguration.class)
@EnableConfigurationProperties(DubboProperties.class)
public class DubboProviderAutoConfiguration {

    @Autowired
    private DubboProperties properties;

    @Autowired
    private ApplicationContext applicationContext;

    private ProtocolConfig protocolConfig;

    private RegistryConfig registryConfig;

    @PostConstruct
    public void init(){
        Map<String, Object> beansWithAnnotation = this.applicationContext.getBeansWithAnnotation(Service.class);
        beansWithAnnotation.forEach((k, v) -> {
            try {
                this.initProviderBean(k, v);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void initProviderBean(String beanName, Object bean) throws Exception {

        Service service = this.applicationContext.findAnnotationOnBean(beanName, Service.class);

        ServiceBean<Object> serviceConfig = new ServiceBean<>(service);

        if (void.class.equals(service.interfaceClass()) && "".equals(service.interfaceName())) {

            if (bean.getClass().getInterfaces().length > 0)
                serviceConfig.setInterface(bean.getClass().getInterfaces()[0]);
            else
                throw new IllegalStateException("failed to export remote service class" + bean.getClass().getName()
                        + ",cause : the @service undefiend interfaceclsss or interface name ,and service class unimplemented any interface");
        }

        String version = service.version();

        version = StringUtils.hasText(version) ? version : this.properties.getVersion();

        serviceConfig.setVersion(version);

        String group = service.group();

        group = StringUtils.hasText(group) ? group: this.properties.getGroup();

        serviceConfig.setGroup(group);

        serviceConfig.setApplicationContext(this.applicationContext);

        serviceConfig.setProtocol(this.protocolConfig);

        serviceConfig.setRegister(this.registryConfig.isRegister());

        serviceConfig.afterPropertiesSet();

        serviceConfig.setRef(bean);

        serviceConfig.export();


    }


}
