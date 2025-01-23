# 用于测试 Nginx

## 文件索引

- [TestController.java](./src/main/java/cn_zxf_test/test/TestController.java)
- [base-test.http](./src/test/http/base-test.http)
- [Nginx-Configs](https://github.com/zengxf/super-demo/tree/master/Demo-Config/Nginx)

## 测试

### CURL 测试

```shell
curl --header "Content-Type: application/json" \
  --request GET \
  --data '{"key1": "v1-009"}' \
  http://localhost:9901/test/base?test1=v1&key2=v2
```

## Tomcat 总结

### Tomcat 改日志级别

```yaml
logging:
  level:
    org:
      apache:
        tomcat: info
        catalina: info
        coyote: debug   # host 解析出错好打印日志
```

### 请求头 host 解析异常

- 处理类: `org.apache.coyote.http11.Http11Processor`
- 日志和异常栈:

```shell
# 日志
... --- [nio-9901-exec-2] o.apache.coyote.http11.Http11Processor   : The host [alive_test_up] is not valid

# 异常栈
java.lang.IllegalArgumentException: The character [_] is never valid in a domain name.
	at org.apache.tomcat.util.http.parser.HttpParser$DomainParseState.next(HttpParser.java:1045) ~[tomcat-embed-core-10.1.28.jar:10.1.28]
	at org.apache.tomcat.util.http.parser.HttpParser.readHostDomainName(HttpParser.java:931) ~[tomcat-embed-core-10.1.28.jar:10.1.28]
	at org.apache.tomcat.util.http.parser.Host.parse(Host.java:67) ~[tomcat-embed-core-10.1.28.jar:10.1.28]
	at org.apache.tomcat.util.http.parser.Host.parse(Host.java:43) ~[tomcat-embed-core-10.1.28.jar:10.1.28]
	at org.apache.coyote.AbstractProcessor.parseHost(AbstractProcessor.java:298) ~[tomcat-embed-core-10.1.28.jar:10.1.28]
	at org.apache.coyote.http11.Http11Processor.prepareRequest(Http11Processor.java:777) ~[tomcat-embed-core-10.1.28.jar:10.1.28]
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:361) ~[tomcat-embed-core-10.1.28.jar:10.1.28]
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63) ~[tomcat-embed-core-10.1.28.jar:10.1.28]
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:904) ~[tomcat-embed-core-10.1.28.jar:10.1.28]
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1741) ~[tomcat-embed-core-10.1.28.jar:10.1.28]
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52) ~[tomcat-embed-core-10.1.28.jar:10.1.28]
	at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1190) ~[tomcat-embed-core-10.1.28.jar:10.1.28]
	at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659) ~[tomcat-embed-core-10.1.28.jar:10.1.28]
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:63) ~[tomcat-embed-core-10.1.28.jar:10.1.28]
	at java.base/java.lang.Thread.run(Thread.java:840) ~[na:na]
```