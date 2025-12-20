## Web-Tomcat 测试

### Base
- 要先执行 1 次，第一次没初始化 DispatcherServlet，直接压测会 Failed requests
```shell
curl http://localhost:9001/api/biz/hello2
curl http://localhost:9001/mock/http/baidu
```

### 自己的服务，没有具体的逻辑
- http://localhost:9001/api/biz/hello2

#### Apache-ab 测试
```shell
> ab -n 20000 -c 20 -m GET http://localhost:9001/api/biz/hello2
This is ApacheBench, Version 2.3 <$Revision: 1923142 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 2000 requests
Completed 4000 requests
Completed 6000 requests
Completed 8000 requests
Completed 10000 requests
Completed 12000 requests
Completed 14000 requests
Completed 16000 requests
Completed 18000 requests
Completed 20000 requests
Finished 20000 requests


Server Software:        
Server Hostname:        localhost
Server Port:            9001

Document Path:          /api/biz/hello2
Document Length:        141 bytes

Concurrency Level:      20
Time taken for tests:   2.121 seconds
Complete requests:      20000
Failed requests:        0
Total transferred:      4920000 bytes
HTML transferred:       2820000 bytes
Requests per second:    9429.11 [#/sec] (mean)
Time per request:       2.121 [ms] (mean)
Time per request:       0.106 [ms] (mean, across all concurrent requests)
Transfer rate:          2265.20 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.2      0       1
Processing:     0    2   0.3      2       4
Waiting:        0    2   0.5      2       4
Total:          0    2   0.3      2       4

Percentage of the requests served within a certain time (ms)
  50%      2
  66%      2
  75%      2
  80%      2
  90%      3
  95%      3
  98%      3
  99%      3
 100%      4 (longest request)
```

### Mock req Baidu
- http://localhost:9001/mock/http/baidu

#### Apache-ab 测试 vt
```shell
# 执行 2 次，以第 2 次为准 (方便 JIT 处理)

ab -n 10000 -c 50 -m GET http://localhost:9001/mock/http/baidu

# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

This is ApacheBench, Version 2.3 <$Revision: 1923142 $>

Benchmarking localhost (be patient)
Completed 1000 requests
Completed 2000 requests
Completed 3000 requests
Completed 4000 requests
Completed 5000 requests
Completed 6000 requests
Completed 7000 requests
Completed 8000 requests
Completed 9000 requests
Completed 10000 requests
Finished 10000 requests


Server Software:        
Server Hostname:        localhost
Server Port:            9001

Document Path:          /mock/http/baidu
Document Length:        188 bytes

Concurrency Level:      50
Time taken for tests:   12.720 seconds
Complete requests:      10000
Failed requests:        7582
   (Connect: 0, Receive: 0, Length: 7582, Exceptions: 0)
Total transferred:      2940408 bytes
HTML transferred:       1890408 bytes
Requests per second:    786.16 [#/sec] (mean)
Time per request:       63.601 [ms] (mean)
Time per request:       1.272 [ms] (mean, across all concurrent requests)
Transfer rate:          225.74 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.4      0       4
Processing:    20   63 134.3     33    1725
Waiting:       20   63 134.4     33    1725
Total:         21   63 134.4     33    1725

Percentage of the requests served within a certain time (ms)
  50%     33
  66%     37
  75%     42
  80%     44
  90%     93
  95%    174
  98%    434
  99%    680
 100%   1725 (longest request)
```


#### Apache-ab 测试 vt
```shell
# 执行 2 次，以第 2 次为准 (方便 JIT 处理)

ab -n 10000 -c 50 -m GET http://localhost:9001/mock/http/baidu

# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

This is ApacheBench, Version 2.3 <$Revision: 1923142 $>

Benchmarking localhost (be patient)
Completed 1000 requests
Completed 2000 requests
Completed 3000 requests
Completed 4000 requests
Completed 5000 requests
Completed 6000 requests
Completed 7000 requests
Completed 8000 requests
Completed 9000 requests
Completed 10000 requests
Finished 10000 requests


Server Software:        
Server Hostname:        localhost
Server Port:            9001

Document Path:          /mock/http/baidu
Document Length:        153 bytes

Concurrency Level:      50
Time taken for tests:   12.359 seconds
Complete requests:      10000
Failed requests:        8631
   (Connect: 0, Receive: 0, Length: 8631, Exceptions: 0)
Total transferred:      2591172 bytes
HTML transferred:       1541172 bytes
Requests per second:    809.14 [#/sec] (mean)
Time per request:       61.794 [ms] (mean)
Time per request:       1.236 [ms] (mean, across all concurrent requests)
Transfer rate:          204.75 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.4      0       8
Processing:    21   61  55.4     44     698
Waiting:       21   61  55.4     44     697
Total:         21   61  55.4     44     698

Percentage of the requests served within a certain time (ms)
  50%     44
  66%     47
  75%     51
  80%     58
  90%    108
  95%    162
  98%    272
  99%    322
 100%    698 (longest request)
```