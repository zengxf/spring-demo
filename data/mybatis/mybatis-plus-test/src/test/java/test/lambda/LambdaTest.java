package test.lambda;

import com.baomidou.mybatisplus.core.conditions.AbstractLambdaWrapper;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.interfaces.Compare;
import com.baomidou.mybatisplus.core.conditions.interfaces.Nested;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.support.LambdaMeta;
import com.baomidou.mybatisplus.core.toolkit.support.ReflectLambdaMeta;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import test.biz.User;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

/**
 * <br>
 * Created by ZXFeng on 2023/9/15
 */
@Slf4j
public class LambdaTest {

    @Test
    public void test1() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        /**
         * 参考类：{@link AbstractLambdaWrapper} / {@link AbstractWrapper} / {@link Compare} / {@link Nested}
         *
         * LambdaQueryWrapper 继承 AbstractLambdaWrapper，
         * AbstractLambdaWrapper 继承 AbstractWrapper，
         * 原码为：
         *     extends AbstractWrapper<T, SFunction<T, ?>, Children> {
         *
         * AbstractWrapper 实现 Compare，Nested
         * 原码为：
         *     AbstractWrapper<T, R, Children extends AbstractWrapper<T, R, Children>>
         *         implements Compare<Children, R>, Nested<Children, Children> {
         *
         * Compare: 设置 gt / eq 等条件
         * Nested: 设置 and / or 连接关系
         *
         * 泛型 Children 相当于返回自身
         */
        LambdaQueryWrapper<User> lambda = wrapper.lambda();
        LambdaQueryWrapper<User> compare = lambda
                .gt(User::getAge, 18)
                .eq(User::getName, "test");
        log.info("compare: [{}]", compare);
    }

    /*** 获取 Lambda 泛型具体实现 */
    @Test
    public void testSFun() throws Exception {
        SFunction<User, ?> fun = User::getAge;

        log.info("fun: [{}]\n", fun);

        LambdaMeta meta = LambdaUtils.extract(fun);
        log.info("method: [{}]", meta.getImplMethodName());
        log.info("class: [{}]\n", meta.getInstantiatedClass());

        Method method = fun.getClass().getDeclaredMethod("writeReplace");
        SerializedLambda sl = (SerializedLambda) ReflectionKit.setAccessible(method).invoke(fun);
        log.info("serialized-lambda: [{}]\n", sl);

        meta = new ReflectLambdaMeta(sl);
        log.info("method: [{}]", meta.getImplMethodName());
        log.info("class: [{}]", meta.getInstantiatedClass());
    }

}
