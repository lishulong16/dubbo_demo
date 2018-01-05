package net.dubbo.spring.boot.listener;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.utils.ConcurrentHashSet;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.listener.InvokerListenerAdapter;
import net.dubbo.spring.boot.domain.ClassIdBean;
import net.dubbo.spring.boot.domain.SpringBootStartedDubboConstans;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/29
 * @doing
 */
public class ConsummerSubscribeListener extends InvokerListenerAdapter {

    public static final Set<ClassIdBean> SUBSCRIBEINTERFACE_SET = new ConcurrentHashSet<ClassIdBean>();

    public static final Map<ClassIdBean, Set<String>> CONNECTION_MAP = new ConcurrentHashMap<ClassIdBean, Set<String>>();

    @Override
    public void referred(Invoker<?> invoker) throws RpcException {
        Class<?> anInterface = invoker.getInterface();
        URL url = invoker.getUrl();

        String group = url.getParameter(SpringBootStartedDubboConstans.GROUP);

        String vsersion = url.getParameter(SpringBootStartedDubboConstans.VERSION);

        ClassIdBean classIdBean = new ClassIdBean(anInterface, group, vsersion);

        SUBSCRIBEINTERFACE_SET.add(classIdBean);

        Set<String> connectionSet = CONNECTION_MAP.get(classIdBean);

        if (connectionSet == null) {
            connectionSet = new ConcurrentHashSet<String>();
            CONNECTION_MAP.put(classIdBean, connectionSet);
        }

        connectionSet.add(invoker.getUrl().toString());
    }

    @Override
    public void destroyed(Invoker<?> invoker) {

        Class<?> anInterface = invoker.getInterface();

        URL url = invoker.getUrl();

        String group = url.getParameter(SpringBootStartedDubboConstans.GROUP);
        String version = url.getParameter(SpringBootStartedDubboConstans.VERSION);

        ClassIdBean classIdBean = new ClassIdBean(anInterface, group, version);

        SUBSCRIBEINTERFACE_SET.remove(classIdBean);

        Set<String> contentSet = CONNECTION_MAP.get(classIdBean);

        if (contentSet != null) {
            contentSet.remove(invoker.getUrl().toString());
        }
        if (contentSet == null || contentSet.size() == 0)
            CONNECTION_MAP.remove(classIdBean);
    }
}
