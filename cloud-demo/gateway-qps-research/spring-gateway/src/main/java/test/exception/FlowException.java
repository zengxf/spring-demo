package test.exception;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 流量超限
 * <p/>
 * Created by ZXFeng on 2026/1/28
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class FlowException extends RuntimeException {

    private long useFlow;
    private long limitedFlow;

    public FlowException(long useFlow, long limitedFlow) {
        super(StrUtil.format("流量超限：限制为 {}，已使用为 {}", limitedFlow, useFlow));
        this.useFlow = useFlow;
        this.limitedFlow = limitedFlow;
    }

}
