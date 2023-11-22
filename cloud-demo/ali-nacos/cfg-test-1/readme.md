# Ali Nacos 配置测试

- Nacos: http://127.0.0.1:8848/nacos/

## 问题

1. `Server check fail, please check server 127.0.0.1 ,port 9848 is available , error ={}`
    - 没启动 Nacos 服务，启动即可

2. **启动不了**
    ```js
    Exception encountered during context initialization 
    - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: 
    Error creating bean with name 'compositeCompatibilityVerifier' defined in class path resource 
    [org/springframework/cloud/configuration/CompatibilityVerifierAutoConfiguration.class]: 
    Failed to instantiate [org.springframework.cloud.configuration.CompositeCompatibilityVerifier]: 
    Factory method 'compositeCompatibilityVerifier' threw exception with message: 
    Spring Cloud/ Spring Boot version compatibility checks have failed: 
    [[VerificationResult@7ac48f05 description = 'Spring Boot [3.1.2] is not compatible with this Spring Cloud release train', 
    action = 'Change Spring Boot version to one of the following versions [3.0.x] .
    ```
    - 将 spring-boot 改成 `3.0.0`

## 测试

1. Nacos 建命名空间
    - http://127.0.0.1:8848/nacos/#/namespace
    - `my-test`
2. Nacos 建配置
    - http://127.0.0.1:8848/nacos/#/configurationManagement
    - 先选择 `my-test`
    - 再创建：Data ID: `my-ser.yaml` Group: `DEFAULT_GROUP`，格式：`YAML`
    - 将 `my-ser-sample.yml` 内容复制进去
    - 点击发布即可
3. 重启应用
4. 访问测试
    - http://localhost:9001/api/biz/hello 