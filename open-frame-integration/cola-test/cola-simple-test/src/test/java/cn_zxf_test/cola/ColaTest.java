package cn_zxf_test.cola;

import cn_zxf_test.model.Context;
import cn_zxf_test.model.Events;
import cn_zxf_test.model.States;
import com.alibaba.cola.statemachine.Action;
import com.alibaba.cola.statemachine.Condition;
import com.alibaba.cola.statemachine.StateMachine;
import com.alibaba.cola.statemachine.StateMachineFactory;
import com.alibaba.cola.statemachine.builder.StateMachineBuilder;
import com.alibaba.cola.statemachine.builder.StateMachineBuilderFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * <br>
 * Created by ZXFeng on 2023/7/8
 */
@Slf4j
public class ColaTest {

    static final String MACHINE_ID = "test-mc-";


    /*** 从单一状态流转到另一个状态 */
    @Test
    public void testExternalNormal() {
        StateMachineBuilder<States, Events, Context> builder = StateMachineBuilderFactory.create();
        builder.externalTransition()
                .from(States.STATE1)
                .to(States.STATE2)
                .on(Events.EVENT1)
                .when(checkCondition())
                .perform(doAction());

        StateMachine<States, Events, Context> stateMachine = builder.build("test-002");
        States target = stateMachine.fireEvent(States.STATE1, Events.EVENT1, new Context());

        Assert.assertEquals(States.STATE2, target);
    }

    private Condition<Context> checkCondition() {
        return (ctx) -> {
            return true;
        };
    }

    private Action<States, Events, Context> doAction() {
        return (from, to, event, ctx) -> {
            System.out.println(
                    ctx.operator + " is operating " + ctx.entityId + ", from: " + from + " to: " + to + " on: " + event
            );
        };
    }


    /*** 从多个状态流传到新的状态 */
    @Test
    public void testExternalTransitionsNormal() {
        StateMachineBuilder<States, Events, Context> builder = StateMachineBuilderFactory.create();
        builder.externalTransitions()
                .fromAmong(States.STATE1, States.STATE2, States.STATE3)
                .to(States.STATE4)
                .on(Events.EVENT1)
                .when(checkCondition())
                .perform(doAction());

        StateMachine<States, Events, Context> stateMachine = builder.build("test-002-001");
        States target = stateMachine.fireEvent(States.STATE2, Events.EVENT1, new Context());

        Assert.assertEquals(States.STATE4, target);
    }


    /*** 状态内部触发流转 */
    @Test
    public void testInternalNormal() {
        StateMachineBuilder<States, Events, Context> builder = StateMachineBuilderFactory.create();
        builder.internalTransition()
                .within(States.STATE1)
                .on(Events.INTERNAL_EVENT)
                .when(checkCondition())
                .perform(doAction());
        StateMachine<States, Events, Context> stateMachine = builder.build(MACHINE_ID + "2");

        stateMachine.fireEvent(States.STATE1, Events.EVENT1, new Context());
        States target = stateMachine.fireEvent(States.STATE1, Events.INTERNAL_EVENT, new Context());

        Assert.assertEquals(States.STATE1, target);
    }


