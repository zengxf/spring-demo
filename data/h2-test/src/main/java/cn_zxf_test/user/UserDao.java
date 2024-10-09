package cn_zxf_test.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p/>
 * Created by ZXFeng on 2024/10/9
 */
@Component
@Slf4j
public class UserDao {

    @Autowired
    private JdbcTemplate jdbc;

    public List<User> findList(String keyword) {
        String sql = """
                SELECT
                    * 
                FROM
                    biz_user
                WHERE 
                    mobile LIKE '%{}%'
                    OR 
                    email LIKE '%{}%'
                """;
        sql = sql.replace("{}", keyword);

        log.info("SQL: \n{}\n", sql);

        List<User> list = jdbc.query(sql, BeanPropertyRowMapper.newInstance(User.class));
        return list;
    }

}
