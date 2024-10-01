package hexlet.code;

import static hexlet.code.Differ.diff;
import static hexlet.code.Plain.formatToPlain;
import static hexlet.code.Stylish.formatToStylish;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Formatter {

    public static String formatDiff(File file1, File file2, String format)
            throws UnsupportedFileExtensionException, ParsingException, IOException {
        List<Map<String, Object>> difference = diff(file1, file2);

        StringBuilder formattedDiff = new StringBuilder();
        if (format.equals("stylish")) formattedDiff.append(formatToStylish(difference));
        else if (format.equals("plain")) formattedDiff.append(formatToPlain(difference));

        return formattedDiff.toString();
    }
}