package org.chench.extra.dubbo.apisix.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 自动注册配置文件中的Dubbo服务
 *
 * @author chench
 * @desc org.chench.extra.dubbo.apisix.config.DubboAPISIXAutoConfiguration
 * @date 2023.08.07
 */
@Configuration
@ImportResource({"classpath:dubbo-provider.xml"})
public class DubboAPISIXAutoConfiguration {
}