package cn.zxf.jdbc_transaction.test.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.zxf.jdbc_transaction.test.user_log.UserLogService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserService {

    @Autowired
    private UserDao dao;

    @Autowired
    private UserLogService logService;

    public User createNonTransaction(String name) {
        User user = User.builder()
                .name(name)
                .age(22)
                .build();
        dao.save(user);

        log.info("user: {}", user);
        logService.createNonTransaction(user.getId());
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public User readOnly(String name, String sign) {
        User one = dao.selectOne();
        log.info("one: [{}]", one);

        User user = User.builder()
                .name(name)
                .age(24)
                .build();
        dao.save(user);

        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED, timeout = 1)
    public User createTransactionalRequiredTimeout(String name, String sign) throws InterruptedException {
        User user = User.builder()
                .name(name)
                .age(24)
                .build();

        Thread.sleep(1100L); // 创建 Statement 后验证
        dao.save(user);
        // Thread.sleep( 1100L ); // 事务提交时，并不验证

        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public User createTransactionalRequired(String name, String sign) {
        User user = User.builder()
                .name(name)
                .age(24)
                .build();
        dao.save(user);

        log.info("user: {}", user);
        try {
            logService.createTransactionalRequired(user.getId(), sign);
        } catch (Exception e) {
            log.info("\nlog error! \nmsg: {}\n", e.getMessage());
        }
        return user;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public User createTransactionalSupports(String name, String sign) {
        User user = User.builder()
                .name(name)
                .age(24)
                .build();
        dao.save(user);

        log.info("user: {}", user);
        try {
            logService.createTransactionalSupports(user.getId(), sign);
        } catch (Exception e) {
            log.info("\nlog error! \nmsg: {}\n", e.getMessage());
        }
        return user;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public User createTransactionalMandatory(String name, String sign) {
        User user = User.builder()
                .name(name)
                .age(24)
                .build();
        dao.save(user);

        log.info("user: {}", user);
        try {
            logService.createTransactionalMandatory(user.getId(), sign);
        } catch (Exception e) {
            log.info("\nlog error! \nmsg: {}\n", e.getMessage());
        }
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User createTransactionalRequiresNew(String name, String sign) {
        return this.createTransactionalRequiresNew(name, "ok", sign);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User createTransactionalRequiresNew(String name, String sign, String logSign) {
        User user = User.builder()
                .name(name)
                .age(24)
                .build();
        dao.save(user);

        log.info("user: {}", user);
        try {
            logService.createTransactionalRequiresNew(user.getId(), logSign);
        } catch (Exception e) {
            log.info("\nlog error! \nmsg: {}\n", e.getMessage());
        }
        this.checkSign(sign);
        return user;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public User createTransactionalNotSupported(String name, String sign) {
        User user = User.builder()
                .name(name)
                .age(24)
                .build();
        dao.save(user);

        log.info("user: {}", user);
        try {
            logService.createTransactionalNotSupported(user.getId(), sign);
        } catch (Exception e) {
            log.info("\nlog error! \nmsg: {}\n", e.getMessage());
        }
        return user;
    }

    @Transactional(propagation = Propagation.NEVER)
    public User createTransactionalNever(String name, String sign) {
        User user = User.builder()
                .name(name)
                .age(24)
                .build();
        dao.save(user);

        log.info("user: {}", user);
        try {
            logService.createTransactionalNever(user.getId(), sign);
        } catch (Exception e) {
            log.info("\nlog error! \nmsg: {}\n", e.getMessage());
        }
        return user;
    }

    @Transactional(propagation = Propagation.NESTED)
    public User createTransactionalNested(String name, String sign) {
        return this.createTransactionalNested(name, "ok", sign);
    }

    @Transactional(propagation = Propagation.NESTED)
    public User createTransactionalNested(String name, String sign, String logSign) {
        User user = User.builder()
                .name(name)
                .age(24)
                .build();
        dao.save(user);

        log.info("user: {}", user);
        try {
            logService.createTransactionalNested(user.getId(), logSign);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("\nlog error! \nmsg: {}\n", e.getMessage());
        }

        this.checkSign(sign);
        return user;
    }

    // ---------
    // ---------

    private void checkSign(String sign) {
        if ("err".equals(sign))
            throw new RuntimeException("Transactional Error Test");
    }

}
