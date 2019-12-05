package test.biz;

public class DefaultService implements SimpleService {
    @Override
    public String serve() {
        return "Default Service";
    }
}