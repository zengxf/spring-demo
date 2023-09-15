package test.biz;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("test_user_1")
public class User {

    private Integer id;
    private String name;
    private int age;
    private String email;
    @TableField("create_date")
    private Date createDate;
    @TableField("modify_date")
    private Date modifyDate;
    @TableField(exist = false)
    private String remark;

}
