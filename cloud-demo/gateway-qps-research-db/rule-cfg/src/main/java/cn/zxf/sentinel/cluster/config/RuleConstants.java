package cn.zxf.sentinel.cluster.config;

/**
 * <p/>
 * Created by ZXFeng on 2026/3/4
 */
public interface RuleConstants {

    String
            SOURCE_FMT = "%s#%s#%s#%s",
            DEFAULT_APP = "@",
            DEFAULT_API = "@",
            SUFFIX_QPS = "QPS",
            SUFFIX_TRAFFIC = "TFC";

    String
            NONE_RULE_SIGN = "";

}
