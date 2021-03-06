# RabbitMQ 测试

## 参考
- [理解 RabbitMQ Exchange](https://zhuanlan.zhihu.com/p/37198933)

## Docker 部署
- `docker run -it --rm --name rabbit --network host rabbitmq:3.7.16-management`
  - 设置密码 `-e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=password`
- Web 控制台：http://192.168.1.117:15672 
  - 默认用户：guest/guest
- TCP 端口：5672

## 配置 JSON 序列化
- 生产者需要配置序列化
- 消费者需要配置反序列化

## 权限
- 创建用户 `zxf`
- Set permission: `/` `test.*` `amq\.default|test.*` `amq\.default|test.*`

## 本地启动
- 进入 `/sbin` 目录，运行 `rabbitmq-server.bat`
- 访问：http://localhost:15672/
- 登录：`admin / admin`