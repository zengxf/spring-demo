package cn.zxf.jpa_transaction.test.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith( SpringRunner.class )
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.NONE )
public class TestUserService {

    @Autowired
    private UserService service;

    @Test
    public void test_createNonTransaction() {
        service.createNonTransaction( "zxf-02" );
    }

    @Test
    public void test_createByRuntimeException() {
        try {
            service.createByRuntimeException( "zxf-bb-1" );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_createByException() {
        try {
            service.createByException( "zxf-bb-0021" );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    // 不能重复提交
    @Test
    public void test_createTransactionalRequired() {
        service.createTransactionalRequired( "zxf-12", "err" );
    }

    // 正常
    @Test
    public void test_createTransactionalSupports() {
        service.createTransactionalSupports( "zxf-22", "err" );
    }

    // 错误；改成 Required 则不能重复提交
    @Test
    public void test_createTransactionalMandatory() {
        service.createTransactionalMandatory( "zxf-32", "err" );
    }

    // 外部正常，内部错误
    @Test
    public void test_createTransactionalRequiresNew() {
        service.createTransactionalRequiresNew( "zxf-42", "err" );
    }

    // 内部正常，外部错误
    @Test
    public void test_createTransactionalRequiresNewError4Ok() {
        service.createTransactionalRequiresNew( "zxf-42", "err", "ok" );
    }

    // 正常
    @Test
    public void test_createTransactionalNotSupported() {
        service.createTransactionalNotSupported( "zxf-52", "err" );
    }

    // 正常
    @Test
    public void test_createTransactionalNever() {
        service.createTransactionalNever( "zxf-62", "err" );
    }

    // 外部正常，内部错误-不支持
    @Test
    public void test_createTransactionalNested() {
        service.createTransactionalNested( "zxf-72", "err" );
    }

    // 外部正常，内部错误-不支持
    @Test
    public void test_createTransactionalNestedOK() {
        service.createTransactionalNested( "zxf-72", "ok" );
    }

    // 外部错误，内部错误-不支持
    @Test
    public void test_createTransactionalNestedError4Ok() {
        service.createTransactionalNested( "zxf-72", "err", "ok" );
    }

}
