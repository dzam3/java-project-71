package hexlet.code;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ConfigDto {
    private String host;
    private int timeout;
    private String proxy;
    private String follow;
    private Boolean verbose;

    public ConfigDto() {
    }

    @Override
    public String toString() {
        return "{" + "\n" +
                "host=" + host + "\n" +
                "timeout=" + timeout + "\n" +
                "proxy=" + proxy + "\n" +
                "follow=" + follow + "\n" +
                "verbose=" + verbose + "\n" +
                '}';
    }

    public static String differ(ConfigDto file1, ConfigDto file2) {
        Map<String, Object> result = new HashMap<>();
        if (!file1.getHost().isEmpty() && file2.getHost().isEmpty()) {
            result.put("- host", file1.getHost());
        } else if (file1.getHost().isEmpty() && !file2.getHost().isEmpty()) {
            result.put("+ host", file2.getHost());
        } else if (file1.getHost().equals(file2.getHost())) {
            result.put("  host", file1.getHost());
        }

        return result.toString();
    }
}
