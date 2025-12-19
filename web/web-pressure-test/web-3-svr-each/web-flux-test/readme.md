## WebFlux 测试

### URL

- http://localhost:8890/api/test/find-one/11
- http://localhost:8890/api/test/find-by-id?userId=11

### Apache-ab 测试

- Sleep 0ms (普通返回)
    - 次数试多了，感觉 QPS 还可以

```js
> ab -n 10000 -c 10 -m GET http://localhost:8890/api/test/find-one/11

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
Server Port:            8890

Document Path:          /api/test/find-one/11
Document Length:        59 bytes

Concurrency Level:      10
Time taken for tests:   1.846 seconds
Complete requests:      10000
Failed requests:        0
Total transferred:      1300000 bytes
HTML transferred:       590000 bytes
Requests per second:    5416.34 [#/sec] (mean)
Time per request:       1.846 [ms] (mean)
Time per request:       0.185 [ms] (mean, across all concurrent requests)
Transfer rate:          687.62 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.3      0       1
Processing:     0    2   0.8      2      12
Waiting:        0    1   0.7      1      11
Total:          0    2   0.8      2      12

Percentage of the requests served within a certain time (ms)
  50%      2
  66%      2
  75%      2
  80%      2
  90%      3
  95%      3
  98%      4
  99%      4
 100%     12 (longest request)
```

- Sleep 0ms (Flux 返回)

```js
> ab -n 10000 -c 10 -m GET http://localhost:8890/api/test/find-by-id?userId=22

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
Server Port:            8890

Document Path:          /api/test/find-by-id?userId=22
Document Length:        53 bytes

Concurrency Level:      10
Time taken for tests:   3.280 seconds
Complete requests:      10000
Failed requests:        0
Total transferred:      1510000 bytes
HTML transferred:       530000 bytes
Requests per second:    3048.33 [#/sec] (mean)
Time per request:       3.280 [ms] (mean)
Time per request:       0.328 [ms] (mean, across all concurrent requests)
Transfer rate:          449.51 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.3      0       1
Processing:     0    3  38.9      2    1233
Waiting:        0    3  36.9      1    1233
Total:          0    3  38.9      2    1233

Percentage of the requests served within a certain time (ms)
  50%      2
  66%      2
  75%      2
  80%      3
  90%      3
  95%      4
  98%      4
  99%      5
 100%   1233 (longest request)
```

- Sleep 50ms
    - QPS 会下降

```js
> ab -n 2000 -c 10 -m GET http://localhost:8890/api/test/find-sleep/s50/50

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
Server Port:            8890

Document Path:          /api/test/find-sleep/s50/50
Document Length:        54 bytes

Concurrency Level:      10
Time taken for tests:   12.635 seconds
Complete requests:      2000
Failed requests:        0
Total transferred:      304000 bytes
HTML transferred:       108000 bytes
Requests per second:    158.29 [#/sec] (mean)
Time per request:       63.174 [ms] (mean)
Time per request:       6.317 [ms] (mean, across all concurrent requests)
Transfer rate:          23.50 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.3      0       1
Processing:    58   62   5.1     62     127
Waiting:       57   61   5.2     61     127
Total:         58   63   5.1     62     127

Percentage of the requests served within a certain time (ms)
  50%     62
  66%     63
  75%     63
  80%     63
  90%     64
  95%     64
  98%     65
  99%     65
 100%    127 (longest request)
```

- Sleep 200ms

```js
> ab -n 1000 -c 10 -m GET http://localhost:8890/api/test/find-sleep/s200/200

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
Server Port:            8890

Document Path:          /api/test/find-sleep/s200/200
Document Length:        55 bytes

Concurrency Level:      10
Time taken for tests:   21.953 seconds
Complete requests:      1000
Failed requests:        0
Total transferred:      153000 bytes
HTML transferred:       55000 bytes
Requests per second:    45.55 [#/sec] (mean)
Time per request:       219.534 [ms] (mean)
Time per request:       21.953 [ms] (mean, across all concurrent requests)
Transfer rate:          6.81 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.3      0       1
Processing:   200  214  42.8    204     824
Waiting:      200  214  42.8    203     824
Total:        201  214  42.8    204     824

Percentage of the requests served within a certain time (ms)
  50%    204
  66%    213
  75%    215
  80%    216
  90%    216
  95%    217
  98%    406
  99%    418
 100%    824 (longest request)
```