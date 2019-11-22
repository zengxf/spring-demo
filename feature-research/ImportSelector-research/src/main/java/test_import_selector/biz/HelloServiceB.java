package test_import_selector.biz;

public class HelloServiceB implements HelloService {

    @Override
    public void doSomething() {
        System.out.println( "Hello B" );
    }

}
