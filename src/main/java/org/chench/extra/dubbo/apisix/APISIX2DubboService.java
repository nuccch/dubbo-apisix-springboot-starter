package org.chench.extra.dubbo.apisix;

import java.util.Map;

/**
 * APISIX调用的Dubbo服务接口
 *
 * @author chench
 * @desc org.chench.extra.dubbo.apisix.APISIX2DubboService
 * @date 2023.08.06
 */
public interface APISIX2DubboService {
    Map<String, Object> invoke(Map<String, Object> context) throws Exception;
}
