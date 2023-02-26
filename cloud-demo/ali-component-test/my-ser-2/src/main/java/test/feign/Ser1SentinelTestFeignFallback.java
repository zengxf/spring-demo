package test.feign;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <br/>
 * Created by ZXFeng on 2023/2/26.
 */
@Component
public class Ser1SentinelTestFeignFallback implements Ser1SentinelTestFeign {

    @Override
    public String sentinelTest() {
        return "降级....";
    }

    @Override
    public List<String> getArray() {
        return null;
    }

}