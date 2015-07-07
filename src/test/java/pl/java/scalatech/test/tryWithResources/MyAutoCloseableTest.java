package pl.java.scalatech.test.tryWithResources;

import org.junit.Test;

public class MyAutoCloseableTest {

    @Test
    public void shouldClosedAfterAction() throws Exception {
        try (MyAutoClosed myAutoClosable = new MyAutoClosed()) {
            myAutoClosable.doSomething("przodownik");
        }
    }

}
