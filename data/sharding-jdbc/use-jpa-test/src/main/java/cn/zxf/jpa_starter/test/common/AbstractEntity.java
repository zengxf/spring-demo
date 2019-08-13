package cn.zxf.jpa_starter.test.common;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.experimental.Accessors;

@MappedSuperclass
@Data
@Accessors( fluent = true )
public class AbstractEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    public Integer id;

    protected Date    createDate;
    protected Date    updateDate;

}
