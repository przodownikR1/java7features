package pl.java.scalatech.test.guava;

import java.io.IOException;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

import org.fest.assertions.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.common.base.Throwables;

/**
 * @author Sławomir Borowiec 
 * Module name : java7features
 * Creating time :  1 kwi 2014 11:07:02
 
 */
@Slf4j
public class ThrowGuavaExample {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldThrowIOException() throws IOException, SQLException {
        exception.expect(IOException.class);
        doSomething(2);
        Assertions.assertThat(true);
    }
    @Test
    public void shouldThrowSQLException() throws IOException, SQLException {
        exception.expect(SQLException.class);
        doSomething(1);
        Assertions.assertThat(true);
    }

    private void doSomething(int test) throws IOException, SQLException {
        try {
            someMethodThatCouldThrowAnything(test);
        } catch (MyException e) {
            handle(e);
        } catch (Throwable t) {
            Throwables.propagateIfInstanceOf(t, IOException.class);
            Throwables.propagateIfInstanceOf(t, SQLException.class);
            throw Throwables.propagate(t);
        }
    }

    private void handle(MyException e) {
        log.info("handle exception");
    }

    private void someMethodThatCouldThrowAnything(int i) throws IOException, SQLException {
        if (i % 2 == 0) {
            throw new IOException("file read error"); 
            }
        throw new SQLException("retrieve data error ...");

    }
}
