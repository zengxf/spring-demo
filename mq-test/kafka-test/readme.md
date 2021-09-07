# Kafka 测试

## 消费组 ID
- 不同的组会重复消费
- 相当的组，会根据 partition 进行分配消费

## 启动
- 其自带有 ZK
  - 改配置 `zookeeper.properties`
    - 修改：`dataDir=../../data/zookeeper`
  - 启动：`zookeeper-server-start.bat ../../config/zookeeper.properties`
- 改配置 `server.properties`
  - 修改：`log.dirs=../../data/kafka-logs`
  - 添加：`auto.create.topics.enable=true`
- 启动：`kafka-server-start.bat ../../config/server.properties`

## GUI: Conduktor
- 可以创建主题
- 消费消息
- 生产消息
  - 格式支持：String、JSON

## 问题
1. 生产/消息一些消息后，Kafka 重启出错
    - 删除 ZK 的数据文件夹和 Kafka 的日志文件夹
2. 使用 Conduktor 生产数据时，消费者消费报错
    - 需要手动添加头：`__TypeId__`，值是（类名）：`cn.zxf.common.Email`
    - 其实查看 Conduktor 的消费记录（双击进去），里面也有头 `__TypeId__`