package test.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * <br/>
 * Created by ZXFeng on 2023/3/2.
 */
@Component
public class UserDao {

    @Autowired
    private JdbcTemplate jdbc;

    public int addMoney(Integer uid, Integer money) {
        String sql = "UPDATE t_user SET money = money + ? WHERE id = ?";
        return jdbc.update(sql, money * 10, uid);
    }

}
