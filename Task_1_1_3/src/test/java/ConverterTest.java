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
 * convertor method Test.
 */
public class ConverterTest {
    @Test
    void testConvert() {
        var test = Expression.convertor("(12+1)");
        Assertions.assertEquals("(12+1)", test.print());
        test = Expression.convertor("(12-1)");
        Assertions.assertEquals("(12-1)", test.print());
        test = Expression.convertor("(12*1)");
        Assertions.assertEquals("(12*1)", test.print());
        test = Expression.convertor("(12/1)");
        Assertions.assertEquals("(12/1)", test.print());
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            var test2 = Expression.convertor("(12%1)");
        });
        test = Expression.convertor("(12+1)/x+(x+ye*3)");
        Assertions.assertEquals("(((12+1)/x)+(x+(ye*3)))", test.print());
    }

    @Test
    void testConstract() {
        var vr = new Add(new Number("4"), new Variable("y"));
        var vr2 = new Sub(new Variable("x"), new Number("2"));
        var vr3 = new Mul(new Number("9"), new Variable("y"));
        var vr4 = new Mul(new Variable("x"), new Variable("y"));
        var vr5 = new Add(vr, vr2);
        var vr6 = new Sub(vr3, vr4);
        var vr7 = new Mul(vr5, vr6);
        var vr8 = new Div(vr7, new Number("2"));
        Assertions.assertEquals("((((4+y)+(x-2))*((9*y)-(x*y)))/2)", vr8.print());
    }

}



