package net.dubbo.spring.boot.metrics;

import net.dubbo.spring.boot.domain.ClassIdBean;
import net.dubbo.spring.boot.listener.StaticsFilterHelp;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author lishulong
 * @version 1.0
 * @email lishulong.never@gmail.com
 * @date 2018/1/3
 */
public class DubboMetrics implements PublicMetrics {


    @Override
    public Collection<Metric<?>> metrics() {
        LinkedList<Metric<?>> metrics = new LinkedList<Metric<?>>();

        StaticsFilterHelp.STATICS_DATA_MAP.entrySet().forEach(bean -> {
            ClassIdBean classIdBean = bean.getKey();
            bean.getValue().forEach((k, v) -> metrics.add(new Metric<Number>("dubbo." + classIdBean + "." + k, v.get())));
        });
        return metrics;
    }
}
