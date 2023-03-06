## Web-Tomcat 测试

### URL

- http://localhost:8866/api/test/find-one/22
- http://localhost:8866/api/test/find-sleep/s50/50

### Apache-ab 测试

- Sleep 0ms
    - QPS 反而更高

```js
> ab -n 10000 -c 10 -m GET http://localhost:8866/api/test/find-one/22
This is ApacheBench, Version 2.3 <$Revision: 1903618 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 1000 requests
Completed 10000 requests
Finished 10000 requests


Server Software:
Server Hostname:        localhost
Server Port:            8866

Document Path:          /api/test/find-one/22
Document Length:        59 bytes

Concurrency Level:      10
Time taken for tests:   1.398 seconds
Complete requests:      10000
Failed requests:        0
Total transferred:      1640000 bytes
HTML transferred:       590000 bytes
Requests per second:    7155.39 [#/sec] (mean)
Time per request:       1.398 [ms] (mean)
Time per request:       0.140 [ms] (mean, across all concurrent requests)
Transfer rate:          1145.98 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.3      0       1
Processing:     0    1   0.7      1       7
Waiting:        0    1   0.6      1       7
Total:          0    1   0.7      1       7

Percentage of the requests served within a certain time (ms)
  50%      1
  66%      1
  75%      2
  80%      2
  90%      2
  95%      3
  98%      3
  99%      3
 100%      7 (longest request)
```

- Sleep 50ms
    - QPS 下降明显

```js
> ab -n 2000 -c 10 -m GET http://localhost:8866/api/test/find-sleep/s50/50
This is ApacheBench, Version 2.3 <$Revision: 1903618 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 1000 requests
Completed 10000 requests
Finished 10000 requests


Server Software:
Server Hostname:        localhost
Server Port:            8866

Document Path:          /api/test/find-sleep/s50/50
Document Length:        36 bytes

Concurrency Level:      10
Time taken for tests:   62.205 seconds
Complete requests:      10000
Failed requests:        0
Total transferred:      1410000 bytes
HTML transferred:       360000 bytes
Requests per second:    160.76 [#/sec] (mean)
Time per request:       62.205 [ms] (mean)
Time per request:       6.221 [ms] (mean, across all concurrent requests)
Transfer rate:          22.14 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.2      0       1
Processing:    53   62   1.0     62      66
Waiting:       52   62   0.9     62      65
Total:         53   62   0.9     62      66

Percentage of the requests served within a certain time (ms)
  50%     62
  66%     63
  75%     63
  80%     63
  90%     63
  95%     64
  98%     64
  99%     64
 100%     66 (longest request)
```

- Sleep 200ms
    - QPS 下降非常明显

```js
> ab -n 1000 -c 10 -m GET http://localhost:8866/api/test/find-sleep/s200/200
This is ApacheBench, Version 2.3 <$Revision: 1903618 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 100 requests
Completed 1000 requests
Finished 1000 requests


Server Software:
Server Hostname:        localhost
Server Port:            8866

Document Path:          /api/test/find-sleep/s200/200
Document Length:        37 bytes

Concurrency Level:      10
Time taken for tests:   20.963 seconds
Complete requests:      1000
Failed requests:        0
Total transferred:      142000 bytes
HTML transferred:       37000 bytes
Requests per second:    47.70 [#/sec] (mean)
Time per request:       209.633 [ms] (mean)
Time per request:       20.963 [ms] (mean, across all concurrent requests)
Transfer rate:          6.61 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.3      0       1
Processing:   200  207   6.4    203     218
Waiting:      200  207   6.4    203     217
Total:        200  207   6.4    203     218

Percentage of the requests served within a certain time (ms)
  50%    203
  66%    215
  75%    215
  80%    216
  90%    217
  95%    217
  98%    217
  99%    217
 100%    218 (longest request)
```