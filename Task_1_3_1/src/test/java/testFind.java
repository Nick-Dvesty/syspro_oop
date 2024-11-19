import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.syspro.Muller.FndSubStr;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class testFind {



    @Test
    public void test() throws IOException {
        Writer writer = new FileWriter("./test.txt", StandardCharsets.UTF_8);
        writer.write("а я такой голодный");
        writer.close();
        long[] ans = {4};
        var ansFind = FndSubStr.find("./test.txt", "такой");
        Assertions.assertEquals(ansFind.size(), ans.length);
        for (int i = 0; i < ansFind.size(); i++) {
            Assertions.assertEquals(ans[i], ansFind.get(i));
        }
        File file = new File("./test.txt");
        file.delete();
    }

    @Test
    public void test2() throws IOException {
        Writer writer = new FileWriter("./test.txt", StandardCharsets.UTF_8);
        writer.write("5555");
        writer.close();
        long[] ans = {0,1,2};
        var ansFind = FndSubStr.find("./test.txt", "55");
        Assertions.assertEquals(ansFind.size(), ans.length);
        for (int i = 0; i < ansFind.size(); i++) {
            Assertions.assertEquals(ans[i], ansFind.get(i));
        }
        File file = new File("./test.txt");
        file.delete();
    }

    @Test
    public void test3() throws IOException {
        Writer writer = new FileWriter("./test.txt", StandardCharsets.UTF_8);
        writer.write("");
        writer.close();
        var ansFind = FndSubStr.find("./test.txt", "1");
        Assertions.assertEquals(ansFind.size(),0);
        File file = new File("./test.txt");
        file.delete();
    }

    @Test
    public void test4() throws IOException {
        Writer writer = new FileWriter("./test.txt", StandardCharsets.UTF_8);
        writer.write("lglhkhklglfdglfdhlkggl;khfl;kghlfh");
        writer.close();
        assertThrows(IllegalArgumentException.class, () -> {
            var ansFind = FndSubStr.find("./test.txt", "");
        });
        File file = new File("./test.txt");
        file.delete();
    }

    @Test
    public void test5() throws IOException {
        assertThrows(NullPointerException.class, () -> {
            var ansFind = FndSubStr.find(null, "");
        });
    }

    @Test
    public void test6() throws IOException {
        assertThrows(NullPointerException.class, () -> {
            var ansFind = FndSubStr.find("test", null);
        });
    }

}
