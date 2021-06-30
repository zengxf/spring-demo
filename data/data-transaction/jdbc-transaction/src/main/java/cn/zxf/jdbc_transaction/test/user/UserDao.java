package cn.zxf.jdbc_transaction.test.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private long index = 100;

    public User selectOne() {
        String sql = "SELECT * FROM user LIMIT 1";
        List<User> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class));
        return list.isEmpty() ? null : list.get(0);
    }

    public User save(User user) {
        jdbcTemplate.update("INSERT INTO user(id, name, age) VALUES(?, ?, ?)", index, user.getName(), user.getAge());
        user.setId(index);
        index++;
        return user;
    }

}
