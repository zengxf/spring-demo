## Web-Tomcat 测试

### Base
- 要先执行 1 次，第一次没初始化 DispatcherServlet，直接压测会 Failed requests
```shell
curl http://localhost:9001/api/biz/hello2
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

#### Apache-ab 测试
```shell
> ab -n 20000 -c 50 -m GET http://localhost:9001/mock/http/baidu
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

Document Path:          /mock/http/baidu
Document Length:        2641 bytes

Concurrency Level:      50
Time taken for tests:   19.000 seconds
Complete requests:      20000
Failed requests:        9230
   (Connect: 0, Receive: 0, Length: 9230, Exceptions: 0)
Total transferred:      54927168 bytes
HTML transferred:       52827168 bytes
Requests per second:    1052.63 [#/sec] (mean)
Time per request:       47.500 [ms] (mean)
Time per request:       0.950 [ms] (mean, across all concurrent requests)
Transfer rate:          2823.15 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.4      0       7
Processing:    19   47  86.9     28    1143
Waiting:       19   47  86.9     28    1143
Total:         19   47  86.9     28    1143

Percentage of the requests served within a certain time (ms)
  50%     28
  66%     30
  75%     31
  80%     33
  90%     51
  95%    134
  98%    379
  99%    526
 100%   1143 (longest request)
```
