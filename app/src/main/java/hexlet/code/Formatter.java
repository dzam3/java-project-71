package hexlet.code;

import static hexlet.code.Differ.diff;

import java.io.File;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static String formatDiff(File file1, File file2, String format) {
        List<Map<String, Object>> difference;
        difference = diff(file1, file2);

        StringBuilder result = new StringBuilder("{\n");
        if (format.equals("stylish")) {
            difference.forEach(item -> {
                Object key = item.get("key");
                Object value = item.get("value");
                if (item.get("type").equals("no change")) {
                    result.append("    ").append(key).append(": ").append(value).append("\n");
                } else if (item.get("type").equals("added")) {
                    result.append("  + ").append(key).append(": ").append(value).append("\n");
                } else if (item.get("type").equals("removed")) {
                    result.append("  - ").append(key).append(": ").append(value).append("\n");
                } else {
                    result.append("  - ").append(key).append(": ").append(item.get("value1")).append("\n");
                    result.append("  + ").append(key).append(": ").append(item.get("value2")).append("\n");
                }
            });
        }
        result.append("}");

        return String.valueOf(result);
    }
}
