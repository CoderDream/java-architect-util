import com.jacoco.tutorial.Calculator;
import org.junit.Assert;
import org.junit.Test;

public class TestCalculator {

    private Calculator instance = new Calculator();

    @Test
    public void testAdd() {
        int a = 10;
        int b = 20;
        int expected = 30;
        Assert.assertEquals(expected, instance.add(a, b));
    }

    @Test
    public void testSub() {
        int a = 10;
        int b = 20;
        int expected = -10;
        Assert.assertEquals(expected, instance.sub(a, b));
    }
}