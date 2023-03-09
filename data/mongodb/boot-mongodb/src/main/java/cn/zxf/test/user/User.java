package cn.zxf.test.user;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by summer on 2017/5/5.
 */
@Data
@Document(collection = "t_user")
public class User {

    @Id
    private String id;
    private Long uid;
    private String userName;
    private String passWord;

}
