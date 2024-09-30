import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.syspro.muller.Number;

public class NumberTest {
    @Test
    public void testPrint() {
        Number num = new Number("1");
        Assertions.assertEquals("1", num.print());
    }

    @Test
    public void testSubstitution() {
        Number num = new Number("1");
        Assertions.assertEquals(1, num.substitution("x = 1"));
        Assertions.assertEquals(1, num.substitution("x = 3"));
    }

    @Test
    public void testDif() {
        Number num = new Number("1");
        Assertions.assertEquals(num.dif().print(), "0");
    }
    @Test
    public void testSimple() {
        Number num = new Number("1");
        Assertions.assertEquals(num.simple().print(), "1");
    }

}
