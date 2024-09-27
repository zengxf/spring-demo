package cn_zxf_test.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * <p/>
 * Created by ZXFeng on 2024/9/27
 */
@Entity
@Table(name = "biz_user")
@DynamicInsert
@DynamicUpdate
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(length = 20)
    private String mobile;

    private String email;

    private Integer status;


}
