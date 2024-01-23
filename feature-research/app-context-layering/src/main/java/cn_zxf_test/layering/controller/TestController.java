package cn_zxf_test.layering.controller;

import cn_zxf_test.layering.bean.ChildBean;
import cn_zxf_test.layering.bean.PartBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p/>
 * Created by ZXFeng on 2024/1/23
 */
@RestController
@Slf4j
public class TestController implements ApplicationContextAware {

    @Autowired
    private ChildBean childBean;

    // @Autowired // 其他 ChildContext 都需要配置，否则会报错
    // private PartBean partBean;

    private ApplicationContext ctx;


    // child1 => http://localhost:8080/test
    // child2 => http://localhost:8081/test
    // child3 => http://localhost:8082/test
    @RequestMapping("/test")
    public ChildBean getChildBean() {
        boolean isContains = ctx.containsBean("partBean");
        if (isContains) {
            PartBean partBean = ctx.getBean(PartBean.class);
            log.info("partBean: [{}]", partBean);
        } else {
            log.info("不存在 PartBean 对象");
        }
        return childBean;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.ctx = applicationContext;
    }

}