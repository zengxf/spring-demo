## MQ 启动

- 配置环境变量 `ROCKETMQ_HOME` 为 `C:\Install\Java\Cloud-Plugin\rocketmq-5.1.0`

```js
// 改 bin\runserver.cmd，使用 JDK8
set "JAVA=%Java8%\bin\java.exe"
"%JAVA%" -version

// 启动
bin> mqnamesrv.cmd


// 改 bin\runbroker.cmd，使用 JDK8
set "JAVA=%Java8%\bin\java.exe"
"%JAVA%" -version

// 改 conf/broker.conf，设置命名服务
// broker 没注册上，参考：https://blog.51cto.com/u_14861909/5708412
namesrvAddr = localhost:9876
brokerIP1 = localhost # IP 可以不设置

// 启动
bin> mqbroker.cmd  -c ../conf/broker.conf
```

- MqAdmin 参考：
    - https://www.cnblogs.com/gmq-sh/p/6232633.html
    - https://rocketmq-1.gitbook.io/rocketmq-connector/kai-fa-zhe-zhong-xin/mqadmin-cao-zuo-zhi-nan
- 创建 Topic

```js
> cd /d C:\Install\Java\Cloud-Plugin\rocketmq-5.1.0\bin 

// 操作 Topic
// 查看命令帮助
mqadmin updateTopic
// 创建 Topic
mqadmin updateTopic  -n localhost:9876  -b localhost:10911  -t test-1
// 查看所有 Topic
mqadmin topicList  -n localhost:9876

// 查看 Broker 信息
mqadmin brokerStatus  –n localhost:9876  –b localhost:10911

// 查看集群信息
mqadmin clusterList  -n localhost:9876
mqadmin clusterList  -n localhost:9876  -m
```