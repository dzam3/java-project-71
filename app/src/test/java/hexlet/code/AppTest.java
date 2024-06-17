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
    private final File testFile3 = Path.of("src/test/resources/file3.yml").toFile();
    private final File testFile4 = Path.of("src/test/resources/file4.yml").toFile();

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
        String actual = generate(testJson1, testJson2, "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateYaml() {
        String expected = """ 
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";
        String actual = generate(testFile3, testFile4, "stylish");
        assertEquals(expected, actual);
    }
    @Test
    public void testMissingExtension() {
        Exception thrown = assertThrows(RuntimeException.class, () -> generate(testFile1, testFile2, "stylish")
        );
        String expectedMessage = "File extension is not supported. Supported extensions:"
                + " .json, .yaml, .yml";
        String actualMessage = thrown.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testEmptyFiles() {
        Exception thrown = assertThrows(RuntimeException.class, () -> generate(emptyFile1, emptyFile2, "stylish")
        );
        String expectedMessage = "File(s) is empty. Please provide a file with"
                + " JSON or YAML data formats";
        String actualMessage = thrown.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
