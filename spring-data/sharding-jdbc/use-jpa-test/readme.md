# 使用 jap 测试 sharding-jdbc
- 数据库 MySQL
- [参考项目](https://github.com/yinjihuan/sharding-jdbc)

## 不支持的语句
- `REPLACE INTO`

## 主从
- 写是主，读是从

## 配置
- 不需要 `driver-class-name`，会自动查找
- `datasource` 下的配置，会自动调用对应的数据源，进行设置值
  - 参考 `SpringBootConfiguration.getDataSource`