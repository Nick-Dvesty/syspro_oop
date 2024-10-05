import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.syspro.muller.Expression;

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
}
