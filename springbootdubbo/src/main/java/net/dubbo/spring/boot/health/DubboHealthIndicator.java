package net.dubbo.spring.boot.health;

import net.dubbo.spring.boot.DubboConsumerAutoCongiguration;
import net.dubbo.spring.boot.annotation.DubboConsumer;
import net.dubbo.spring.boot.domain.ClassIdBean;
import net.dubbo.spring.boot.listener.ConsummerSubscribeListener;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;

/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/29
 * @doing
 */
public class DubboHealthIndicator extends AbstractHealthIndicator{

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        for(ClassIdBean classIdBean: ConsummerSubscribeListener.SUBSCRIBEINTERFACE_SET){
//            DubboConsumerAutoCongiguration
        }
    }
}
