// sentinel 规则配置
// gateway-sentinel-flow

[
  {
    "resource": "my-ser-1-route-1",
    "limitApp": "default",
    "grade": 1,
    "interval": 10,
    "intervalUnit": 0,
    "count": 3,
    "strategy": 0,
    "controlBehavior": 0,
    "resourceMode": 0
  },
  {
    "resource": "ReactiveCompositeDiscoveryClient_my-ser-1",
    "limitApp": "default",
    "grade": 1,
    "interval": 10,
    "intervalUnit": 0,
    "count": 2,
    "strategy": 0,
    "controlBehavior": 0,
    "resourceMode": 0
  }
]