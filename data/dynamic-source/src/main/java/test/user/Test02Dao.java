package test.user;

import com.baomidou.dynamic.datasource.annotation.DS;
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
public class Test02Dao {

    @Autowired
    private JdbcTemplate jdbc;

    @DS("test02")
    public List<User02> findAll() {
        String sql = "SELECT * FROM test_user";
        return jdbc.query(sql, BeanPropertyRowMapper.newInstance(User02.class));
    }

}
