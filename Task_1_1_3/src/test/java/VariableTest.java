import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.syspro.muller.Variable;

public class VariableTest {
    @Test
    public void testConstructor() {
    Variable vr = new Variable("x");
    Variable vr2 = new Variable("fal");
    Variable vr3 = new Variable("g");
    Variable vr4 = new Variable("HAD");
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
        new Variable("{");
    });
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
        new Variable("");
    });
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
        new Variable(null);
    });
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
        Variable vr3 = new Variable("zov");
        Assertions.assertEquals(1, vr.substitution("x = 1; y = 2"));
        Assertions.assertEquals(2, vr2.substitution("x = 1; y = 2"));
        Assertions.assertEquals(2, vr3.substitution("x = 1; zov = 2"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            vr3.substitution("x = 1; y = 2");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            vr3.substitution("zov = 5x; y = 2");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            vr3.substitution("zov = 5 ; y = 2");
        });
    }

    @Test
    void TestDif(){
        Variable vr = new Variable("x");
        Variable vr2 = new Variable("wow");
        Assertions.assertEquals("1", vr.dif("x").print());
        Assertions.assertEquals("0", vr.dif("wow").print());
        Assertions.assertEquals("1", vr2.dif("wow").print());
        Assertions.assertEquals("0", vr2.dif("x").print());
    }

    @Test
    void TestSimple(){
        Variable vr = new Variable("x");
        Assertions.assertEquals("x", vr.simple().print());
    }
}
