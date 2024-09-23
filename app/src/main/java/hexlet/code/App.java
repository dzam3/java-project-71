package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.File;
import java.io.IOException;

import static hexlet.code.Formatter.formatDiff;


@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "checksum 4.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {

    @Parameters(index = "0", description = "path to first file")
    private File filepath1;
    @Parameters(index = "1", description = "path to second file")
    private File filepath2;
    @Option(
            names = {"-f", "--format"},
            defaultValue = "stylish",
            description = "output format [default: ${DEFAULT-VALUE}]"
    )
    private String format;

    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }
    @Override
    public void run() {
        try {
            System.out.println(formatDiff(filepath1, filepath2, format));
        } catch (IOException e) {
            System.err.println("I/O Error: " + e.getMessage());
        } catch (ParsingException e) {
            System.err.println("Parsing Error: " + e.getMessage());
        } catch (UnsupportedFileExtensionException e) {
            System.err.println("Unsupported File Extension: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
