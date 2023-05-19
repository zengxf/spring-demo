# 懒加载测试

## 使用

- 直接加注解 `@ComponentScan(lazyInit = true)` 即可

## 单元测试用时测试

```shell
# 开启懒加载（lazyInit = true）： 用时 3~4 秒
# -----------
# cn_zxf_test.biz.BizServiceLazyTest
# -----------
11:41:22.933 [Test worker]	11:41:26: Execution finished
11:42:00.476 [Test worker] 	11:42:03: Execution finished
11:42:25.890 [Test worker]	11:42:29: Execution finished
11:42:53.996 [Test worker]	11:42:57: Execution finished

# 禁用懒加载（lazyInit = false）：用时 7 秒
# -----------
# cn_zxf_test.biz.BizServiceNotLazyTest
# -----------
11:43:52.226 [Test worker]  11:43:59: Execution finished
11:48:06.012 [Test worker]  11:48:13: Execution finished
11:48:34.223 [Test worker]  11:48:41: Execution finished 
11:49:00.793 [Test worker]  11:49:07: Execution finished
```