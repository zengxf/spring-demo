package test.lambda;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import test.biz.User;

/**
 * <br>
 * Created by ZXFeng on 2023/9/15
 */
@Slf4j
public class TestSFun {

    User user = new User();

    @Test
    public void test111111111111111() {
        SFunction<User, ?> fun = User::getAge;
        fun.apply(user);
        log.info("fun: [{}]\n", fun);
    }


}
