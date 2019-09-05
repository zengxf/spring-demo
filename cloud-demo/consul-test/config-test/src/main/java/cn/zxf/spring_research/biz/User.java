package cn.zxf.spring_research.biz;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document( "test_user" )
public class User {

    @Id
    private String  id;
    private String  name;
    private Integer age;

}
