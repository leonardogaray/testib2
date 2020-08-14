package com.todotresde.interbanking.stockoption.commons;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * The type Csv utils.
 */
public class CSVUtils {

    private static final String DEFAULT_SEPARATOR = ", ";

    /**
     * Write line.
     *
     * @param w      the w
     * @param values the values
     * @throws IOException the io exception
     */
    public static void writeLine(Writer w, List<String> values) throws IOException {
        writeLine(w, values, DEFAULT_SEPARATOR, ' ');
    }

    /**
     * Write line.
     *
     * @param w          the w
     * @param values     the values
     * @param separators the separators
     * @throws IOException the io exception
     */
    public static void writeLine(Writer w, List<String> values, String separators) throws IOException {
        writeLine(w, values, separators, ' ');
    }

    private static String followCVSformat(String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;

    }

    /**
     * Write line.
     *
     * @param w           the w
     * @param values      the values
     * @param separators  the separators
     * @param customQuote the custom quote
     * @throws IOException the io exception
     */
    public static void writeLine(Writer w, List<String> values, String separators, char customQuote) throws IOException {

        boolean first = true;

        //default customQuote is empty

        if (separators == " ") {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            if (!first) {
                sb.append(separators);
            }
            if (customQuote == ' ') {
                sb.append(followCVSformat(value));
            } else {
                sb.append(customQuote).append(followCVSformat(value)).append(customQuote);
            }

            first = false;
        }
        sb.append("\n");
        w.append(sb.toString());


    }

}
