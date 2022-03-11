package test.commodity;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.AbstractApplicationTest5;

import java.util.ArrayList;
import java.util.List;

/**
 * <br/>
 * Created by ZXFeng on 2022/3/11.
 */
@Slf4j
public class CommodityRepositoryTest extends AbstractApplicationTest5 {

    @Autowired
    CommodityRepository repos;

    @Test
    public void save() {
        for (int i = 5; i <= 10; i++) {
            Commodity commodity = new Commodity()
                    // .setId(1)
                    .setName("t-" + i)
                    .setStatus(i % 5)
                    .setTags(new ArrayList<>(List.of(
                            new Commodity.Tag().setKey("k1").setValue("v1-" + i),
                            new Commodity.Tag().setKey("k2").setValue("v2-" + i)
                    )));
            repos.save(commodity);
        }
    }

    @Test
    public void findByStatus() {
        int status = 2;
        List<Commodity> list = repos.findByStatus(status);
        list.forEach(commodity -> log.info("commodity: [{}]", commodity));
    }

    @Test
    public void findByStatusByNative() {
        int status = 2;
        List<Commodity> list = repos.findByStatusByNative(status);
        list.forEach(commodity -> log.info("commodity: [{}]", commodity));
    }

}