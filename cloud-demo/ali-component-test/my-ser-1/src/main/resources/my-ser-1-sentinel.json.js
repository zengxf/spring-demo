// sentinel 规则配置
// 参考：https://blog.csdn.net/menglinjie/article/details/105575632
// 数值参考：**.sentinel.**.RuleConstant

// my-ser-1-sentinel-flow
[
  {
    "resource": "ser1-sentinel-test",
    "limitApp": "default",
    "grade": 1,
    "count": 3,
    "strategy": 0,
    "controlBehavior": 0,
    "clusterMode": false
  },
  {
    "resource": "/api/biz/sentinel-test",
    "limitApp": "default",
    "grade": 1,
    "count": 3,
    "strategy": 0,
    "controlBehavior": 0,
    "clusterMode": false
  }
]


// my-ser-1-sentinel-degrade
[
  {
    "resource": "ser1-sentinel-delay-get-user",
    "limitApp": "default",
    "grade": 0,
    "count": 200,
    "slowRatioThreshold": 0.2,
    "minRequestAmount": 5,
    "statIntervalMs": 1000,
    "timeWindow": 10
  },
  {
    "resource": "ser1-sentinel-error-get-user",
    "limitApp": "default",
    "grade": 2,
    "count": 2,
    "minRequestAmount": 5,
    "statIntervalMs": 1000,
    "timeWindow": 10
  }
]