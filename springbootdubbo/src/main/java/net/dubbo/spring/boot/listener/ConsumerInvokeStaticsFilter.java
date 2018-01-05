package net.dubbo.spring.boot.listener;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import net.dubbo.spring.boot.domain.ClassIdBean;
import net.dubbo.spring.boot.domain.SpringBootStartedDubboConstans;

/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/29
 * @doing
 */
@Activate(group = Constants.CONSUMER)
public class ConsumerInvokeStaticsFilter extends StaticsFilterHelp {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Invoker<?> invocationInvoker = invocation.getInvoker();
        Class<?> interfaces = invocationInvoker.getInterface();
        URL url = invocationInvoker.getUrl();
        String group = url.getParameter(SpringBootStartedDubboConstans.GROUP);
        String version = url.getParameter(SpringBootStartedDubboConstans.VERSION);
        ClassIdBean classIdBean = new ClassIdBean(interfaces, group, version);
        increase(classIdBean, invocation.getMethodName());
        return invoker.invoke(invocation);
    }
}
