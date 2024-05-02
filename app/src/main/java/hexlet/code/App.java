package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.File;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.ConfigDto;
import com.fasterxml.jackson.core.type.TypeReference;



@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "checksum 4.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {

    @Parameters(index = "0", description = "path to first file")
    private static File filepath1;
    @Parameters(index = "1", description = "path to second file")
    private File filepath2;
    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "stylish";

    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }
    @Override
    public void run() { // your business logic goes here...
        System.out.println("Hello World!");
        try {
            ConfigDto file1 = readJson(filepath1);
            ConfigDto file2 = readJson(filepath1);
            System.out.println(ConfigDto.differ(file1, file2));
//            System.out.println(readJson(filepath1));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ConfigDto readJson(File file) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ConfigDto jsonFile = mapper.readValue(filepath1, ConfigDto.class);
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//        String showJson = mapper.writeValueAsString(jsonFile1);

//        Map<String, ConfigDto> jsonMap1 = mapper.readValue(filepath1, typeRef);

        return jsonFile;
    }
}
