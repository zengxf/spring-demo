package test.biz;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@Setting(shards = 2)
@Document(indexName = "user")
public class User {

    @Id
    private String id;
    private String name;
    @Field(type = FieldType.Date, format = {}, pattern = "uuuu-MM-dd")
    private LocalDate birthday;
    private Integer age;
    private String city;
    @Field(type = FieldType.Date, format = {}, pattern = "uuuu-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}
