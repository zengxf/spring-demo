package test.biz;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.AbstractAppTest;

@Slf4j
public class UserEaoTest extends AbstractAppTest {

    @Autowired
    UserEao eao;

    @Test
    public void test() {
        eao.createIndex();

        String id = "f88";
        User user = eao.findOne(id);
        log.info("id: [{}], user: [{}]", id, user);

        user = User.builder()
                .id(id)
                .name("zxf")
                .age(88)
                .build();
        log.info("save-before user: [{}]", user);
        User po = eao.save(user);
        log.info("po: [{}], save-after: [{}]", po, user);

        String delRes = eao.delete(id);
        log.info("delRes: [{}]", delRes);

        user = eao.findOne(id);
        log.info("id: [{}], delete-after user: [{}]", id, user);
    }

}
