// sentinel 规则配置
// 参考：https://blog.csdn.net/menglinjie/article/details/105575632
// 数值参考：**.sentinel.**.RuleConstant
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
  },
  {
    "resource": "test.dubbo.api.DUserService:getUsers(java.lang.Integer)",
    "limitApp": "default",
    "grade": 1,
    "count": 3,
    "strategy": 0,
    "controlBehavior": 0,
    "clusterMode": false
  }
]