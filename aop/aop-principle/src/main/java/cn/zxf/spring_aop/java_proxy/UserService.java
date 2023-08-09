package cn.zxf.spring_aop.java_proxy;

public class UserService implements IUserService, ILogService {

    @Override
    public String getUserName(String id) {
        System.out.println("进入 service ==> user-id: " + id);
        return "user-name-" + id;
    }

    @Override
    public String log(String id) {
        System.out.println("进入 service.log(id) ==> user-id: " + id);
        return "log-test-" + id;
    }

}
