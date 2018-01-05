package net.dubbo.spring.boot;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import net.dubbo.spring.boot.endpoint.DubboEndpoint;
import net.dubbo.spring.boot.endpoint.DubboOperationEendPoint;
import net.dubbo.spring.boot.health.DubboHealthIndicator;
import net.dubbo.spring.boot.metrics.DubboMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/29
 * @doing
 */
@Configuration
@EnableConfigurationProperties(DubboProperties.class)
public class DubboAutoConfiguration {

    @Autowired
    private DubboProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public ApplicationConfig dubboApplicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(this.properties.getAppname());
        return applicationConfig;
    }

    @Bean
    @ConditionalOnMissingBean
    public RegistryConfig dubboRegistryCongig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(this.properties.getRegister());
        return registryConfig;
    }

    @Bean
    @ConditionalOnMissingBean
    public ProtocolConfig dubboProtocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName(this.properties.getProtocol());
        protocolConfig.setPort(this.properties.getPort());
        protocolConfig.setThreads(this.properties.getThreads());
        return protocolConfig;
    }

    @Bean
    public DubboHealthIndicator dubboHealthIndicator() {
        return new DubboHealthIndicator();
    }

    @Bean
    public DubboEndpoint dubboEndpoint() {
        return new DubboEndpoint();
    }

    @Bean
    public DubboMetrics dubboMetrics() {
        return new DubboMetrics();
    }

    @Bean
    public DubboOperationEendPoint dubboOperationEendPoint() {
        return new DubboOperationEendPoint();
    }


}
