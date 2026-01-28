package test.exception;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

/**
 * 流量超限
 * <p/>
 * Created by ZXFeng on 2026/1/28
 */
@Data
public class QpsException extends RuntimeException {

    private long useNum;
    private long limitedNum;

    public QpsException(long useNum, long limitedNum) {
        super(StrUtil.format("QPS 超限：限制为 {}，已使用为 {}", limitedNum, useNum));
        this.useNum = useNum;
        this.limitedNum = limitedNum;
    }

}
