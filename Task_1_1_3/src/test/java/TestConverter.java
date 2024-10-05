import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.syspro.muller.Add;
import ru.nsu.syspro.muller.Div;
import ru.nsu.syspro.muller.Expression;
import ru.nsu.syspro.muller.Mul;
import ru.nsu.syspro.muller.Number;
import ru.nsu.syspro.muller.Sub;
import ru.nsu.syspro.muller.Variable;

public class TestConverter {
    @Test
    public void testConvert() {
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
    public void testConstract() {
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

    @Test
    public void testSubstitution() {
        var vr = Expression.convertor("2+5*x");
        Assertions.assertEquals(12, vr.eval("x = 2"));
        vr = Expression.convertor("40-3*x+5*y");
        Assertions.assertEquals(-65, vr.eval("x = 40; y = 3"));
        vr = Expression.convertor("(40+3*x)*(5-y)");
        Assertions.assertEquals(0, vr.eval("x = 2; y = 5"));
        vr = Expression.convertor("(40+3*x)+(5-y)");
        Assertions.assertEquals(47, vr.eval("x = 2; y = 4"));
        vr = Expression.convertor("(40+3*x)+(5+y)/2");
        Assertions.assertEquals(50.5d, vr.eval("x = 2; y = 4"));
        Assertions.assertThrows(ArithmeticException.class, () -> {
            var vr2 = Expression.convertor("(40+3*x)+(5+y)/0");;
                vr2.eval("x = 2; y = 4");
        });
        Assertions.assertThrows(ArithmeticException.class, () -> {
            var vr2 = Expression.convertor("0/0");;
            vr2.eval("x = 2; y = 4");
        });
        Assertions.assertThrows(ArithmeticException.class, () -> {
            var vr2 = Expression.convertor("(5-10)/0");;
            vr2.eval("x = 2; y = 4");
        });
    }

    @Test void testDifferentiation() {
        Expression vr = new Add(new Number("4"), new Variable("y"));
        Assertions.assertEquals(vr.derivative("y").print(), "(0+1)");
        vr = new Add(new Variable("x"), new Variable("y"));
        Assertions.assertEquals(vr.derivative("x").print(), "(1+0)");
        Assertions.assertEquals(vr.derivative("z").print(), "(0+0)");
        vr = new Sub(new Variable("xi"), new Number("1"));
        Assertions.assertEquals(vr.derivative("xi").print(), "(1-0)");
        vr = new Mul(new Variable("xi"), new Number("32"));
        Assertions.assertEquals(vr.derivative("xi").print(), "((1*32)+(xi*0))");
        vr = new Div(new Variable("xi"), new Number("32"));
        Assertions.assertEquals(vr.derivative("xi").print(), "(((1*32)-(xi*0))/(32*32))");
    }

    @Test void testSimple() {
        Expression vr = Expression.convertor("4+y");
        Assertions.assertEquals(vr.simple().print(), "(4+y)");
        Assertions.assertEquals(vr.derivative("y").simple().print(), "1");
        vr = Expression.convertor("4-y");
        Assertions.assertEquals(vr.simple().print(), "(4-y)");
        Assertions.assertEquals(vr.simple().derivative("y").print(), "(0-1)");
        vr = Expression.convertor("(23*x)-(23*x)");
        Assertions.assertEquals(vr.simple().print(), "0");
        vr = Expression.convertor( "x+y");
        Assertions.assertEquals(vr.derivative("x").simple().print(), "1" );
        Assertions.assertEquals(vr.derivative("z").simple().print(), "0" );
        vr = Expression.convertor("xi+1");
        Assertions.assertEquals(vr.derivative("xi").simple().print(), "1");
        vr = Expression.convertor("xi*32");
        Assertions.assertEquals(vr.derivative("xi").simple().print(), "32");
        vr = Expression.convertor("xi/32");
        Assertions.assertEquals(vr.derivative("xi").simple().print(), "0");
        vr = Expression.convertor("1*(xi*1)");
        Assertions.assertEquals(vr.simple().print(), "xi");
        vr = Expression.convertor("(0*1)+(xi*0)");
        Assertions.assertEquals(vr.simple().print(), "0");
        vr = Expression.convertor("0/(12*x)");
        Assertions.assertEquals(vr.simple().print(), "0");
        vr = Expression.convertor("23+x/1");
        Assertions.assertEquals(vr.simple().print(), "(23+x)");
        vr = Expression.convertor("23+x/32");
        Assertions.assertEquals(vr.simple().print(), "(23+(x/32))");
    }
}



