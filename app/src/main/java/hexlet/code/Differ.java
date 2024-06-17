package hexlet.code;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static hexlet.code.Parser.readJson;
import static hexlet.code.Parser.readYaml;

public class Differ {
    static final List<String> YAML_EXT = List.of(".yaml", ".yml");

    public static String generate(File file1, File file2) {
        try {
            Map<String, Object> jointMap = new TreeMap<>();
            String fileName1 = file1.getName().toLowerCase();
            String fileName2 = file2.getName().toLowerCase();

            String ext1 = getFileExtension(fileName1);
            String ext2 = getFileExtension(fileName2);

            Map<String, Object> map1 = new HashMap<>();
            Map<String, Object> map2 = new HashMap<>();

            if (YAML_EXT.contains(ext1) && YAML_EXT.contains(ext2)) {
                map1.putAll(readYaml(file1));
                map2.putAll(readYaml(file2));
            } else if (ext1.equals(".json") && ext2.equals(".json")) {
                map1.putAll(readJson(file1));
                map2.putAll(readJson(file2));
            } else {
                throw new RuntimeException("File extension is not supported. Supported extensions:"
                        + " .json, .yaml, .yml");
            }

            jointMap.putAll(map1);
            jointMap.putAll(map2);
            StringBuilder result = new StringBuilder("{\n");

            jointMap.forEach((key, value) -> {
                if (value.equals(map1.get(key)) && value.equals(map2.get(key))) {
                    result.append("    ").append(key).append(": ").append(value).append("\n");
                } else if (!map1.containsKey(key)) {
                    result.append("  + ").append(key).append(": ").append(value).append("\n");
                } else if (!map2.containsKey(key)) {
                    result.append("  - ").append(key).append(": ").append(value).append("\n");
                } else if (!value.equals(map1.get(key)) && value.equals(map2.get(key))) {
                    result.append("  - ").append(key).append(": ").append(map1.get(key)).append("\n");
                    result.append("  + ").append(key).append(": ").append(value).append("\n");
                }
            });
            result.append("}");
            return result.toString();
        } catch (RuntimeException e) {
            throw e; // Rethrow RuntimeExceptions to preserve their messages
        } catch (Exception e) {
            throw new RuntimeException("File extension is not supported. Supported extensions: .json, .yaml, .yml");
        }
    }
    private static String getFileExtension(String fileName) {
        int index = fileName.lastIndexOf('.');
        if (index == -1) {
            throw new RuntimeException("File extension is not supported. Supported extensions: .json, .yaml, .yml");
        }
        return fileName.substring(index);
    }
}
