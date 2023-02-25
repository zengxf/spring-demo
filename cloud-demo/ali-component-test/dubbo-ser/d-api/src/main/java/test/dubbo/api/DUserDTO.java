package test.dubbo.api;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <br/>
 * Created by ZXFeng on 2023/2/25.
 */
@Data
@Accessors(chain = true)
public class DUserDTO implements Serializable {

    private String name;
    private Integer age;
    private String sign;

}
