package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Formatter {
    public static String formatDiff(Map<String, Object> map1, Map<String, Object> map2, String format) {
        StringBuilder result = new StringBuilder("{\n");
        Map<String, Object> jointMap = new TreeMap<>();

        jointMap.putAll(map1);
        jointMap.putAll(map2);
        if (format.equals("stylish")) {
            jointMap.forEach((key, value) -> {
                Object value1 = map1.get(key);
                Object value2 = map2.get(key);

                if (Objects.equals(value, value1) && Objects.equals(value, value2)) {
                    result.append("    ").append(key).append(": ").append(value).append("\n");
                } else if (!map1.containsKey(key)) {
                    result.append("  + ").append(key).append(": ").append(value).append("\n");
                } else if (!map2.containsKey(key)) {
                    result.append("  - ").append(key).append(": ").append(value).append("\n");
                } else {
                    result.append("  - ").append(key).append(": ").append(value1).append("\n");
                    result.append("  + ").append(key).append(": ").append(value).append("\n");
                }
            });
            result.append("}");
        }
        return result.toString();
    }
}
