# DataWay 测试
- 参考： https://my.oschina.net/ta8210/blog/3234639
- 总的来说，比较方便
- 建表 SQL：`table.sql`，API 数据 SQL：`data.sql`

## 说明
- 引用 `hasor-all`，映射不了 UI
- 访问： http://127.0.0.1:9066/interface-ui/#/
  - def: `admin/admin`，参考：`AdminUiAuthorization`
- 要先**冒烟**，然后才能**发布**

## API
- GET
  - `SELECT * FROM user;`
    - Parameters: `{ }`
  - `SELECT * FROM user WHERE id = #{id};`
    - Parameters: `{"id": 2}`
    - `?` 后面加参数
- POST 
  - `INSERT INTO user (`name`, age, email) VALUES (#{name}, #{age}, #{email});`
  - Parameters(Json body)
```JSON
{
    "name":"test-1.fa",
    "age": 32,
    "email": "ttt@sina.cn"
}
```
- DELETE
  - `DELETE FROM user WHERE id = #{id};`
  - Parameters(Json body): `{ "id": 6 }`