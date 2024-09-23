package hexlet.code;

import static hexlet.code.Differ.diff;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Formatter {

    public static String formatDiff(File file1, File file2, String format)
            throws UnsupportedFileExtensionException, ParsingException, IOException {
        List<Map<String, Object>> difference = diff(file1, file2);

        StringBuilder result = new StringBuilder("{\n");
        if (format.equals("stylish")) {
            difference.forEach(item -> {
                Object key = item.get("key");
                Object value = item.get("value");
                ChangeType type = (ChangeType) item.get("type");

                switch (type) {
                    case NO_CHANGE:
                        result.append("    ").append(key).append(": ").append(value).append("\n");
                        break;
                    case ADDED:
                        result.append("  + ").append(key).append(": ").append(value).append("\n");
                        break;
                    case REMOVED:
                        result.append("  - ").append(key).append(": ").append(value).append("\n");
                        break;
                    case CHANGED:
                        result.append("  - ").append(key).append(": ").append(item.get("value1")).append("\n");
                        result.append("  + ").append(key).append(": ").append(item.get("value2")).append("\n");
                        break;
                    default:
                        throw new IllegalStateException("Unknown ChangeType: " + type);
                }
            });
        }
        result.append("}");

        return result.toString();
    }
}