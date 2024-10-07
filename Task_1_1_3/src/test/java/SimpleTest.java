import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.syspro.muller.Add;
import ru.nsu.syspro.muller.Div;
import ru.nsu.syspro.muller.Expression;
import ru.nsu.syspro.muller.Mul;
import ru.nsu.syspro.muller.Number;
import ru.nsu.syspro.muller.Sub;
import ru.nsu.syspro.muller.Variable;

/**
 * Simple method Test.
 */
public class SimpleTest {

    @Test
    void testSimple() {
        Expression vr = Expression.convertor("4+y");
        vr = new Add(new Number(4), new Variable("y"));
        Assertions.assertEquals(vr.simple().print(), "(4+y)");
        vr = new Add(new Number(0), new Number(1));
        Assertions.assertEquals(vr.simple().print(), "1");
        vr = new Sub(new Number(4), new Variable("y"));
        Assertions.assertEquals(vr.simple().print(), "(4-y)");
        vr =  new Sub(new Mul(new Number(23), new Variable("x")),
            new Mul(new Number(23), new Variable("x")));
        Assertions.assertEquals(vr.simple().print(), "0");
        vr = new Add(new Number(1), new Number(0));
        Assertions.assertEquals(vr.simple().print(), "1");
        vr = new Add(new Number(0), new Number(0));
        Assertions.assertEquals(vr.simple().print(), "0");
        vr = new Add(new Number(1), new Number(0));
        Assertions.assertEquals(vr.simple().print(), "1");
        vr = new Add(new Mul(new Number(1), new Number(32)),
            new Mul(new Number(0), new Variable("xi")));
        Assertions.assertEquals(vr.simple().print(), "32");
        vr = new Div(new Sub(new Mul(new Number(1), new Number(32)),
            new Mul(new Number(0), new Variable("xi"))), new Mul(new Number(32),
            new Number(32)));
        Assertions.assertEquals(vr.simple().print(), "0");
        vr = new Mul(new Number(1), new Mul(new Variable("xi"), new Number(1)));
        Assertions.assertEquals(vr.simple().print(), "xi");
        vr = new Add(new Mul(new Number(0), new Number(1)),
            new Mul(new Variable("xi"), new Number(0)));
        Assertions.assertEquals(vr.simple().print(), "0");
        vr =new Div(new Number(0), new Mul(new Number(12), new Variable("x")));
        Assertions.assertEquals(vr.simple().print(), "0");
        vr = new Add(new Number(23), new Div(new Variable("x"), new Number(1)));
        Assertions.assertEquals(vr.simple().print(), "(23+x)");
        vr = new Add(new Number(23), new Div(new Variable("x"), new Number(32)));
        Assertions.assertEquals(vr.simple().print(), "(23+(x/32))");
    }
}
