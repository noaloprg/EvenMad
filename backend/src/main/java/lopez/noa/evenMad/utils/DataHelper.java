package lopez.noa.evenMad.utils;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class that absrtacts logic for transdormation o general data
 */
public class DataHelper {

    private static final String FIRST_IS_NUMBER_PATTERN = "^[a-zA-Z]";

    /**
     * Cleans a string, ready to show
     * @param text original text
     * @return cleaned text
     */
    public static String stringCleaner(String text) {
        // removes spaces
        String cleanString = text.trim();

        Matcher matcher = Pattern.compile(FIRST_IS_NUMBER_PATTERN)
                .matcher(cleanString);

        // validates that the 1º char is a string to put it to upperCase
        if (matcher.matches())
            cleanString = cleanString.substring(0, 1).toUpperCase() + cleanString.substring(1);
        return cleanString;
    }
}
