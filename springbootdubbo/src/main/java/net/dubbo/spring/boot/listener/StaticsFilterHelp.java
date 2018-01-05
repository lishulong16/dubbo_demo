package net.dubbo.spring.boot.listener;

import com.alibaba.dubbo.rpc.Filter;
import net.dubbo.spring.boot.domain.ClassIdBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/29
 * @doing
 */
public abstract class StaticsFilterHelp implements Filter {

    public static final Map<ClassIdBean, Map<String, AtomicLong>> STATICS_DATA_MAP = new ConcurrentHashMap<ClassIdBean, Map<String, AtomicLong>>();

    public static void increase(ClassIdBean classIdBean, String methodName) {
        Map<String, AtomicLong> methodCountMap = STATICS_DATA_MAP.get(classIdBean);

        if (methodCountMap == null) {
            synchronized (StaticsFilterHelp.class) {
                methodCountMap = STATICS_DATA_MAP.get(classIdBean);
                if (methodCountMap == null) {
                    methodCountMap = new ConcurrentHashMap<String, AtomicLong>();
                    STATICS_DATA_MAP.put(classIdBean, methodCountMap);
                }
            }
        }

        AtomicLong count = methodCountMap.get(methodName);
        if (count == null) {
            synchronized (StaticsFilterHelp.class) {
                count = methodCountMap.get(methodName);
                if (count == null) {
                    count = new AtomicLong(0);
                    methodCountMap.put(methodName, count);
                }
            }
        }
        count.incrementAndGet();
    }

    public static long getValue(ClassIdBean classIdBean, String methodName) {
        Map<String, AtomicLong> methodCounMap = STATICS_DATA_MAP.get(classIdBean);
        if (methodCounMap == null)
            return 0;
        AtomicLong count = methodCounMap.get(methodName);
        return count == null ? 0 : count.get();
    }


}
