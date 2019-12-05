package test.biz;

public class CustomService implements SimpleService {
    @Override
    public String serve() {
        return "Custom Service";
    }
}
