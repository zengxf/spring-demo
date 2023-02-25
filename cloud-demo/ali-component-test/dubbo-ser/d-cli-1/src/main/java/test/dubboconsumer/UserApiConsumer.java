package test.dubboconsumer;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;
import test.dubbo.api.DUserDTO;
import test.dubbo.api.DUserService;

import java.util.List;

/**
 * <br/>
 * Created by ZXFeng on 2023/2/25.
 */
@Component
public class UserApiConsumer {

    @DubboReference
    private DUserService service;

    public DUserDTO getUser(String id) {
        return service.getUser(id);
    }

    public List<DUserDTO> getUsers(Integer pageSize) {
        return service.getUsers(pageSize);
    }

}
