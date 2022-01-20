package bz.benchmark.runner.capitatize;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@State(Scope.Benchmark)
public class Capitalize {

    private static final Pattern PATTERN_OLD = Pattern.compile("\\.\\s|!\\s|\\?\\s");
    private static final Pattern PATTERN_NEW = Pattern.compile("[!?.]\\s");
    private static final char[] DELIMITER = new char[]{'.', '!', '?'};
    private static final char WHITE_CHAR = ' ';

    private static String formatSentence(String sentence) {
        if (sentence.length() <= 0) {
            return sentence;
        }
        return Character.toUpperCase(sentence.charAt(0)) + sentence.substring(1);
    }

    private static boolean isDelimiter(final char ch) {
        for (final char delimiter : DELIMITER) {
            if (ch == delimiter) {
                return true;
            }
        }
        return false;
    }

    public String capitalizeOld(String in) {
        int length = in.length();
        if (length == 0) {
            return in;
        }

        if (length == 1) {
            return in.toUpperCase();
        }

        // LowerCase the whole string
        in = in.toLowerCase();

        int lastMatch = 0;
        StringBuilder builder = new StringBuilder(in.length());

        Matcher matcher = PATTERN_OLD.matcher(in);

        while (matcher.find()) {
            // Get the current sentence
            String currentSentence = in.substring(lastMatch, matcher.start());
            if (currentSentence.length() > 0) {
                // Append the formatted string
                builder.append(formatSentence(currentSentence));
                // Append the delimiter (". ", "! " or "? "
                builder.append(matcher.group());
            }
            // Keep position of the last matched occurrence in order to continue the match from this point
            lastMatch = matcher.end();
        }

        // Append the rest of the string
        builder.append(formatSentence(in.substring(lastMatch)));

        return builder.toString();
    }

    public String capitalizeOldNewRegex(String in) {
        int length = in.length();
        if (length == 0) {
            return in;
        }

        if (length == 1) {
            return in.toUpperCase();
        }

        // LowerCase the whole string
        in = in.toLowerCase();

        int lastMatch = 0;
        StringBuilder builder = new StringBuilder(in.length());

        Matcher matcher = PATTERN_NEW.matcher(in);

        while (matcher.find()) {
            // Get the current sentence
            String currentSentence = in.substring(lastMatch, matcher.start());
            if (currentSentence.length() > 0) {
                // Append the formatted string
                builder.append(formatSentence(currentSentence));
                // Append the delimiter (". ", "! " or "? "
                builder.append(matcher.group());
            }
            // Keep position of the last matched occurrence in order to continue the match from this point
            lastMatch = matcher.end();
        }

        // Append the rest of the string
        builder.append(formatSentence(in.substring(lastMatch)));

        return builder.toString();
    }


    public String capitalizeNewLikeOldWay(String in) {
        int length = in.length();
        if (length == 0) {
            return in;
        }

        if (length == 1) {
            return in.toUpperCase();
        }

        for (char delimiter : DELIMITER) {
            if (in.startsWith(String.format("%s%s", delimiter, WHITE_CHAR))) {
                in = in.substring(2);
            }
        }

        // LowerCase the whole string
        in = in.toLowerCase();
        final char[] buffer = in.toCharArray();
        final char[] out = new char[length];
        int j = 0;

        boolean delimiterFound = false;
        boolean whiteCharAfterDelimiter = true;
        boolean firstDelimiter = false;
        for (final char ch : buffer) {
            if (isDelimiter(ch)) {
                delimiterFound = true;
                if (!firstDelimiter) {
                    out[j] = ch;
                    j++;
                }
            } else if (delimiterFound) {
                whiteCharAfterDelimiter = ch == WHITE_CHAR;
                delimiterFound = false;
                if (!firstDelimiter) {
                    out[j] = ch;
                    j++;
                }
                firstDelimiter = whiteCharAfterDelimiter;
            } else if (whiteCharAfterDelimiter) {
                out[j] = Character.toTitleCase(ch);
                j++;
                whiteCharAfterDelimiter = false;
                firstDelimiter = false;
            } else {
                out[j] = ch;
                j++;
            }
        }

        return new String(out);
    }

    public String trullyCapitalize(String in) {
        int length = in.length();
        if (length == 0) {
            return in;
        }

        if (length == 1) {
            return in.toUpperCase();
        }
        final char[] buffer = in.toLowerCase().toCharArray();
        boolean delimiterFound = false;
        boolean capitalizeNext = true;
        for (int i = 0; i < buffer.length; i++) {
            final char ch = buffer[i];
            if (isDelimiter(ch)) {
                delimiterFound = true;
            } else if (delimiterFound) {
                capitalizeNext = ch == WHITE_CHAR;
                delimiterFound = false;
            } else if (capitalizeNext) {
                buffer[i] = Character.toTitleCase(ch);
                capitalizeNext = false;
            }
        }
        return new String(buffer);
    }
}
