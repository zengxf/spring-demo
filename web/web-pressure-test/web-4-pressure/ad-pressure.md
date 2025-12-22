## Web-Tomcat 测试

### Base
- 要先执行 1 次，第一次没初始化 DispatcherServlet，直接压测会 Failed requests
```shell
curl http://localhost:9001/api/biz/hello2
curl http://localhost:9001/mock/http/baidu
```


---
### 自己的服务，没有具体的逻辑
- http://localhost:9001/api/biz/hello2

#### Apache-ab 测试 vt & Tomcat
```shell
# 执行 2 次，以第 2 次为准 (方便 JIT 处理)

ab -n 20000 -c 50 -m GET http://localhost:9001/api/biz/hello2

# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

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
Document Length:        132 bytes

Concurrency Level:      50
Time taken for tests:   2.203 seconds
Complete requests:      20000
Failed requests:        3392
   (Connect: 0, Receive: 0, Length: 3392, Exceptions: 0)
Total transferred:      4401272 bytes
HTML transferred:       2641272 bytes
Requests per second:    9077.11 [#/sec] (mean)
Time per request:       5.508 [ms] (mean)
Time per request:       0.110 [ms] (mean, across all concurrent requests)
Transfer rate:          1950.72 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.3      0       1
Processing:     1    5   0.5      5      12
Waiting:        0    4   0.9      4      11
Total:          1    5   0.5      5      12

Percentage of the requests served within a certain time (ms)
  50%      5
  66%      6
  75%      6
  80%      6
  90%      6
  95%      6
  98%      6
  99%      6
 100%     12 (longest request)
```

#### Apache-ab 测试 vt & Jetty
```shell
# 执行 2 次，以第 2 次为准 (方便 JIT 处理)

ab -n 20000 -c 50 -m GET http://localhost:9001/api/biz/hello2

# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

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
Document Length:        132 bytes

Concurrency Level:      50
Time taken for tests:   2.191 seconds
Complete requests:      20000
Failed requests:        2516
   (Connect: 0, Receive: 0, Length: 2516, Exceptions: 0)
Total transferred:      4397484 bytes
HTML transferred:       2637484 bytes
Requests per second:    9129.58 [#/sec] (mean)
Time per request:       5.477 [ms] (mean)
Time per request:       0.110 [ms] (mean, across all concurrent requests)
Transfer rate:          1960.31 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.3      0       1
Processing:     1    5   0.6      5      15
Waiting:        0    4   1.0      4      14
Total:          1    5   0.6      5      15

Percentage of the requests served within a certain time (ms)
  50%      5
  66%      6
  75%      6
  80%      6
  90%      6
  95%      6
  98%      6
  99%      7
 100%     15 (longest request)
```


---
### Mock req Baidu
- http://localhost:9001/mock/http/baidu

#### Apache-ab 测试 vt & Tomcat
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


#### Apache-ab 测试 vt & Tomcat
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


#### Apache-ab 测试 pt & Jetty
```shell
# 执行 2 次，以第 2 次为准 (方便 JIT 处理)

ab -n 10000 -c 50 -m GET http://localhost:9001/mock/http/baidu

# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

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
Document Length:        148 bytes

Concurrency Level:      50
Time taken for tests:   9.615 seconds
Complete requests:      10000
Failed requests:        7230
   (Connect: 0, Receive: 0, Length: 7230, Exceptions: 0)
Total transferred:      2378304 bytes
HTML transferred:       1498304 bytes
Requests per second:    1040.07 [#/sec] (mean)
Time per request:       48.074 [ms] (mean)
Time per request:       0.961 [ms] (mean, across all concurrent requests)
Transfer rate:          241.56 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.3      0       3
Processing:    19   47  57.5     31     519
Waiting:       19   47  57.5     31     519
Total:         20   48  57.5     31     519

Percentage of the requests served within a certain time (ms)
  50%     31
  66%     33
  75%     36
  80%     38
  90%     71
  95%    151
  98%    282
  99%    346
 100%    519 (longest request)
```


#### Apache-ab 测试 vt & Jetty
```shell
# 执行 2 次，以第 2 次为准 (方便 JIT 处理)

ab -n 10000 -c 50 -m GET http://localhost:9001/mock/http/baidu

# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

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
Document Length:        179 bytes

Concurrency Level:      50
Time taken for tests:   14.090 seconds
Complete requests:      10000
Failed requests:        7797
   (Connect: 0, Receive: 0, Length: 7797, Exceptions: 0)
Total transferred:      2681136 bytes
HTML transferred:       1801136 bytes
Requests per second:    709.74 [#/sec] (mean)
Time per request:       70.448 [ms] (mean)
Time per request:       1.409 [ms] (mean, across all concurrent requests)
Transfer rate:          185.83 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.4      0       2
Processing:    20   70 130.9     33    2121
Waiting:       20   70 130.9     33    2120
Total:         20   70 130.9     34    2121

Percentage of the requests served within a certain time (ms)
  50%     34
  66%     38
  75%     44
  80%     55
  90%    115
  95%    293
  98%    542
  99%    658
 100%   2121 (longest request)
```