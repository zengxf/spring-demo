package cn.zxf.mybatis_starter.test.mapper;

import cn.zxf.mybatis_starter.test.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <br>
 * Created by ZXFeng on 2023/4/17
 */
@Mapper
public interface UserXmlMapper {

    Integer findStatus(int id);

    Integer findOneStatus(String name, int age);

    List<User> findList();

}
