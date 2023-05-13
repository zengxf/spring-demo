package cn.zxf.mybatis_starter.test.jsondata;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <br>
 * Created by ZXFeng on 2023/5/13
 */
@Data
@Accessors(chain = true)
public class JsonData {

    private Integer id;
    private String remark;
    // private Long[] userIds; // 不能直接转换
    private String userIds;
    private LocalDateTime createDate;

}
