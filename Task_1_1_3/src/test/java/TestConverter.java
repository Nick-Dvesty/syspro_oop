import org.junit.jupiter.api.Test;
import ru.nsu.syspro.muller.Expression;

public class TestConverter {
    @Test
    public void testConvert() {
        var test = Expression.convertor("((12-1)*6+x)");
        System.out.println(test.substitution("x = 20"));

    }
}
