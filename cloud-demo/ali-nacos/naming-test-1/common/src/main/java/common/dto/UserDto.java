package common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <br/>
 * Created by ZXFeng on 2024/1/3.
 */
@Data
@AllArgsConstructor(staticName = "of")
public class UserDto {

    private String name;
    private Integer age;

}
