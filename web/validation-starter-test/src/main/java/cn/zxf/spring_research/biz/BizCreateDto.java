package cn.zxf.spring_research.biz;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import cn.zxf.spring_research.custom_validation.CheckIntegerInterval;
import lombok.Data;

@Data
@CheckIntegerInterval
public class BizCreateDto {

    @NotEmpty( message = "邮箱不能为空" )
    @Email
    private String  email;

    @NotEmpty( message = "密码不能为空" )
    @Length( min = 4, message = "密码长度不能少于 4 位" )
    private String  password;

    private Integer start;
    private Integer end;

}
