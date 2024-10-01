import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.syspro.muller.Variable;

public class VariableTest {
    @Test
    public void testConstructor() {
    Variable vr = new Variable("x");
    Variable vr2 = new Variable("fal");
    Variable vr3 = new Variable("g");
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
        new Variable("4*3");
    });
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
        new Variable("4");
    });
    }

    @Test
    public void testPrint() {
        Variable vr = new Variable("x");
        Assertions.assertEquals("x", vr.print());
        Variable vr2 = new Variable("nyashmyash");
        Assertions.assertEquals("nyashmyash", vr2.print());
    }

    @Test
    void testSubstitution(){
        Variable vr = new Variable("x");
        Variable vr2 = new Variable("y");
        Variable vr3 = new Variable("z");
        Assertions.assertEquals(1, vr.substitution("x = 1; y = 2"));
        Assertions.assertEquals(2, vr2.substitution("x = 1; y = 2"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            vr3.substitution("x = 1; y = 2");
        });

    }

    @Test
    void TestDif(){
        Variable vr = new Variable("x");

    }
}
