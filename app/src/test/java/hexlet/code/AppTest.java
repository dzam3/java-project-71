package hexlet.code;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;

import static hexlet.code.Differ.generate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AppTest {
    private final File testFile1 = Path.of("src/test/resources/testFile1").toFile();
    private final File testFile2 = Path.of("src/test/resources/testFile2").toFile();
    private final File testJson1 = Path.of("src/test/resources/file1.json").toFile();
    private final File testJson2 = Path.of("src/test/resources/file2.json").toFile();
    private final File emptyFile1 = Path.of("src/test/resources/emptyFile1.json").toFile();
    private final File emptyFile2 = Path.of("src/test/resources/emptyFile2.json").toFile();


    @Test
    public void testGenerateJson() {
        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
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
        String expectedMessage = "File(s) is empty. Please provide a file with"
                + " JSON or YAML data formats";
        String actualMessage = thrown.getMessage();
        System.out.println(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
