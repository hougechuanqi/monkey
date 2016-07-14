package pub.vrrech.monkey.rpc.api.filter;

import java.util.HashMap;
import java.util.Map;

import pub.vrrech.monkey.rpc.api.Filter;
import pub.vrrech.monkey.rpc.api.Invocation;
import pub.vrrech.monkey.rpc.api.Invoker;
import pub.vrrech.monkey.rpc.api.Result;
import pub.vrrech.monkey.rpc.api.RpcContext;
import pub.vrrech.monkey.rpc.api.RpcInvocation;
import pub.vrrech.monkey.rpc.api.exception.RpcException;
import pub.vrtech.common.Constants;

/***
 * 
 *
 * Function description：
 * 1.XXX
 * 2.XXX
 * @author houge
 */
public class ContextFilter implements Filter {

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Map<String, String> attachments = invocation.getAttachments();
        if (attachments != null) {
            attachments = new HashMap<String, String>(attachments);
            attachments.remove(Constants.PATH_KEY);
            attachments.remove(Constants.GROUP_KEY);
            attachments.remove(Constants.VERSION_KEY);
            attachments.remove(Constants.TOKEN_KEY);
            attachments.remove(Constants.TIMEOUT_KEY);
        }
        RpcContext.getContext()
                .setInvoker(invoker)
                .setInvocation(invocation)
                .setAttachments(attachments)
                .setLocalAddress(invoker.getUrl().getHost(), 
                                 invoker.getUrl().getPort());
        if (invocation instanceof RpcInvocation) {
            ((RpcInvocation)invocation).setInvoker(invoker);
        }
        try {
            return invoker.invoke(invocation);
        } finally {
            RpcContext.removeContext();
        }
    }
}