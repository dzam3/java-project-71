package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Objects;

import hexlet.code.ChangeType;
import static hexlet.code.Parser.readJson;
import static hexlet.code.Parser.readYaml;

public class Differ {
    static final List<String> YAML_EXT = List.of(".yaml", ".yml");

    public static List<Map<String, Object>> diff(File file1, File file2)
            throws IOException, ParsingException, UnsupportedFileExtensionException {
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
            throw new UnsupportedFileExtensionException("File extension is not supported. Supported extensions: " +
                    ".json, .yaml, .yml");
        }

        Map<String, Object> jointMap = new TreeMap<>();
        List<Map<String, Object>> result = new ArrayList<>();

        jointMap.putAll(map1);
        jointMap.putAll(map2);

        jointMap.forEach((key, value) -> {
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            if (Objects.equals(value, value1) && Objects.equals(value, value2)) {
                result.add(createNode(key, ChangeType.NO_CHANGE, value1));
            } else if (!map1.containsKey(key)) {
                result.add(createNode(key, ChangeType.ADDED, value2));
            } else if (!map2.containsKey(key)) {
                result.add(createNode(key, ChangeType.REMOVED, value1));
            } else {
                result.add(createNode(key, ChangeType.CHANGED, value1, value2));
            }
        });

        return result;
    }
    private static String getFileExtension(String fileName) throws UnsupportedFileExtensionException {
        int index = fileName.lastIndexOf('.');
        if (index == -1) {
            throw new UnsupportedFileExtensionException("File extension is not supported. Supported extensions:" +
                    " .json, .yaml, .yml");
        }
        return fileName.substring(index);
    }

    private static Map<String, Object> createNode(String key, ChangeType type, Object value) {
        Map<String, Object> node = new HashMap<>();
        node.put("key", key);
        node.put("type", type);
        node.put("value", value);
        return node;
    }

    private static Map<String, Object> createNode(String key, ChangeType type, Object value1, Object value2) {
        Map<String, Object> node = new HashMap<>();
        node.put("key", key);
        node.put("type", type);
        node.put("value1", value1);
        node.put("value2", value2);
        return node;
    }
}
