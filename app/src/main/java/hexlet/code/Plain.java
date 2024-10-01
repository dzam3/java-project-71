package hexlet.code;

import java.util.List;
import java.util.Map;

public class Plain {

    public static StringBuilder formatToPlain(List<Map<String, Object>> difference) {
        StringBuilder result = new StringBuilder();
        difference.forEach(item -> {
            Object key = item.get("key");
            Object value1 = item.get("value1");
            value1 = checkType(value1);
            Object value2 = item.get("value2");
            value2 = checkType(value2);
            ChangeType type = (ChangeType) item.get("type");

            switch (type) {
                case NO_CHANGE:
                    result
                            .append("Property '")
                            .append(key)
                            .append("' was not changed")
                            .append("\n");
                    break;
                case ADDED:
                    result
                            .append("Property '")
                            .append(key)
                            .append("' was added with value: ")
                            .append(value1)
                            .append("\n");
                    break;
                case REMOVED:
                    result
                            .append("Property '")
                            .append(key)
                            .append("' was removed")
                            .append("\n");
                    break;
                case CHANGED:
                    result
                            .append("Property '")
                            .append(key)
                            .append("' was updated. From ")
                            .append(value1)
                            .append(" to ")
                            .append(value2)
                            .append("\n");
                    break;
                default:
                    throw new IllegalStateException("Unknown ChangeType: " + type);
            }
        });
        return result;
    }

    private static Object checkType(Object obj) {
        if (obj instanceof Character) {
            return "'" + obj + "'";
        } else if (obj instanceof String) {
            return "'" + obj + "'";
        } else if (obj == null) {
            return null;
        } else if (obj instanceof Map) {
            return "[complex value]";
        } else if (obj instanceof List) {
            return "[complex value]";
        } else if (obj.getClass().isArray()) {
            return "[complex value]";
        }
        else {
            return obj;
        }
    }
}
