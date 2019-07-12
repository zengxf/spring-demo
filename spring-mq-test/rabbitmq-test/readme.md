# RabbitMQ 测试

## Docker 部署
- `docker run -it --rm --name rabbit --network host rabbitmq:3.7.16-management`
  - 设置密码 `-e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=password`
- Web 控制台：http://192.168.1.117:15672 
  - 默认用户：guest/guest
- TCP 端口：5672

## 配置 JSON 序列化
- 生产者需要配置序列化
- 消费者需要配置反序列化