package test.auto;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

@Data
@DynamicUpdate
@DynamicInsert
@Entity
@Table( name = "test_money" )
public class AutoMoneyPO {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO, generator = "myid" )
    @GenericGenerator( name = "myid", strategy = "test.auto.ManulInsertGenerator" )
    private Integer   id;
    private String    name;
    private Long      money;
    private Byte      isDeleted;
    @CreatedDate
    private Timestamp createAt;
    @CreatedDate
    private Timestamp updateAt;
}
