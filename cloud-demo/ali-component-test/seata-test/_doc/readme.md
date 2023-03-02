## Seata Server 部署

### 参考

- https://seata.io/zh-cn/docs/ops/deploy-guide-beginner.html
- SQL 位置：`%SEATA-HOME%\script\server\db`
    - 执行 `mysql.sql`
- 把 `lib\jdbc\mysql-connector-java-8.0.27.jar` 复制到 `lib` 下
- 把 `conf\application.example.yml` 里面 `store.db` 部分复制 `application.yml`
    - 修改配置：
        - `mode: db`
        - `driver-class-name: com.mysql.cj.jdbc.Driver`
- ~~Nacos 配置~~：

```yml
  registry:
    # support: nacos, eureka, redis, zk, consul, etcd3, sofa
    type: nacos
    nacos:
      application: seata-server
      server-addr: 127.0.0.1:8848
      namespace: my-test
      group: group1
      cluster: default
```

### Web 控制台

- http://localhost:7091/
- 账号：`seata/seata`

---

## Java

### 参考

- 使用：https://blog.csdn.net/dghkgjlh/article/details/104730024
- undo_log.sql: https://github.com/seata/seata-samples/blob/master/ha/src/main/resources/sql/undo_log.sql

### JDK17 报错

- 添加 JVM 参数：`--add-opens=java.base/java.lang=ALL-UNNAMED`

### sign 说明

```js
sign | ser-main | ser-order | ser-info
--   |   --     |    --     |    --
1    |   err    |    --     |    --
2    |   -      |    err    |    --
3    |   -      |    --     |    err
```