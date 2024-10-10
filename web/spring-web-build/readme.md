# Spring Web 构建


## 排除指定文件
```shell
# 测试任务 (加参)
gradle -PExcludeFlag=1 task2

# 排除指定文件 (构建)
gradle -PExcludeFlag=1 build -x test


# 默认不排除 (构建)
gradle build -x test
```


## 测试 jar
```shell
# prod (ok)
java -jar -Dspring.profiles.active=prod   build/libs/spring-web-build.jar

# pwd (使用默认值，不会异常)
java -jar -Dspring.profiles.active=pwd    build/libs/spring-web-build.jar
java -jar                                 build/libs/spring-web-build.jar
```


## JLink 测试
```shell
# Git Bash 下运行

# 使用 Gradle 插件
gradle jlink

# 运行
./build/image/bin/spring-web-build


```

### 解压
```shell
# 进目录
cd ./build/image/lib

# 提取
jimage extract modules
```


## Native 测试
```shell
# 编译 (跳过测试)
gradle nativeCompile -x nativeTestCompile

# 运行 (指定参数)
./build/native/nativeCompile/spring-web-build -Dspring.profiles.active=prod
```