package test.user;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <br/>
 * Created by ZXFeng on  2021/9/22.
 */
@Data
@Accessors(chain = true)
public class UserDto {

    private String id;
    private String name;
    private Integer age;
    private String remark;
    private String status;

}
