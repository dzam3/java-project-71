package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    static final List<String> YAML_EXT = List.of(".yaml", ".yml");
    public static Map<String, Object> readJson(File file) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonMap = mapper.readValue(file, Map.class);
        return jsonMap;
    }

    public static Map<String, Object> readYaml(File file) throws Exception {
        ObjectMapper mapper = new YAMLMapper();
        Map<String, Object> yamlMap = mapper.readValue(file, Map.class);
        return yamlMap;
    }

    public static String generate(File file1, File file2) {
        try {
            Map<String, Object> jointMap = new TreeMap<>();
            String fileName1 = file1.getName().toLowerCase();
            String fileName2 = file2.getName().toLowerCase();
            System.out.println(fileName1);
            String ext1 = fileName1.substring(fileName1.indexOf('.'));
            String ext2 = fileName2.substring(fileName2.indexOf('.'));
            Map<String, Object> map1 = new HashMap<>();
            Map<String, Object> map2 = new HashMap<>();

            if (YAML_EXT.contains(ext1) && YAML_EXT.contains(ext2)) {
                map1.putAll(readYaml(file1));
                map2.putAll(readYaml(file2));
            } else if (ext1.equals(".json") && ext2.equals(".json")) {
                map1.putAll(readJson(file1));
                map2.putAll(readJson(file2));
            } else {
                throw new RuntimeException("File extension is not supported. Supported extensions: \".json\", "
                        + YAML_EXT);
            }

            jointMap.putAll(map1);
            jointMap.putAll(map2);
            StringBuilder result = new StringBuilder("{\n");

            jointMap.forEach((key, value) -> {
                if (value.equals(map1.get(key)) && value.equals(map2.get(key))) {
                    result.append("    " + key + ": " + value + "\n");
                } else if (!map1.containsKey(key)) {
                    result.append("  + " + key + ": " + value + "\n");
                } else if (!map2.containsKey(key)) {
                    result.append("  - " + key + ": " + value + "\n");
                } else if (!value.equals(map1.get(key)) && value.equals(map2.get(key))) {
                    result.append("  - " + key + ": " + map1.get(key) + "\n");
                    result.append("  + " + key + ": " + value + "\n");
                }
            });
            result.append("}");
            return result.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
