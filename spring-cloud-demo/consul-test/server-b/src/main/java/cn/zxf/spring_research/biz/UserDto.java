package cn.zxf.spring_research.biz;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor( staticName = "of" )
public class UserDto {

    String id;
    String name;
    Date   createDate;

}
