package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;

public class Parser {
    @SuppressWarnings("unchecked")
    public static Map<String, Object> readJson(File file) throws Exception {
        if (Files.size(file.toPath()) == 0) {
            throw new RuntimeException("File(s) is empty. Please provide a file with"
                    + " JSON or YAML data formats");
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, Map.class);
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> readYaml(File file) throws Exception {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(file, Map.class);
    }
}
