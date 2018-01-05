package net.dubbo.spring.boot.listener;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.utils.ConcurrentHashSet;
import com.alibaba.dubbo.rpc.Exporter;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.listener.ExporterListenerAdapter;
import net.dubbo.spring.boot.domain.ClassIdBean;
import net.dubbo.spring.boot.domain.SpringBootStartedDubboConstans;

import java.util.Set;

/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/29
 * @doing
 */
public class ProviderExportListener extends ExporterListenerAdapter {

    public static final Set<ClassIdBean> EXPORTINTERFACE_SET = new ConcurrentHashSet<ClassIdBean>();

    public static final Set<URL> EXPORT_URL = new ConcurrentHashSet<URL>();

    @Override
    public void exported(Exporter<?> exporter) throws RpcException {
        Invoker<?> invoker = exporter.getInvoker();

        Class<?> anInterface = invoker.getInterface();

        URL url = invoker.getUrl();
        String version = url.getParameter(SpringBootStartedDubboConstans.VERSION);
        String group = url.getParameter(SpringBootStartedDubboConstans.GROUP);
        ClassIdBean classIdBean = new ClassIdBean(anInterface, group, version);

        EXPORTINTERFACE_SET.add(classIdBean);

        if (!url.getProtocol().equals("injvm"))
            EXPORT_URL.add(url);
    }

    @Override
    public void unexported(Exporter<?> exporter) throws RpcException {
        Invoker<?> invoker = exporter.getInvoker();
        Class<?> anInterface = invoker.getInterface();
        URL url = invoker.getUrl();
        String group = url.getParameter(SpringBootStartedDubboConstans.GROUP);
        String version = url.getParameter(SpringBootStartedDubboConstans.VERSION);
        ClassIdBean classIdBean = new ClassIdBean(anInterface, group, version);
        EXPORTINTERFACE_SET.remove(classIdBean);
        if (!url.getProtocol().equals("injvm"))
            EXPORT_URL.remove(url);

    }
}
