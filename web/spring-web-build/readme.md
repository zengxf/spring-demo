# Spring Web 构建


## 排除指定文件
```shell
# 测试任务 (加参)
gradle -PExcludeFlag=1 task2

# 排除指定文件 (构建)
gradle -PExcludeFlag=1 build -x test
```


## 测试 jar
```shell
# prod (ok)
java -jar -Dspring.profiles.active=prod   build/libs/spring-web-build.jar

# pwd (使用默认值，不会异常)
java -jar -Dspring.profiles.active=pwd    build/libs/spring-web-build.jar
```