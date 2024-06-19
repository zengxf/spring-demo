# RocketMQ 测试

## Docker 部署
```
echo "brokerIP1=192.168.1.117" > /data/test/docker/broker.properties
docker run -it --name rocketmq --rm --network host -v /data/test/docker:/opt/conf rocketmqinc/rocketmq:4.4.0

# 进入内容运行
docker exec -it rocketmq /bin/bash
sh mqnamesrv -n 192.168.1.117:9876
sh mqbroker -n 192.168.1.117:9876 -c /opt/conf/broker.properties

# Web 控制台
docker run --name rocketmq-web -e "JAVA_OPTS=-Drocketmq.namesrv.addr=192.168.1.117:9876 -Dcom.rocketmq.sendMessageWithVIPChannel=false" -p 8880:8080 -it --rm styletang/rocketmq-console-ng
```

## 单机部署
- 参考：[RocketMQ-单机本地部署](https://github.com/zengxf/super-demo/blob/master/X-In-Action/Ali-X/RocketMQ-单机本地部署.md)
```js
// 创建主题
mqadmin updateTopic -b 127.0.0.1:10911 -t test-obj
```

## 问题
- `No route info of this topic: test-obj` -> https://www.cnblogs.com/lingyejun/p/16369083.html