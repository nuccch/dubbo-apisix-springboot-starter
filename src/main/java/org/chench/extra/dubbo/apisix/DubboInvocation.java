package org.chench.extra.dubbo.apisix;

/**
 * 对Dubbo服务调用的抽象
 *
 * @author chench
 * @desc org.chench.extra.dubbo.apisix.DubboInvocation
 * @date 2023.08.06
 */
public class DubboInvocation {
    /** 服务的完整限定名，如：org.chench.extra.dubbo.HelloService */
    private String service;

    /** 服务的方法名 */
    private String method;

    /** 服务的方法参数列表 */
    private DubboInvocationParameter[] parameters;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public DubboInvocationParameter[] getParameters() {
        return parameters;
    }

    public void setParameters(DubboInvocationParameter[] parameters) {
        this.parameters = parameters;
    }
}