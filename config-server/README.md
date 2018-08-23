# config-server

## 自动配置更新机制
采用spring cloud config server。
* 在server端配置config文件目录。
`application-*.yml`文件配置示例:
 ```
spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/huangydyn/spring-cloud-config-example.git
          username: huangydyn
          password: {GIT_PASSWORD}
          searchPaths: ConfigData
```
对应配置文件目录在对应git仓库下ConfigData目录。更新该git仓库下配置文件*properties。
- 无消息总线更新方式
client服务定时调用自身服务`POST /refresh`接口实时可刷新客户端程序的配置。

- 有消息总线更新方式
调用本服务`POST /bus/refresh`接口实时可刷新所有客户端程序的配置。
未配置ApplicationContextID刷新单个服务：
`POST bus/refresh?destination=applicationName:port`

配置ApplicationContextID刷新单个服务：
配置
  `
  spring.application.name=client_a
  spring.application.index=1a2b3e1a2b3e1a2b3e
  POST bus/refresh?destination=client_a:1a2b3e1a2b3e1a2b3e`
* http请求地址和资源文件映射如下:
  - /{application}/{profile}[/{label}]
  - /{application}-{profile}.yml
  - /{label}/{application}-{profile}.yml
  - /{application}-{profile}.properties
  - /{label}/{application}-{profile}.properties
 访问实例:
 ```
 curl localhost:8001/alert_config/uat/master
 ```
  注:
   - {application} 对应Client的"spring.application.name"属性;
   - {profile} 对应Client的 "spring.profiles.active"属性(逗号分隔的列表); 
   - {label} 映射到Git服务器的commit id, 分支名称或者tag，默认值为master