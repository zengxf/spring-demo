package test.user;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import test.user.User;
import test.user.UserDao;
import test.user.UserRepository;

@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.NONE )
@RunWith( SpringRunner.class )
public class TestUserBatchInsert {

    @Autowired
    private UserRepository repos;
    @Autowired
    private UserDao        dao;

    @Test
    public void repositoryBatchInsert() {
        List<User> list = data();
        repos.saveAll( list );
        repos.deleteAll( list );
    }

    @Test
    public void jdbcBatchInsert() {
        List<User> list = data();
        dao.batchSaveByJdbc( list );
        // repos.deleteAll();
    }

    @Test
    public void jpaBatchInsert() {
        List<User> list = data();
        dao.batchSave( list );
        // repos.deleteAll();
    }

    private List<User> data() {
        List<User> list = Stream.of( 23, 34, 45, 65, 76 )
                .map( i -> new User() //
                        .setName( "zxf-batch-" + i )
                        .setLoginMobile( "166-batch-" + i ) //
                )
                .collect( Collectors.toList() );
        return list;
    }

}