    /*** 多线程测试并发测试 */
    @Test
    public void testMultiThread() {
        String mcId = "testMultiThread";
        this.buildStateMachine(mcId);

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                StateMachine<States, Events, Context> stateMachine = StateMachineFactory.get(mcId);
                States target = stateMachine.fireEvent(States.STATE1, Events.EVENT1, new Context());
                Assert.assertEquals(States.STATE2, target);
            });
            thread.start();
        }

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                StateMachine<States, Events, Context> stateMachine = StateMachineFactory.get(mcId);
                States target = stateMachine.fireEvent(States.STATE1, Events.EVENT4, new Context());
                Assert.assertEquals(States.STATE4, target);
            });
            thread.start();
        }

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                StateMachine<States, Events, Context> stateMachine = StateMachineFactory.get(mcId);
                States target = stateMachine.fireEvent(States.STATE1, Events.EVENT3, new Context());
                Assert.assertEquals(States.STATE3, target);
            });
            thread.start();
        }
    }

    private void buildStateMachine(String mcId) {
        StateMachineBuilder<States, Events, Context> builder = StateMachineBuilderFactory.create();
        builder.externalTransition()
                .from(States.STATE1)
                .to(States.STATE2)
                .on(Events.EVENT1)
                .when(checkCondition())
                .perform(doAction());
        builder.externalTransition()
                .from(States.STATE1)
                .to(States.STATE3)
                .on(Events.EVENT3)
                .when(checkCondition())
                .perform(doAction());
        builder.externalTransition()
                .from(States.STATE1)
                .to(States.STATE4)
                .on(Events.EVENT4)
                .when(checkCondition())
                .perform(doAction());
        builder.build(mcId); // 会自动注册
    }


    /*** 多分支状态流转示例 */
    @Test
    public void testChoice() {
        StateMachineBuilder<States, Events, Context> builder = StateMachineBuilderFactory.create();
        builder.internalTransition()
                .within(States.STATE1)
                .on(Events.EVENT1)
                .when(checkCondition1())
                .perform(doAction());
        builder.externalTransition()
                .from(States.STATE1)
                .to(States.STATE2)
                .on(Events.EVENT1)
                .when(checkCondition2())
                .perform(doAction());
        builder.externalTransition()
                .from(States.STATE1)
                .to(States.STATE3)
                .on(Events.EVENT1)
                .when(checkCondition3())
                .perform(doAction());
        /** 重复定义会报错 */
        // builder.externalTransition()
        //         .from(States.STATE1)
        //         .to(States.STATE3)
        //         .on(Events.EVENT1)
        //         .when(checkCondition3())
        //         .perform(doAction());

        StateMachine<States, Events, Context> stateMachine = builder.build("ChoiceConditionMachine");
        States target1 = stateMachine.fireEvent(States.STATE1, Events.EVENT1, new Context("1"));
        Assert.assertEquals(States.STATE1, target1);

        States target2 = stateMachine.fireEvent(States.STATE1, Events.EVENT1, new Context("2"));
        Assert.assertEquals(States.STATE2, target2);

        States target3 = stateMachine.fireEvent(States.STATE1, Events.EVENT1, new Context("3"));
        Assert.assertEquals(States.STATE3, target3);
    }

    private Condition<Context> checkCondition1() {
        return (ctx) -> {
            return "1".equals(ctx.sign);
        };
    }

    private Condition<Context> checkCondition2() {
        return (ctx) -> {
            return "2".equals(ctx.sign);
        };
    }

    private Condition<Context> checkCondition3() {
        return (ctx) -> {
            return "3".equals(ctx.sign);
        };
    }


    /*** 不满足状态流转条件时的处理：状态不会变化 */
    @Test
    public void testConditionNotMeet() {
        StateMachineBuilder<States, Events, Context> builder = StateMachineBuilderFactory.create();
        builder.externalTransition()
                .from(States.STATE1)
                .to(States.STATE2)
                .on(Events.EVENT1)
                .when(checkConditionFalse())
                .perform(doAction());

        StateMachine<States, Events, Context> stateMachine = builder.build("NotMeetConditionMachine");
        States target = stateMachine.fireEvent(States.STATE1, Events.EVENT1, new Context());

        Assert.assertEquals(States.STATE1, target);
    }

    private Condition<Context> checkConditionFalse() {
        return (ctx) -> {
            return false;
        };
    }


    /**
     * 生成 PlantUML
     * <p/>
     * 在线查看：https://www.planttext.com/
     * <br/>
     * 语法参考：https://plantuml.com/zh/sequence-diagram
     */
    @Test
    public void testGeneratePlantUML() {
        StateMachineBuilder<States, Events, Context> builder = StateMachineBuilderFactory.create();
        builder.externalTransition()
                .from(States.STATE1)
                .to(States.STATE2)
                .on(Events.EVENT1)
                .when(checkConditionFalse())
                .perform(doAction());
        builder.externalTransition()
                .from(States.STATE1)
                .to(States.STATE3)
                .on(Events.EVENT3)
                .when(checkConditionFalse())
                .perform(doAction());
        builder.externalTransition()
                .from(States.STATE1)
                .to(States.STATE4)
                .on(Events.EVENT4)
                .when(checkConditionFalse())
                .perform(doAction());
        builder.externalTransition()
                .from(States.STATE2)
                .to(States.STATE4)
                .on(Events.EVENT4)
                .when(checkConditionFalse())
                .perform(doAction());
        builder.externalTransition()
                .from(States.STATE2)
                .to(States.STATE3)
                .on(Events.EVENT3)
                .when(checkConditionFalse())
                .perform(doAction());

        StateMachine<States, Events, Context> stateMachine = builder.build("NotMeetConditionMachine");
        String plantUml = stateMachine.generatePlantUML();

        log.info("UML: \n\n{}\n\n", plantUml);
    }

}
