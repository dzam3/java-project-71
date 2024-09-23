package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;


public class Parser {
    @SuppressWarnings("unchecked")
    public static Map<String, Object> readJson(File file) throws IOException, ParsingException {
        if (!file.exists()) {
            throw new IOException("File not found: " + file.getPath());
        }
        if (Files.size(file.toPath()) == 0) {
            throw new ParsingException("I/O Error: File not found");
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(file, Map.class);
        } catch (JsonProcessingException e) {
            throw new ParsingException("Failed to parse JSON file: " + file.getPath(), e);
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> readYaml(File file) throws IOException, ParsingException {
        if (!file.exists()) {
            throw new IOException("File not found: " + file.getPath());
        }
        if (Files.size(file.toPath()) == 0) {
            throw new ParsingException("File is empty: " + file.getPath());
        }
        ObjectMapper mapper = new YAMLMapper();
        try {
            return mapper.readValue(file, Map.class);
        } catch (JsonProcessingException e) {
            throw new ParsingException("Failed to parse YAML file: " + file.getPath(), e);
        }
    }
}
