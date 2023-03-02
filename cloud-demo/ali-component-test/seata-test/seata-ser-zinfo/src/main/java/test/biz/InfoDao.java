package test.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * <br/>
 * Created by ZXFeng on 2023/3/2.
 */
@Component
public class InfoDao {

    @Autowired
    private JdbcTemplate jdbc;

    public int logInfo(Integer uid, Integer money) {
        String sql = "INSERT INTO t_info(uid, money, create_time) VALUES(?, ?, NOW(3))";
        return jdbc.update(sql, uid, money);
    }

}
