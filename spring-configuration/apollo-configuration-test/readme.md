# Apollo 配置测试

## 启动 server
- 初始化数据库：执行 `apolloconfigdb.sql` 和 `apolloportaldb.sql`
- 启动：`sh demo.sh start`

### 访问
- [入口](http://localhost:8070)
- 登录账号：`apollo/admin`

### Spring 配置
- 要启用 `apollo.bootstrap.enabled = true`，才能获取到配置