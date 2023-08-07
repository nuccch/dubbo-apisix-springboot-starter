package org.chench.extra.dubbo.apisix;

import com.alibaba.fastjson2.JSONObject;
import org.chench.extra.dubbo.apisix.constant.ResponseStatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * APISIX调用的Dubbo服务接口实现
 *
 * @author chench
 * @desc org.chench.extra.dubbo.apisix.APISIX2DubboServiceImpl
 * @date 2023.08.06
 */
public class APISIX2DubboServiceImpl implements APISIX2DubboService {
    private static final Logger LOGGER = LoggerFactory.getLogger(APISIX2DubboServiceImpl.class);

    @Autowired
    private ApplicationContext appContext;

    @Override
    public Map<String, Object> invoke(Map<String, Object> context) throws Exception {
        // 返回给APISIX的数据是一个Map对象
        Map<String, Object> httpResp = new HashMap<>();
        String body = null;
        try {
            body = new String((byte[]) context.get("body"));
            // 利用反射机制调用Dubbo服务的方法
            DubboInvocation invocation = JSONObject.parseObject(body, DubboInvocation.class);
            int paramSize = invocation.getParameters().length;
            Object[] args = new Object[paramSize];
            Class[] types = new Class[paramSize];
            for (int i = 0; i < args.length; i++) {
                DubboInvocationParameter parameter = invocation.getParameters()[i];
                args[i] = parameter.getValue();
                types[i] = Class.forName(parameter.getType());
            }
            Object svc = appContext.getBean(Class.forName(invocation.getService()));
            Object result = svc.getClass().getMethod(invocation.getMethod(), types).invoke(svc, args);
            httpResp.put("status", ResponseStatusCode.OK.getCode());
            httpResp.put("body", JSONObject.toJSONString(result));
        } catch (Exception e) {
            LOGGER.error("invoke dubbo error, body: {}, error: {}", body, e.getMessage(), e);
            httpResp.put("status", ResponseStatusCode.ERROR.getCode());
            httpResp.put("body", e.getStackTrace());
            e.printStackTrace();
        }
        return httpResp;
    }
}