## Create table
```sql
CREATE TABLE `qps_rule` (
    `id`            INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '规则 ID',
    
    `tenant`        VARCHAR(64) NOT NULL DEFAULT '' COMMENT '租户 (机构)',
    `app`           VARCHAR(64) NOT NULL DEFAULT '' COMMENT '应用名 (可对指定应用限流)',
    `api`           VARCHAR(255) NOT NULL DEFAULT '' COMMENT '接口 path (可对指定接口限流)',
    `type`          TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '规则类型: 1-QPS; 2-Traffic',
    `limit`         BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '限制数',
    `window`        INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '时间窗口 (ms)',
    `enable`        TINYINT(1) NOT NULL DEFAULT 1 COMMENT '是否启用: 0-禁用; 1-启用',
    
    `create_time`   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    
    -- 完善的审计能力
    `creator`       VARCHAR(64) COMMENT '创建人',
    `modifier`      VARCHAR(64) COMMENT '更新人',
    `description`   VARCHAR(512) COMMENT '规则描述',
    `ext_config`    JSON COMMENT '扩展配置 (JSON 格式)',
    
    PRIMARY KEY (`id`),
    
    KEY `idx_tenant` (`tenant`),
    KEY `idx_app` (`app`),
    KEY `idx_enable` (`enable`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='限流规则配置表'
;
```


## Init - 机构
```sql
INSERT INTO qps_rule
    (tenant, app, api, `type`, `limit`, `window`)
VALUES
    ('DC', 'test-web1', '/api/biz/mockReq1', 1, 2, 2000),
    ('DC', 'test-web1', '', 1, 4, 2000),    -- 不设置具体 api
    ('DC', '', '', 1, 8, 2000)              -- 不设置具体应用
;

INSERT INTO qps_rule
    (tenant, app, api, `type`, `limit`, `window`)
VALUES
    ('DC', 'test-web1', '/api/biz/mockFlow1k', 2, 2000, 2000),
    ('DC', 'test-web1', '', 2, 4000, 2000), -- 不设置具体 api
    ('DC', '', '', 2, 8000, 2000)           -- 不设置具体应用
;
```


## Bck
```sql
INSERT INTO qps_rule(
    id, tenant, app, api, `type`, `limit`, `window`, `enable`
) VALUES (
    1089, 'zxf', 'test-web1', '/api/biz/test', 1, 20, 2000, 1
)
ON DUPLICATE KEY UPDATE
    `type` = VALUES(`type`),
    `limit` = VALUES(`limit`),
    `window` = VALUES(`window`),
    `enable` = VALUES(`enable`),
    update_time = CURRENT_TIMESTAMP
;
```