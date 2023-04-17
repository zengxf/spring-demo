package cn.zxf.mybatis_starter.test.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * <br>
 * Created by ZXFeng on 2023/4/17
 */
@Mapper
public interface UserXmlMapper {

    Integer findStatus(int id);

    Integer findOneStatus(String name, int age);

}
