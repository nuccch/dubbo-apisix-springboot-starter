# README

## 功能概述

这是一个`胶水层`的Dubbo服务，为Dubbo应用提供APISIX调用入口。  

## 如何使用

1.先将该项目安装到Maven本地仓库：`mvn clean install -Dmaven.test.skip=true`  
2.在需要给APISIX网关暴露Dubbo服务调用的模块中引入该组件：
```xml
<dependency>
    <groupId>org.chench.extra.spring.boot</groupId>
    <artifactId>dubbo-apisix-springboot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```
3.在APISIX中创建路由信息
```shell
curl http://127.0.0.1:9180/apisix/admin/routes/2  -H 'X-API-KEY: edd1c9f034335f136f87ad84b625c8f1' -X PUT -d '
{
    "uris": [
        "/demo"
    ],
    "plugins": {
        "dubbo-proxy": {
            "service_name": "org.chench.extra.dubbo.apisix.APISIX2DubboService",
            "service_version": "0.0.0",
            "method": "invoke"
        }
    },
    "upstream": {
		"type": "roundrobin",
		"nodes": {
		  "192.168.2.8:20881": 1  # 指定要调用的上游Dubbo服务地址
	    }
	}
}'
```
注意：在上述示例路由信息中，如下几个参数是需要根据实际情况进行修改的。
`uris`：这个参数修改为具体的接口路径
`upstream`：修改为具体的Dubbo服务地址


4.调用APISIX接口
```shell
curl http://127.0.0.1:9080/demo  -X POST --data '
{
    "service": "org.chench.extra.dubbo.consumer.service.HelloServiceConsumer", # 要调用的Dubbo服务接口完整限定名
    "method": "hello",                                                         # 要调用的Dubbo服务接口方法
    "parameters": [                                                            # 要调用的Dubbo服务接口参数列表
        {
            "type": "java.lang.String",                                        # 参数类型完整限定类名
            "value": "chench"                                                  # 参数值
        }
    ]
}'
```
