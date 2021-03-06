package test.user;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import test.user.User;
import test.user.UserDao;
import test.user.UserRepository;

@Slf4j
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.NONE )
@RunWith( SpringRunner.class )
public class TestUserRepository {

    @Autowired
    private UserRepository repos;
    @Autowired
    private UserDao        dao;

    @Test
    public void find_repos() {
        log.info( "repos: {}", repos );
    }

    @Test
    public void findCURD() {
        String name = "zxf-aa";
        User user = new User().setName( name )
                .setLoginMobile( "bb" );
        repos.save( user );
        log.info( "inserted-user: {}", user );
        Integer id = user.id();

        user = repos.findById( id )
                .get()
                .setLoginMobile( "bb-cc" )
                .setLastLoginDate( new Date() );
        repos.save( user );

        user = repos.findById( id )
                .get();
        log.info( "find-list-user: {}", user );
    }

    @Test
    public void findStatus() {
        User user = new User().setName( "zxf" )
                .setStatus( 1 )
                .setLoginMobile( "888" );
        repos.save( user );
        log.info( "inserted-user: {}", user );
        Integer id = user.id();

        Integer status = repos.findStatus( id );
        log.info( "user-status: {}", status );
    }

    @Test
    public void replace() {
        User user = new User() //
                .id( 100 )
                .setName( "zxf" )
                .setStatus( 1 )
                .setLoginMobile( "888" );
        log.info( "replace-user: {}", user );
        repos.replace( user );
    }

    @Test
    public void replaceParams() {
        repos.replace( 101, "zxf", "999", 1 );
    }

    @Test
    public void insert() {
        repos.insert( 102, "zxf", "999", 1 );
    }

    @Test
    public void findStatusAndId() {
        User user = new User().setName( "zxf" )
                .setStatus( 1 )
                .setLoginMobile( "888" );
        repos.save( user );
        log.info( "inserted-user: {}", user );
        Integer id = user.id();

        Object statusObj = repos.findStatusAndId( id );
        log.info( "user-status: {}", statusObj );
    }

    @Test
    public void findOneOnlyStatus() {
        User user = new User().setName( "zxf" )
                .setStatus( 1 )
                .setLoginMobile( "888" );
        repos.save( user );
        log.info( "inserted-user: {}", user );
        Integer id = user.id();

        // user = dao.findOneOnlyColumnA( id );
        // user = dao.findOneOnlyColumnB( id );
        log.info( "user-status: {}", user );

        Object userVo = dao.findOneOnlyColumn( id );
        log.info( "user-status: {}", userVo );
    }

}
