package utils;

import java.util.ArrayList;
import java.util.List;

class StringUtil {

    static List<Character> splitToChars(String string) {
        List<Character> charsList = new ArrayList<>();
        char[] chars = string.toCharArray();
        for (char c : chars)
            charsList.add(c);
        return charsList;
    }
}
