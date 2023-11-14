### **spring cloud study**

spring cloud-演示-研究-项目

common的为公共模块,包含公共头,公共体等。

eureka与eureka2为双注册中心,互相注册互相守望。

order为模拟订单模块,payment支付模块,互相假装微服务内远程调用。

rabbitMQ-provider/consumer为消息队列生产者与消费者。

redis为redis模块,模拟获取token防止重复提交校验,以及redis分布式锁。