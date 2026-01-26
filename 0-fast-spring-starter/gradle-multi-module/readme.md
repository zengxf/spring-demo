# 快速创建 Gradle 多模块（Spring Web）项目-脚手架

## 版本
- JDK: 17
- Gradle: 8.8

## Gradle 相关
- 查看所有项目：`gradle -q projects`
- 构建所有项目：`gradle build -x test`
- **清理**单个项目：`gradle :spring-web-1:clean`
- **构建**单个项目：`gradle :spring-web-1:build -x test`