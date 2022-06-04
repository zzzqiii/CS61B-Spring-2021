package flik;
import org.junit.Test;
import static org.junit.Assert.*;
public class testFlik {
    @Test
    public void testTrue() {
        assertTrue("True", Flik.isSameNumber(128, 128));
    }

    @Test
    public void testFalse() {
        assertTrue(Flik.isSameNumber(1000, 1000));
    }
}
