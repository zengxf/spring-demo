package test.commodity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

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
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Commodity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private Integer status;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<Tag> tags;

    @Data
    @Accessors(chain = true)
    public static class Tag {
        private String key;
        private String value;
    }

}
