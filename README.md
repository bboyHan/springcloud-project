## Spring Cloud Demo。

* 个人微信号：bboyHan
* 微信公众号：sharontang0323，关键字搜索“23号杂货铺”
* 每日技术杂文更新，欢迎关注

### 项目环境：

- jdk 8 +
- Spring Cloud Greenwich.RELEASE
- Spring Boot 2.1.2.RELEASE

### 项目说明

| 模块 | 说明 | 备注 |
|------|------|------|
| han-discovery-eureka | eureka服务发现  | 端口8761 |
| han-consumer-user-feign | feign消费调用  | 端口9001 |
| han-provider-user | user server服务  | 端口8001 |
| han-provider-search | elasticsearch服务  | 端口8011 |
| han-consumer-rabbitmq | rabbitmq消息中间件 | 端口7901，正在完善... |
| han-common-util | 通用工具包  |   |

###