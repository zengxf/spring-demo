package test.dubboconsumer;

import org.apache.dubbo.config.annotation.DubboService;
import test.dubbo.api.TempService;

/**
 * <br/>
 * Created by ZXFeng on 2023/2/26.
 */
@DubboService(timeout = 3000)
public class TempServiceImpl implements TempService {

    @Override
    public String name() {
        return "OK";
    }

}
