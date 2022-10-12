import com.jacoco.tutorial.Human;
import org.junit.Test;
public class TestHuman {

    Human human = new Human();

    @Test
    public void testHuman() {
        human.say();
    }
}
