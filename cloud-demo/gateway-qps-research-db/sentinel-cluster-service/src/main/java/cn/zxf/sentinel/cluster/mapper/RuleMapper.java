package cn.zxf.sentinel.cluster.mapper;

import cn.zxf.sentinel.cluster.config.RulePO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p/>
 * Created by ZXFeng on 2026/3/2
 */
@Mapper
public interface RuleMapper {

    List<RulePO> selectEnabledAll();

    int saveRule(RulePO rule);

}
