package cn.zxf.mybatis_starter.test.jsondata;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <br>
 * Created by ZXFeng on 2023/5/13
 */
@Mapper
public interface JsonDataMapper {

    List<JsonData> findAll();

    int save(JsonData data);

}
