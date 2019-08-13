package cn.zxf.spring_research.biz;

import java.util.Date;

import lombok.Data;

@Data
public class UserDto {

    String id;
    String name;
    Date   createDate;

}
