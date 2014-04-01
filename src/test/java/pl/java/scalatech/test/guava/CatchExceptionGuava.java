package pl.java.scalatech.test.guava;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.fest.assertions.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import pl.java.scalatech.test.guava.bean.ExampleBean;

/**
 * @author Sławomir Borowiec
 *         Module name : java7features
 *         Creating time : 1 kwi 2014 11:40:48
 */

public class CatchExceptionGuava {
    private final static String SQL_EXCEPTION = "retrieve data error ...";
    @Rule
    public ExpectedException thrown = ExpectedException.none();
 
    @Test
    public void shouldThrowSomeException() throws Exception {
        // given
        thrown.expect(SQLException.class);
        thrown.expectMessage(SQL_EXCEPTION);
        ExampleBean exampleBean = new ExampleBean();
        // when
        exampleBean.doSomething(1); 
        // then
        fail("SQLException should throw");
    }
    
    

    @Test
    public void shouldCatchIOException() throws IOException, SQLException {
        //given
        ExampleBean exampleBean = new ExampleBean();
        //when
        catchException(exampleBean).doSomething(2);
        //then
        Assertions.assertThat(caughtException()).isExactlyInstanceOf(IOException.class);

    }

    @Test
    public void shouldCatchSqlException() throws IOException, SQLException {
        //given
        ExampleBean exampleBean = new ExampleBean();
        //when
        catchException(exampleBean).doSomething(1);
        //then
        Assertions.assertThat(caughtException()).isExactlyInstanceOf(SQLException.class).hasMessage(SQL_EXCEPTION);

    }

}
