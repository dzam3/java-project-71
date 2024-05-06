package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    public static Map<String, Object> readJson(File file) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonMap = mapper.readValue(file, Map.class);
        return jsonMap;
    }

    public static String generate(File file1, File file2) {
        try {
            Map<String, Object> jointJson = new TreeMap<>();
            Map<String, Object> jsonMap1 = readJson(file1);
            Map<String, Object> jsonMap2 = readJson(file2);
            jointJson.putAll(jsonMap1);
            jointJson.putAll(jsonMap2);
            StringBuilder result = new StringBuilder("{\n");

            jointJson.forEach((key, value) -> {
                if (value.equals(jsonMap1.get(key)) && value.equals(jsonMap2.get(key))) {
                    result.append("    " + key + ": " + value + "\n");
                } else if (!jsonMap1.containsKey(key)) {
                    result.append("  + " + key + ": " + value + "\n");
                } else if (!jsonMap2.containsKey(key)) {
                    result.append("  - " + key + ": " + value + "\n");
                } else if (!value.equals(jsonMap1.get(key)) && value.equals(jsonMap2.get(key))) {
                    result.append("  - " + key + ": " + jsonMap1.get(key) + "\n");
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
