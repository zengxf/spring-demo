package cn.zxf.mybatis_plugin_test.user;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

@Mapper
// @Repository // 可以不用
public interface UserMapper {

    @Insert("INSERT INTO user1 (name, age, login_mobile, status) VALUES(#{name}, #{age}, #{loginMobile}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Select("SELECT status FROM user1 WHERE id = #{id}")
    Integer findStatus(int id);

    @Select("SELECT id, name, age, login_mobile loginMobile FROM user1 WHERE id = #{id}")
    User findById(int id);

    @Select("SELECT id, name, age, login_mobile loginMobile FROM user1 WHERE name = #{key} OR login_mobile = #{key}")
    List<User> findListByKey(String key);

    @Update("UPDATE user1 SET name=#{name}, age=#{age}, login_mobile=#{loginMobile} WHERE id = #{id}")
    void update(User user);

    @Update("REPLACE INTO user1 (id, name, age, login_mobile, status) VALUE(#{id}, #{name}, #{age}, #{loginMobile}, #{status})")
    void replace(User user);

    @UpdateProvider(type = SqlBuilder.class, method = "buildUpdate")
    void updateFieldNullable(User user);

    static class SqlBuilder {
        public static String buildUpdate(User user) {
            return new SQL() {
                {
                    UPDATE("user1");
                    if (user.name() != null)
                        SET("name = #{name}");
                    if (user.age() != null)
                        SET("age = #{age}");
                    WHERE("id = #{id}");
                }
            }.toString();
        }
    }
}
