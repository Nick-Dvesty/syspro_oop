
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.syspro.muller.FndSubStr;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestFind {

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
        long[] ans = {0, 1, 2};
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
        Assertions.assertEquals(ansFind.size(), 0);
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

    @Test
    public void test7() throws IOException {
        Writer writer = new FileWriter("./test.txt", StandardCharsets.UTF_8);
        ArrayList<Long> ans = new ArrayList<>();
        for (long i = 0; i < (2 << 20); i++) {
            if (i % (2 << 19) == 0 ) {
                writer.write(String.valueOf(1));
                ans.add(i);
            } else {
                writer.write(String.valueOf(2));
            }
        }
        writer.close();
        var ansFind = FndSubStr.find("./test.txt", "1");
        Assertions.assertEquals(ansFind.size(), ans.size());
        for (int i = 0; i < ansFind.size(); i++) {
            Assertions.assertEquals(ans.get(i), ansFind.get(i));
        }
        File file = new File("./test.txt");
        file.delete();
    }

    @Test
    public void test8() throws IOException {
        Writer writer = new FileWriter("./test.txt", StandardCharsets.UTF_8);
        writer.write("lglhk");
        writer.close();
        var ansFind = FndSubStr.find("./test.txt", "lglhkdfsfsff");
        Assertions.assertEquals(ansFind.size(), 0);
        File file = new File("./test.txt");
        file.delete();
    }

}
