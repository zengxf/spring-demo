package cn_zxf_test.layering;

import cn_zxf_test.layering.bean.ChildBean;
import cn_zxf_test.layering.context.ChildContext1;
import cn_zxf_test.layering.context.ChildContext2;
import cn_zxf_test.layering.context.ChildContext3;
import cn_zxf_test.layering.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * <p/>
 * Created by ZXFeng on 2024/1/23
 */
@Slf4j
public class AppMain {

    public static void main(String[] args) {
        SpringApplicationBuilder appBuilder =
                new SpringApplicationBuilder()
                        .sources(RootContext.class)
                        // 第一个子 context 用 child，剩下的都用 sibling
                        .child(ChildContext1.class)
                        .sibling(ChildContext2.class)
                        .sibling(ChildContext3.class)
                // .child(ChildContext1.class, ChildContext2.class, ChildContext3.class) // 这种配置启动不了
                ;

        ConfigurableApplicationContext ctx = appBuilder.run();

        log.info("\n\n\n");
        log.info("--------------------------");
        ChildBean cb = ctx.getBean(ChildBean.class);
        log.info("cb: [{}]", cb);   // child3
    }

}
