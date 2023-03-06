## Web-Undertow 测试

### URL

- http://localhost:8876/api/test/find-one/33
- http://localhost:8876/api/test/find-sleep/s50/50

### Apache-ab 测试

- Sleep 0ms
    - QPS 反而更高

```js
> ab -n 10000 -c 10 -m GET http://localhost:8876/api/test/find-one/33

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
Server Port:            8876

Document Path:          /api/test/find-one/33
Document Length:        59 bytes

Concurrency Level:      10
Time taken for tests:   1.561 seconds
Complete requests:      10000
Failed requests:        0
Total transferred:      1660000 bytes
HTML transferred:       590000 bytes
Requests per second:    6405.21 [#/sec] (mean)
Time per request:       1.561 [ms] (mean)
Time per request:       0.156 [ms] (mean, across all concurrent requests)
Transfer rate:          1038.34 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.3      0       1
Processing:     0    1   2.1      1      97
Waiting:        0    1   1.9      1      97
Total:          0    2   2.1      1      97

Percentage of the requests served within a certain time (ms)
  50%      1
  66%      2
  75%      2
  80%      2
  90%      2
  95%      3
  98%      4
  99%      5
 100%     97 (longest request)
```

- Sleep 50ms
    - QPS 下降明显

```js
> ab -n 2000 -c 10 -m GET http://localhost:8876/api/test/find-sleep/s50/50

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
Server Port:            8876

Document Path:          /api/test/find-sleep/s50/50
Document Length:        36 bytes

Concurrency Level:      10
Time taken for tests:   12.491 seconds
Complete requests:      2000
Failed requests:        0
Total transferred:      286000 bytes
HTML transferred:       72000 bytes
Requests per second:    160.12 [#/sec] (mean)
Time per request:       62.454 [ms] (mean)
Time per request:       6.245 [ms] (mean, across all concurrent requests)
Transfer rate:          22.36 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.3      0       1
Processing:    51   62   1.1     62      77
Waiting:       51   62   1.1     62      77
Total:         52   62   1.1     62      77

Percentage of the requests served within a certain time (ms)
  50%     62
  66%     63
  75%     63
  80%     63
  90%     63
  95%     64
  98%     64
  99%     65
 100%     77 (longest request)
```

- Sleep 200ms
    - QPS 下降非常明显

```js
> ab -n 1000 -c 10 -m GET http://localhost:8876/api/test/find-sleep/s200/200

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
Server Port:            8876

Document Path:          /api/test/find-sleep/s200/200
Document Length:        37 bytes

Concurrency Level:      10
Time taken for tests:   20.835 seconds
Complete requests:      1000
Failed requests:        0
Total transferred:      144000 bytes
HTML transferred:       37000 bytes
Requests per second:    48.00 [#/sec] (mean)
Time per request:       208.354 [ms] (mean)
Time per request:       20.835 [ms] (mean, across all concurrent requests)
Transfer rate:          6.75 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.3      0       1
Processing:   200  206   5.5    203     217
Waiting:      200  205   5.4    203     217
Total:        200  206   5.5    203     217

Percentage of the requests served within a certain time (ms)
  50%    203
  66%    204
  75%    206
  80%    214
  90%    216
  95%    216
  98%    216
  99%    217
 100%    217 (longest request)
```