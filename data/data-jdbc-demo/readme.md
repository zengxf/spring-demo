# Spring Data JDBC 测试

## MySQL 优化

- 优化 https://blog.csdn.net/zhengxiaoya2017/article/details/123668080
- `sync_binlog` 参数说明 https://www.cnblogs.com/Cherie/p/3309503.html
- `innodb_flush_log_at_trx_commit` 参数说明 https://support.huaweicloud.com/bestpractice-rds/rds_02_0010.html

```sql
-- 下面设置并没有提高 MySQL 插入性能
SELECT @@innodb_buffer_pool_size;
SET GLOBAL innodb_buffer_pool_size = 536870912;


SELECT @@sync_binlog;
SET GLOBAL sync_binlog = 2000;

-- 这个有点用
SELECT @@innodb_flush_log_at_trx_commit;
SET GLOBAL innodb_flush_log_at_trx_commit = 0;
```