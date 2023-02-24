package test.biz;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String name;
    private int age;
    private String email;
    @TableField("createdate")
    private Date createDate;
    @TableField("modifydate")
    private Date modifyDate;
    @TableField(exist = false)
    private String remark;
}
