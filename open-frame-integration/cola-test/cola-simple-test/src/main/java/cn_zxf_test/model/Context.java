package cn_zxf_test.model;

import lombok.NoArgsConstructor;

/**
 * <br>
 * Created by ZXFeng on 2023/7/8
 */
@NoArgsConstructor
public class Context {

    public String sign;
    public String operator = "111";
    public String entityId = "222";

    public Context(String sign) {
        this.sign = sign;
    }

}
