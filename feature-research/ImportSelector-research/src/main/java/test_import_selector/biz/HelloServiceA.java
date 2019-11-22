package test_import_selector.biz;

public class HelloServiceA implements HelloService {

    @Override
    public void doSomething() {
        System.out.println( "Hello A" );
    }

}
