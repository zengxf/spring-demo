package cn.zxf.jpa_starter.test.user;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.NONE )
@RunWith( SpringRunner.class )
public class TestUserDao {

    @Autowired
    UserDao dao;

    @Test
    public void findOne() {
        List<User> list = dao.findList( "f" );
        list.forEach( user -> log.info( "\n\n user: {}\n", user ) );

    }

}
