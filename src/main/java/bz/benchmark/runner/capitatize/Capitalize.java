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
        return ch == '.' || ch == '?' || ch == '!';
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
        StringBuilder sb = new StringBuilder(length);

        boolean delimiterFound = false;
        boolean whiteCharAfterDelimiter = true;
        boolean firstDelimiter = false;
        for (final char ch : buffer) {
            if (isDelimiter(ch)) {
                delimiterFound = true;
                if (!firstDelimiter) {
                    sb.append(ch);
                }
            } else if (delimiterFound) {
                whiteCharAfterDelimiter = ch == WHITE_CHAR;
                delimiterFound = false;
                if (!firstDelimiter) {
                    sb.append(ch);
                }
                firstDelimiter = whiteCharAfterDelimiter;
            } else if (whiteCharAfterDelimiter) {
                sb.append(Character.toTitleCase(ch));
                whiteCharAfterDelimiter = false;
                firstDelimiter = false;
            } else {
                sb.append(ch);
            }
        }

        return sb.toString();
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

    public String capitalizeNewLikeOldWayGuillaume(String in) {
        int length = in.length();
        if (length == 0) {
            return in;
        }

        if (length == 1) {
            return in.toUpperCase();
        }
        final char[] buffer = in.toCharArray();
        boolean capitalizeNext = true;
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < buffer.length - 1; i++) {
            final char ch1 = buffer[i];
            final char ch2 = buffer[i + 1];
            if (isDelimiter(ch1) && Character.isWhitespace(ch2)) {
                if (!capitalizeNext) {
                    capitalizeNext = true;
                    sb.append(ch1);
                    sb.append(ch2);
                }
                if (i == buffer.length - 2) {
                    return sb.toString(); // end reached
                }
                i++;
            } else if (capitalizeNext) {
                sb.append(Character.toUpperCase(ch1));
                capitalizeNext = false;
            } else {
                sb.append(Character.toLowerCase(ch1));
            }
        }
        // last char handling
        char lastChar = buffer[buffer.length - 1];
        sb.append(capitalizeNext ? Character.toUpperCase(lastChar) : Character.toLowerCase(lastChar));
        return sb.toString();
    }
}
