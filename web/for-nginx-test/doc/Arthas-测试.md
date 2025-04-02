# Arthas 测试

---
## 热替换
```shell
# 打包
gradle build -x test

# 启动
java -jar ./build/libs/for-nginx-test.jar

# 测试
curl http://localhost:9901/test/arthas/base


# ---------------------------------
# ---------------------------------


# 启动 Arthas

# arthas-4.0.5> 
java -jar arthas-boot.jar

# 输出
[6]: 33456 ./build/libs/for-nginx-test.jar

# 选择
6

# 查看加载器
sc -d cn_zxf_test.test.ArthasController
 
# 查看源码
jad cn_zxf_test.test.ArthasController
# 指定类加载器，使用类全称 (不能用 Hash)。 这步可以不用指定 类加载器
# jad --classLoaderClass org.springframework.boot.loader.launch.LaunchedClassLoader cn_zxf_test.test.ArthasController

# 重新改 ArthasController 并编译

# 热加载类
redefine D:/MyData/pub-project/spring-demo/web/for-nginx-test/build/classes/java/main/cn_zxf_test/test/ArthasController.class
# 指定类加载器，使用类全称 (不能用 Hash)。 这步可以不用指定 类加载器
# redefine --classLoaderClass org.springframework.boot.loader.launch.LaunchedClassLoader \
#   D:/MyData/pub-project/spring-demo/web/for-nginx-test/build/classes/java/main/cn_zxf_test/test/ArthasController.class

# 重新测试   (有效果)
curl http://localhost:9901/test/arthas/base

# 重新看源码 (没效果)
# 原因：
#     redefine 与 jad 的机制冲突
#     redefine 和 jad/watch/trace 等命令共享 JVM 的 Retransform 机制。
#     执行 redefine 后，再运行 jad 会触发字节码重置
jad cn_zxf_test.test.ArthasController
```