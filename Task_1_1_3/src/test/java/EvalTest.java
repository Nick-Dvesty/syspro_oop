import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.syspro.muller.Add;
import ru.nsu.syspro.muller.Div;
import ru.nsu.syspro.muller.Expression;
import ru.nsu.syspro.muller.Number;
import ru.nsu.syspro.muller.Mul;
import ru.nsu.syspro.muller.Sub;
import ru.nsu.syspro.muller.Variable;

/**
 * eval method Test.
 */
public class EvalTest {

    @Test
    void testEval() {
        Expression vr = new Add(new Number(2), new Mul(new Number(5),
            new Variable("x")));
        Assertions.assertEquals(12, vr.eval("x = 2"));
        vr = new Add (new Sub(new Number(40), new Mul(new Number(3),
            new Variable("x"))), new Mul(new Number(5), new Variable("y")));
        Assertions.assertEquals(-65, vr.eval("x = 40; y = 3"));
        vr = new Mul(new Add(new Number(40), new Mul(new Number(3),
            new Variable("x"))), (new Sub(new Number(5), new Variable("y"))));
        Assertions.assertEquals(0, vr.eval("x = 2; y = 5"));
        vr = new Add(new Add(new Number(40), new Mul(new Number(3),
            new Variable("x"))),(new Sub(new Number(5), new Variable("y"))));
        Assertions.assertEquals(47, vr.eval("x = 2; y = 4"));
        vr = new Add((new Add(new Number(40),new Mul(new Number(3),
            new Variable("x")))), new Div(new Add(new Number(5),
            new Variable("y")), new Number(2)));
        Assertions.assertEquals(50.5d, vr.eval("x = 2; y = 4"));
        Assertions.assertThrows(ArithmeticException.class, () -> {
            Expression vr2 = new Add((new Add(new Number(40), new Mul(new Number(3),
                new Variable("x")))), new Div(new Add(new Number(5),
                new Variable("y")), new Number(0)));
            vr2.eval("x = 2; y = 4");
        });
        Assertions.assertThrows(ArithmeticException.class, () -> {
            Expression vr2 = new Div(new Number(0), new Number(0));
            vr2.eval("x = 2; y = 4");
        });
        Assertions.assertThrows(ArithmeticException.class, () -> {
            Expression vr2 = new Div(new Sub(new Number(5),
                new Number(10)), new Number(0));
            vr2.eval("x = 2; y = 4");
        });
    }
}
