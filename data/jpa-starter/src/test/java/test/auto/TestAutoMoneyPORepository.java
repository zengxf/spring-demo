package test.auto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import test.auto.AutoMoneyPO;
import test.auto.AutoMoneyPORepository;

@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.NONE )
@RunWith( SpringRunner.class )
public class TestAutoMoneyPORepository {
    @Autowired
    AutoMoneyPORepository repos;

    @Test
    public void test() {
        // 使用自定义的主键生成策略
        AutoMoneyPO moneyPO = new AutoMoneyPO();
        moneyPO.setId( 20 );
        moneyPO.setName( "test 1" );
        moneyPO.setMoney( 2200L + ( (long) ( Math.random() * 100 ) ) );
        moneyPO.setIsDeleted( (byte) 0x00 );
        AutoMoneyPO res = repos.save( moneyPO );
        System.out.println( "after insert res: " + res );

        moneyPO.setMoney( 3200L + ( (long) ( Math.random() * 100 ) ) );
        res = repos.save( moneyPO );
        System.out.println( "after insert res: " + res );

        moneyPO = new AutoMoneyPO();
        moneyPO.setName( "test 2" );
        moneyPO.setMoney( 2200L + ( (long) ( Math.random() * 100 ) ) );
        moneyPO.setIsDeleted( (byte) 0x00 );
        res = repos.save( moneyPO );
        System.out.println( "after insert res: " + res );
    }

}
