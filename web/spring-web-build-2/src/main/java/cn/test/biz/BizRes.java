package cn.test.biz;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * <p/>
 * Created by ZXFeng on 2024/10/10
 */
@Data
@Accessors(chain = true)
public class BizRes {

    /**
     * 实体 ID
     */
    private Long id;


    /**
     * 批次 ID
     */
    private String batchId;

    /**
     * 标识
     */
    private String sign;


    /**
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy--MM--dd", timezone = "GMT+8")
    private LocalDate createDate;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private LocalTime createTime;

    /**
     * 修改时间
     */
    @JsonProperty("up-time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;


}
