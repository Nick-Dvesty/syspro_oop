import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.syspro.muller.Add;
import ru.nsu.syspro.muller.Div;
import ru.nsu.syspro.muller.Expression;
import ru.nsu.syspro.muller.Mul;
import ru.nsu.syspro.muller.Number;
import ru.nsu.syspro.muller.Sub;
import ru.nsu.syspro.muller.Variable;

public class DerivativeTest {
    @Test
    void testDifferentiation() {
        Expression vr = new Add(new ru.nsu.syspro.muller.Number("4"), new Variable("y"));
        Assertions.assertEquals(vr.derivative("y").print(), "(0+1)");
        vr = new Add(new Variable("x"), new Variable("y"));
        Assertions.assertEquals(vr.derivative("x").print(), "(1+0)");
        Assertions.assertEquals(vr.derivative("z").print(), "(0+0)");
        vr = new Sub(new Variable("xi"), new ru.nsu.syspro.muller.Number("1"));
        Assertions.assertEquals(vr.derivative("xi").print(), "(1-0)");
        vr = new Mul(new Variable("xi"), new ru.nsu.syspro.muller.Number("32"));
        Assertions.assertEquals(vr.derivative("xi").print(), "((1*32)+(xi*0))");
        vr = new Div(new Variable("xi"), new Number("32"));
        Assertions.assertEquals(vr.derivative("xi").print(), "(((1*32)-(xi*0))/(32*32))");
    }
}
