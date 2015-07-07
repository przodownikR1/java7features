package pl.java.scalatech.test.tryWithResources;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyAutoClosed implements AutoCloseable {

    public void doSomething(String str) {
        log.info("+++  hello  {}", str);
    }

    @Override
    public void close() throws Exception {
        log.info("+++  close ");
    }

}
