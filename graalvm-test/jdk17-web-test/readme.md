# GraalVM Web 测试


## 参考
- Gradle - `graalvmNative` 配置: https://graalvm.github.io/native-build-tools/latest/gradle-plugin.html


## 命令
```shell
# 编译

echo %time% && ^
gradle nativeCompile 

echo %time% && ^
echo %time%


# 运行 (加参)

my-app p1 p2


# 测试

curl http://localhost:9001/api/biz/hello
```