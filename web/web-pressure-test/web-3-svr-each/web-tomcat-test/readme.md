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
Server Port:            8866

Document Path:          /api/test/find-one/22
Document Length:        59 bytes

Concurrency Level:      10
Time taken for tests:   1.357 seconds
Complete requests:      10000
Failed requests:        0
Total transferred:      1640000 bytes
HTML transferred:       590000 bytes
Requests per second:    7367.46 [#/sec] (mean)
Time per request:       1.357 [ms] (mean)
Time per request:       0.136 [ms] (mean, across all concurrent requests)
Transfer rate:          1179.94 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.3      0       1
Processing:     0    1   0.7      1      34
Waiting:        0    1   0.7      1      34
Total:          0    1   0.8      1      34

Percentage of the requests served within a certain time (ms)
  50%      1
  66%      1
  75%      2
  80%      2
  90%      2
  95%      2
  98%      3
  99%      3
 100%     34 (longest request)
```

- Sleep 50ms
    - QPS 下降明显

```js
> ab -n 2000 -c 10 -m GET http://localhost:8866/api/test/find-sleep/s50/50

This is ApacheBench, Version 2.3 <$Revision: 1903618 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 200 requests
Completed 400 requests
Completed 600 requests
Completed 800 requests
Completed 1000 requests
Completed 1200 requests
Completed 1400 requests
Completed 1600 requests
Completed 1800 requests
Completed 2000 requests
Finished 2000 requests


Server Software:
Server Hostname:        localhost
Server Port:            8866

Document Path:          /api/test/find-sleep/s50/50
Document Length:        36 bytes

Concurrency Level:      10
Time taken for tests:   12.527 seconds
Complete requests:      2000
Failed requests:        0
Total transferred:      282000 bytes
HTML transferred:       72000 bytes
Requests per second:    159.65 [#/sec] (mean)
Time per request:       62.636 [ms] (mean)
Time per request:       6.264 [ms] (mean, across all concurrent requests)
Transfer rate:          21.98 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.3      0       1
Processing:    58   62   1.2     62      66
Waiting:       58   61   1.2     61      65
Total:         58   62   1.1     62      66

Percentage of the requests served within a certain time (ms)
  50%     62
  66%     63
  75%     63
  80%     63
  90%     64
  95%     64
  98%     65
  99%     65
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
Completed 200 requests
Completed 300 requests
Completed 400 requests
Completed 500 requests
Completed 600 requests
Completed 700 requests
Completed 800 requests
Completed 900 requests
Completed 1000 requests
Finished 1000 requests


Server Software:
Server Hostname:        localhost
Server Port:            8866

Document Path:          /api/test/find-sleep/s200/200
Document Length:        37 bytes

Concurrency Level:      10
Time taken for tests:   20.859 seconds
Complete requests:      1000
Failed requests:        0
Total transferred:      142000 bytes
HTML transferred:       37000 bytes
Requests per second:    47.94 [#/sec] (mean)
Time per request:       208.592 [ms] (mean)
Time per request:       20.859 [ms] (mean, across all concurrent requests)
Transfer rate:          6.65 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.3      0       1
Processing:   200  206   5.6    203     217
Waiting:      200  206   5.6    203     217
Total:        200  206   5.6    203     217

Percentage of the requests served within a certain time (ms)
  50%    203
  66%    205
  75%    214
  80%    215
  90%    216
  95%    216
  98%    216
  99%    217
 100%    217 (longest request)
```