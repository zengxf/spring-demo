# RabbitMQ 测试

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