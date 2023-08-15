package test.commodity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <br/>
 * Created by ZXFeng on 2022/3/11.
 */
@Table
@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Accessors(chain = true)
public class Commodity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Integer status;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Tag> tagMap;
    @JdbcTypeCode(SqlTypes.JSON)
    private List<Tag> tags;

    @Column(columnDefinition = "DATETIME default CURRENT_TIMESTAMP()")
    private LocalDateTime createDate;
    @Column(columnDefinition = "DATETIME default CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP()")
    private LocalDateTime updateDate;


    @Data
    @Accessors(chain = true)
    public static class Tag {
        private String key;
        private String value;
    }

}
