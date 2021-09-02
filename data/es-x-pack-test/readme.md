# ES-x-pack 测试

## 好的参考资料
- Spring Data Elasticsearch 使用： https://www.jianshu.com/p/56e755415e63
  - 有介绍 `Repository` 风格操作

## ElasticSearch

- ES 下载： https://www.elastic.co/cn/downloads/elasticsearch-no-jdk
- ES 版本：`elasticsearch-7.14.0-no-jdk`
- 启用 x-pack 参考： https://www.jianshu.com/p/40e35c6a32a4
    - 改配置 `elasticsearch.yml`
        - 新增 `xpack.security.enabled: true`
    - 重启 ES：双击 `elasticsearch.bat`
    - 设置密码 ` ./bin/elasticsearch-setup-passwords interactive `
        - `auto` - 随机生成密码
        - `interactive` - 自定义不同用户的密码
        - 注意：必须配置好 x-pack 之后，才能设置密码，否则会报错

## Kibana

- 改配置 `kibana.yml`
    - 启用 `elasticsearch.hosts`，使用默认即可
    - 启用 `elasticsearch.username`，使用默认即可
    - 修改 `elasticsearch.password`
- 启动：双击 `kibana.bat`
- 访问： http://127.0.0.1:5601/
    - 登录：`elastic/xxxxxx`，使用 `elastic` 账号
- DevTools 使用参考： https://segmentfault.com/a/1190000038394618

## 使用 Spring-Data 遇到的问题及解决

- 实体类要用 `@Document(indexName = "user")` 注解
    - `indexName` 必须是小写
- 创建索引时，需要设置 `number_of_shards`

## 其他说明

- 保存时：会自动生成 ID
- index 不存在时
    - `get()` 查找会报错
    - 但保存 `save()` 不会报错，会自动创建
- 字符串类型要加 `.keyword`
  - 可用 Kibana 的 DevTools 工具的 SQL 转换查看
- 聚合统计查询会返回所有的匹配记录，这个有点坑

### Kibana SQL 示例

- 使用参考： https://segmentfault.com/a/1190000038394618
- SQL 查询

```curl
POST /_sql?format=txt
{
  "query": "SQL_statement"
}
```

- SQL 转化 DSL

```curl
POST /_sql/translate
{
  "query": "SQL_statement"
}
```

- SQL 示例

```SQL
SELECT id, name, age, birthday, city, updateTime FROM user LIMIT 10

SELECT * FROM user LIMIT 10

SELECT age, COUNT(*) AS total FROM user GROUP BY age LIMIT 10
```

### SQL Client

- 使用参考： https://segmentfault.com/a/1190000038394618
- ES 自带的 SQL CLI
- 启动：` elasticsearch-sql-cli http://elastic:xxxxxx@127.0.0.1:9200 `
- 然后输入 SQL 查询，要加分号