# Apollo 配置测试

## 启动 server
- 初始化数据库：执行 `apolloconfigdb.sql` 和 `apolloportaldb.sql`
- 启动：`sh demo.sh start`

### 添加环境
- 在 `ApolloPortalDB` 库 `serverconfig` 表的 `apollo.portal.envs` 列上进行相应修改
- 编辑 `demo.sh`，添加 `-Ddev_meta` 和 `-Dpro_meta`
- 应用指定环境：
  - 可参考 `DefaultServerProvider` 类，在相应文件下设置
  - 也可设置 JVM 参数 `-Denv=PRO`
  - 另：在 `apollo-env.properties` 指定 `xx.meta` 路径

### 访问
- [入口](http://localhost:8070)
- 登录账号：`apollo/admin`

### Spring 配置
- 要设置 `apollo.bootstrap.enabled = true`，才能获取到配置
- 设置命名空间 `@EnableApolloConfig( { "ns1" } )`，默认是 `application`
  - 要和 `@Configuration` 一起使用
- 设置集群 `apollo.cluster = test-cluster`，默认是 `default`