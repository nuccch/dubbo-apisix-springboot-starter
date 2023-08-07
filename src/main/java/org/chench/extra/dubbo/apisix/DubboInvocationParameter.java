package org.chench.extra.dubbo.apisix;

/**
 * Dubbo服务调用的参数
 *
 * @author chench
 * @desc org.chench.extra.dubbo.apisix.DubboInvocationParameter
 * @date 2023.08.06
 */
public class DubboInvocationParameter {
    private String type;
    private String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}