## WebFlux 测试

### URL

- http://localhost:8890/api/test/find-one/22
- http://localhost:8890/api/test/create?userId=22

### Apache-ab 测试

- Sleep 0ms (普通返回)
    - 次数试多了，感觉 QPS 还可以

```js
> ab -n 10000 -c 10 -m GET http://localhost:8890/api/test/find-one/22
This is ApacheBench, Version 2.3 <$Revision: 1903618 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 1000 requests
Completed 10000 requests
Finished 10000 requests


Server Software:
Server Hostname:        localhost
Server Port:            8890

Document Path:          /api/test/find-one/22
Document Length:        59 bytes

Concurrency Level:      10
Time taken for tests:   1.121 seconds
Complete requests:      10000
Failed requests:        0
Total transferred:      1300000 bytes
HTML transferred:       590000 bytes
Requests per second:    8920.20 [#/sec] (mean)
Time per request:       1.121 [ms] (mean)
Time per request:       0.112 [ms] (mean, across all concurrent requests)
Transfer rate:          1132.45 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.2      0       1
Processing:     0    1   1.0      1      25
Waiting:        0    1   1.0      1      25
Total:          0    1   1.0      1      25

Percentage of the requests served within a certain time (ms)
  50%      1
  66%      1
  75%      1
  80%      1
  90%      2
  95%      2
  98%      3
  99%      4
 100%     25 (longest request)
```

- Sleep 0ms (Flux 返回)

```js
> ab -n 10000 -c 10 -m GET http://localhost:8890/api/test/create?userId=22
This is ApacheBench, Version 2.3 <$Revision: 1903618 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 1000 requests
Completed 10000 requests
Finished 10000 requests


Server Software:
Server Hostname:        localhost
Server Port:            8890

Document Path:          /api/test/create?userId=22
Document Length:        142 bytes

Concurrency Level:      10
Time taken for tests:   1.819 seconds
Complete requests:      10000
Failed requests:        0
Non-2xx responses:      10000
Total transferred:      2300000 bytes
HTML transferred:       1420000 bytes
Requests per second:    5497.15 [#/sec] (mean)
Time per request:       1.819 [ms] (mean)
Time per request:       0.182 [ms] (mean, across all concurrent requests)
Transfer rate:          1234.71 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.3      0       1
Processing:     0    2   0.8      2       5
Waiting:        0    1   0.6      1       5
Total:          0    2   0.8      2       5

Percentage of the requests served within a certain time (ms)
  50%      2
  66%      2
  75%      2
  80%      2
  90%      3
  95%      3
  98%      3
  99%      4
 100%      5 (longest request)
```

- Sleep 50ms
    - QPS 会下降

```js
> ab -n 10000 -c 10 -m GET http://localhost:8890/api/test/create-sleep/s50/50
This is ApacheBench, Version 2.3 <$Revision: 1903618 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 1000 requests
Completed 10000 requests
Finished 10000 requests


Server Software:
Server Hostname:        localhost
Server Port:            8890

Document Path:          /api/test/create-sleep/s50/50
Document Length:        54 bytes

Concurrency Level:      10
Time taken for tests:   62.604 seconds
Complete requests:      10000
Failed requests:        0
Total transferred:      1520000 bytes
HTML transferred:       540000 bytes
Requests per second:    159.73 [#/sec] (mean)
Time per request:       62.604 [ms] (mean)
Time per request:       6.260 [ms] (mean, across all concurrent requests)
Transfer rate:          23.71 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.3      0       1
Processing:    54   62   2.4     62     258
Waiting:       53   61   2.3     61     240
Total:         54   62   2.4     62     258

Percentage of the requests served within a certain time (ms)
  50%     62
  66%     63
  75%     63
  80%     63
  90%     64
  95%     64
  98%     65
  99%     66
 100%    258 (longest request)
```

- Sleep 200ms

```js
> ab -n 1000 -c 10 -m GET http://localhost:8890/api/test/create-sleep/s200/200
This is ApacheBench, Version 2.3 <$Revision: 1903618 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 100 requests
Completed 1000 requests
Finished 1000 requests


Server Software:
Server Hostname:        localhost
Server Port:            8890

Document Path:          /api/test/create-sleep/s200/200
Document Length:        55 bytes

Concurrency Level:      10
Time taken for tests:   21.152 seconds
Complete requests:      1000
Failed requests:        0
Total transferred:      153000 bytes
HTML transferred:       55000 bytes
Requests per second:    47.28 [#/sec] (mean)
Time per request:       211.519 [ms] (mean)
Time per request:       21.152 [ms] (mean, across all concurrent requests)
Transfer rate:          7.06 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.4      0       1
Processing:   200  209   9.7    206     430
Waiting:      200  208   9.7    204     429
Total:        201  210   9.7    206     430

Percentage of the requests served within a certain time (ms)
  50%    206
  66%    215
  75%    216
  80%    216
  90%    217
  95%    219
  98%    220
  99%    231
 100%    430 (longest request)
```