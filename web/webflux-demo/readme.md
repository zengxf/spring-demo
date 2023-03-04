## WebFlux 测试

### URL

- http://localhost:8890/api/test/find-one/22
- http://localhost:8890/api/test/create?userId=22

### Apache-ab 测试

```js
> ab -n 10000 -c 10 -m POST http://localhost:8890/api/test/find-one/22
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
Document Length:        145 bytes

Concurrency Level:      10
Time taken for tests:   2.080 seconds
Complete requests:      10000
Failed requests:        9104
   (Connect: 0, Receive: 0, Length: 9104, Exceptions: 0)
Non-2xx responses:      10000
Total transferred:      2339208 bytes
HTML transferred:       1459208 bytes
Requests per second:    4806.59 [#/sec] (mean)
Time per request:       2.080 [ms] (mean)
Time per request:       0.208 [ms] (mean, across all concurrent requests)
Transfer rate:          1098.01 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.3      0       1
Processing:     0    2   0.9      2       9
Waiting:        0    2   0.7      1       9
Total:          0    2   0.9      2       9
WARNING: The median and mean for the waiting time are not within a normal deviation
        These results are probably not that reliable.

Percentage of the requests served within a certain time (ms)
  50%      2
  66%      2
  75%      3
  80%      3
  90%      3
  95%      4
  98%      4
  99%      4
 100%      9 (longest request)
```

```js
> ab -n 10000 -c 10 -m POST http://localhost:8890/api/test/create?userId=22
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

```js
> ab -n 10000 -c 10 -m POST http://localhost:8890/api/test/create-sleep/s50/50
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
Document Length:        151 bytes

Concurrency Level:      10
Time taken for tests:   2.335 seconds
Complete requests:      10000
Failed requests:        9991
   (Connect: 0, Receive: 0, Length: 9991, Exceptions: 0)
Non-2xx responses:      10000
Total transferred:      2418894 bytes
HTML transferred:       1538894 bytes
Requests per second:    4283.27 [#/sec] (mean)
Time per request:       2.335 [ms] (mean)
Time per request:       0.233 [ms] (mean, across all concurrent requests)
Transfer rate:          1011.79 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.3      0       1
Processing:     0    2   1.6      2     121
Waiting:        0    2   1.5      2     120
Total:          0    2   1.6      2     121

Percentage of the requests served within a certain time (ms)
  50%      2
  66%      2
  75%      3
  80%      3
  90%      3
  95%      4
  98%      4
  99%      5
 100%    121 (longest request)
```

- Sleep 200ms

```js
> ab -n 10000 -c 10 -m POST http://localhost:8890/api/test/create-sleep/s500/500
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

Document Path:          /api/test/create-sleep/s500/500
Document Length:        157 bytes

Concurrency Level:      10
Time taken for tests:   1.698 seconds
Complete requests:      10000
Failed requests:        0
Non-2xx responses:      10000
Total transferred:      2450000 bytes
HTML transferred:       1570000 bytes
Requests per second:    5887.58 [#/sec] (mean)
Time per request:       1.698 [ms] (mean)
Time per request:       0.170 [ms] (mean, across all concurrent requests)
Transfer rate:          1408.65 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.3      0       1
Processing:     0    2   0.7      1       5
Waiting:        0    1   0.6      1       4
Total:          0    2   0.7      2       5
WARNING: The median and mean for the processing time are not within a normal deviation
        These results are probably not that reliable.

Percentage of the requests served within a certain time (ms)
  50%      2
  66%      2
  75%      2
  80%      2
  90%      3
  95%      3
  98%      3
  99%      3
 100%      5 (longest request)
```