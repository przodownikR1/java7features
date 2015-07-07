package pl.java.scalatech.test.guava;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkElementIndex;
import static com.google.common.base.Preconditions.checkNotNull;

import org.fest.assertions.Assertions;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.util.Assert;

import com.google.common.base.Preconditions;

/**
 * @author Sławomir Borowiec
 *         Module name : java7features
 *         Creating time : 18 mar 2014 14:16:16
 */
public class PreconditionsTest {
    private int[] values = new int[10];
    private final String login = null;
    private final int first = 2;
    private final int second = 1;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldPlainJavaValidationNPEThrow() {
        exception.expect(NullPointerException.class);
        if (null == login) { throw new NullPointerException("login must not be null"); }
    }

    @Test
    public void shouldPlainJavaValidationIllegalArgumentExceptionThrow() {
        exception.expect(IllegalArgumentException.class);
        if (first >= second) { throw new IllegalArgumentException("fist (" + first + ") must be smaller than second (" + second + ")"); }
    }

    @Test
    public void shouldNotNullValid() {
        String name = "przodownik";
        String returned = checkNotNull(name, " null");
        Assertions.assertThat(returned).isEqualTo(name);
    }

    @Test
    public void shouldSpringFrameworkAssertIllegalArgumentExceptionThrow() {
        exception.expect(IllegalArgumentException.class);
        Assert.notNull(login, "login must not be null");
        Assert.isTrue(first < second, "first (" + first + ") must be smaller than second (" + second + ")");
    }

    @Test
    @Ignore
    public void shouldAacheCommonsValidateIllegalArgumentExceptionThrow() {
        exception.expect(IllegalArgumentException.class);
        //Validate.notNull(login, "login must not be null");
        //Validate.isTrue(first < second, "first " + first + "  must be smaller than second" + second);
    }

    @Test
    public void shouldGuavaPreconditionsCheckNPEThrow() {
        exception.expect(NullPointerException.class);
        Preconditions.checkNotNull(login, "login must not be null");
    }

    @Test
    public void shouldGuavaPreconditionsCheckIllegalArgumentThrow() {
        exception.expect(IllegalArgumentException.class);
        Preconditions.checkArgument(first < second, "first (%s) must be smaller than second (%s)", first, second);
    }

    @Test
    public void shouldPlainJavaAsserts() {
        assert (null != login) : "login must not be null";
        assert (first < second) : "first (" + first + ") must be smaller than second (" + second + ")";
    }

    @Test
    public void shouldThrowIndexOutOfBoundsException() {
        int index = 11;
        exception.expect(IndexOutOfBoundsException.class);
        checkElementIndex(index, values.length, "Index out of bounds for values");
    }

    @Test
    public void shouldThrowIllegalArgumentException() {
        int valueToSet = 20;
        int currentIndex = 0;
        exception.expect(IllegalArgumentException.class);
        checkArgument(valueToSet <= 10, "Value can't be more than 10");
        values[currentIndex] = valueToSet;
    }
}
