# CommandLineRunner 与 ApplicationRunner 的区别
- 区别在于接收的参数不一样
    - CommandLineRunner 的参数是最原始的参数，没有做任何处理
    - ApplicationRunner 的参数是 ApplicationArguments，是对原始参数做了进一步的封装
    - ApplicationArguments 解析 `--name=value`
- 用户使用 CommandLineRunner 或者 ApplicationRunner 接口均可实现应用启动初始化某些功能的需求
- 如果希望对参数有更多的操作，则可以选择实现 ApplicationRunner 接口