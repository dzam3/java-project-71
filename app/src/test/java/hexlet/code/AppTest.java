package hexlet.code;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static hexlet.code.Differ.generate;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    private final File testFile1 = Path.of("src/test/resources/testFile1").toFile();
    private final File testFile2 = Path.of("src/test/resources/testFile2").toFile();
    private final File testJson1 = Path.of("src/test/resources/file1.json").toFile();
    private final File testJson2 = Path.of("src/test/resources/file2.json").toFile();
    private final File emptyFile1 = Path.of("src/test/resources/emptyFile1.json").toFile();
    private final File emptyFile2 = Path.of("src/test/resources/emptyFile2.json").toFile();


    //    @BeforeAll
//    public static void beforeAll() {
//        testFile1 = new File("testFile1");
//        testFile2 = new File("testFile2");
//        System.out.println(testFile1.getAbsolutePath());
//
//        String testFileContent1 = "{\n"
//                + "  \"host\": \"hexlet.io\",\n"
//                + "  \"timeout\": 50,\n"
//                + "  \"proxy\": \"123.234.53.22\",\n"
//                + "  \"follow\": false\n"
//                + "}\n";
//        String testFileContent2 = "{\n"
//                + "  \"timeout\": 20,\n"
//                + "  \"verbose\": true,\n"
//                + "  \"host\": \"hexlet.io\"\n"
//                + "}\n";
//        try (FileWriter writer1 = new FileWriter(testFile1);
//             FileWriter writer2 = new FileWriter(testFile2)) {
//            writer1.write(testFileContent1);
//            writer2.write(testFileContent2);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
    @Test
    public void testGenerateJson() {
        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        String actual = generate(testJson1, testJson2);
        assertEquals(expected, actual);
    }

    @Test
    public void testMissingExtension() {
        Exception thrown = assertThrows(RuntimeException.class, () -> generate(testFile1, testFile2)
        );
        String expectedMessage = "File extension is not supported. Supported extensions:"
                + " .json, .yaml, .yml";
        String actualMessage = thrown.getMessage();
        System.out.println(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testEmptyFiles() {
        Exception thrown = assertThrows(RuntimeException.class, () -> generate(emptyFile1, emptyFile2)
        );
    }
}
