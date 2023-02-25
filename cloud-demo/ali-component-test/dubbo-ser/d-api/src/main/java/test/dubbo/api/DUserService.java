package test.dubbo.api;

import java.util.List;

/**
 * <br/>
 * Created by ZXFeng on 2023/2/25.
 */
public interface DUserService {

    DUserDTO getUser(String id);

    List<DUserDTO> getUsers(Integer pageSize);

}
