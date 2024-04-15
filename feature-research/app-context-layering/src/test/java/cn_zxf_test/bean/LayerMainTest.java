package cn_zxf_test.bean;

import cn_zxf_test.layering.bean.ChildBean;
import cn_zxf_test.layering.bean.PartBean;
import cn_zxf_test.layering.bean.RootBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 分层测试
 * <p/>
 * Created by ZXFeng on 2024/4/15
 */
@Slf4j
public class LayerMainTest {

    public static void main(String[] args) {
        GenericApplicationContext parent = new GenericApplicationContext();
        parent.setDisplayName("parent-ctx");
        parent.registerBean(RootBean.class, () -> new RootBean("P-Root-001"));
        parent.registerBean(PartBean.class, () -> new PartBean("Part-001"));

        GenericApplicationContext child = new GenericApplicationContext(parent);
        child.setDisplayName("child-ctx");
        child.registerBean(RootBean.class, () -> new RootBean("C-Root-888"));   // 覆盖
        child.registerBean(ChildBean.class);

        // -----------------------

        parent.refresh();
        extracted(parent, RootBean.class);
        extracted(parent, PartBean.class);
        extracted(parent, ChildBean.class);

        child.refresh();
        extracted(child, RootBean.class);   // 有覆盖，返回子类的
        extracted(child, PartBean.class);   // 没有覆盖，返回父类的
        extracted(child, ChildBean.class);
    }

    private static <T> void extracted(GenericApplicationContext ctx, Class<T> clazz) {
        try {
            T obj = ctx.getBean(clazz);
            log.info("ctx: [{}], clazz: [{}], obj: [{}]", ctx.getDisplayName(), clazz.getSimpleName(), obj);
        } catch (Exception e) {
            log.error("ctx: [{}], clazz: [{}], error: [{}]", ctx.getDisplayName(), clazz.getSimpleName(), e.getMessage(), e);
        }
        log.info("--------------------------\n\n");
    }

}
