package net.dubbo.spring.boot.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.dubbo.spring.boot.DubboProperties;
import net.dubbo.spring.boot.metrics.DubboMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/29
 * @doing
 */
@Component
public class DubboEndpoint extends AbstractEndpoint<Map<String,Object>> {


    @Autowired
    private DubboProperties dubboProperties;

    @Autowired
    private DubboMetrics dubboMetrics;

    public DubboEndpoint(String id) {
        super(id);
    }

    public DubboEndpoint(String id, boolean sensitive) {
        super(id, sensitive);
    }

    public DubboEndpoint(String id, boolean sensitive, boolean enabled) {
        super(id, sensitive, enabled);
    }

    @Override
    public Map<String, Object> invoke() {

        HashMap<String, Object> result = new HashMap<>();

        result.put("endpoint",this.buildEndpoint());

        return null;
    }

    private Object buildEndpoint() {

    }
}
