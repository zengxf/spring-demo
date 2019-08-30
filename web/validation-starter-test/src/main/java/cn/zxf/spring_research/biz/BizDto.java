package cn.zxf.spring_research.biz;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class BizDto {

    private Integer age;
    @NotEmpty( message = "不能为空" )
    private String  name;

}
