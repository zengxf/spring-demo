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
- Nacos 配置：

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

### Seata 低版本序列化问题

- 参考：https://blog.csdn.net/fu_huo_1993/article/details/120706236

### 使用 Nacos 要升级 Seata 版本

- 可参考：`ser-order` 项目，不使用 `cloud-starter` 依赖

---

## 测试

### sign 说明

```js
sign | ser-main | ser-order | ser-info
--   |   --     |    --     |    --
1    |   err    |    --     |    --
2    |   --     |    err    |    --
3    |   --     |    --     |    err
4    | timeout  |    --     |    --
5    |   --     |  timeout  |    --
6    |   --     |    --     |  timeout
7    |   --     |   stop    |    --
8    |   --     |    --     |   stop
```

- http://localhost:6851/api/biz/operate?sign=1
- http://localhost:6851/api/biz/operate?sign=2
- http://localhost:6851/api/biz/operate?sign=3
- http://localhost:6851/api/biz/operate?sign=4
- http://localhost:6851/api/biz/operate?sign=5
- http://localhost:6851/api/biz/operate?sign=6
- http://localhost:6851/api/biz/operate?sign=7
- http://localhost:6851/api/biz/operate?sign=8
- http://localhost:6851/api/biz/operate?sign=100

### 测试总结

- 全局事务超时，会回滚
    - 下流节点也设置全局事务，不报错，如：`main 3s, info 300s(sleep 4s)`
    - 下流节点设置普通事务，会报错，如：`main 3s, order(sleep 4s)`
    - 下流节点设置全局事务，时间会以上流的为准，不报错，如：`main 300s, info 3s(sleep 4s)`