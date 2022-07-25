package test.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <br/>
 * Created by ZXFeng on 2022/7/25.
 */
@Slf4j
@Repository
public class Test01Dao {

    @Autowired
    private JdbcTemplate jdbc;

    public List<User01> findAll() {
        String sql = "SELECT * FROM test";
        return jdbc.query(sql, BeanPropertyRowMapper.newInstance(User01.class));
    }

}
