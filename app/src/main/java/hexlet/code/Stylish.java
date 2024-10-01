package hexlet.code;

import java.util.List;
import java.util.Map;

public class Stylish {

    public static StringBuilder formatToStylish(List<Map<String, Object>> difference) {
        StringBuilder result = new StringBuilder("{\n");
        difference.forEach(item -> {
            Object key = item.get("key");
            Object value1 = item.get("value1");
            Object value2 = item.get("value2");
            ChangeType type = (ChangeType) item.get("type");

            switch (type) {
                case NO_CHANGE:
                    result.append("    ").append(key).append(": ").append(value1).append("\n");
                    break;
                case ADDED:
                    result.append("  + ").append(key).append(": ").append(value1).append("\n");
                    break;
                case REMOVED:
                    result.append("  - ").append(key).append(": ").append(value1).append("\n");
                    break;
                case CHANGED:
                    result.append("  - ").append(key).append(": ").append(value1).append("\n");
                    result.append("  + ").append(key).append(": ").append(value2).append("\n");
                    break;
                default:
                    throw new IllegalStateException("Unknown ChangeType: " + type);
            }
        });
        result.append("}");
        return result;
    }
}