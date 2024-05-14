package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static hexlet.code.Differ.generate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    private static File testFile1;
    private static File testFile2;
    @BeforeAll
    public static void beforeAll() {
        testFile1 = new File("src/test/resources/testFile1");
        testFile2 = new File("src/test/resources/testFile2");
        System.out.println(testFile1.getAbsolutePath());

        String testFileContent1 = "{\n"
                + "  \"host\": \"hexlet.io\",\n"
                + "  \"timeout\": 50,\n"
                + "  \"proxy\": \"123.234.53.22\",\n"
                + "  \"follow\": false\n"
                + "}\n";
        String testFileContent2 = "{\n"
                + "  \"timeout\": 20,\n"
                + "  \"verbose\": true,\n"
                + "  \"host\": \"hexlet.io\"\n"
                + "}\n";
        try (FileWriter writer1 = new FileWriter(testFile1);
             FileWriter writer2 = new FileWriter(testFile2)) {
            writer1.write(testFileContent1);
            writer2.write(testFileContent2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testGenerate() {
        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        String actual = generate(testFile1, testFile2);

        assertEquals(expected, actual);
    }

}
