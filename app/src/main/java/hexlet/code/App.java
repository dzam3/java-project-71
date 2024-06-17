package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.File;
import static hexlet.code.Differ.generate;


@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "checksum 4.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {

    @Parameters(index = "0", description = "path to first file")
    private File filepath1;
    @Parameters(index = "1", description = "path to second file")
    private File filepath2;
    @Option(names = {"-f", "--format"}, defaultValue = "stylish", description = "output format [default: ${DEFAULT-VALUE}]")
    private String format;

    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }
    @Override
    public void run() { // your business logic goes here...
        try {
            System.out.println(generate(filepath1, filepath2, format));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
