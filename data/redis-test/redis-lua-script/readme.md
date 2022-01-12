# Redis lua 脚本测试
- 参考：《SpringCloud + Nginx 高并发核心编程》

## 问题
- Redis Key 出现：`\xac\xed\x00\x05t\x00\x11`
  - 解决：配置序列化类 