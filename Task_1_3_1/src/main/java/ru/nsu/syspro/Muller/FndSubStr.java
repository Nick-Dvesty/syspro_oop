package ru.nsu.syspro.Muller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Class for searching for a substring.
 */
abstract public class FndSubStr {

    private static int nextChar(Reader fileReader) throws IOException {
        char surrogChar = 0;
        int ansChar;
        while ((ansChar = fileReader.read()) != -1) {
            if (surrogChar != 0) {
                return Character.toCodePoint(surrogChar, (char) ansChar);
            } else if (Character.isHighSurrogate((char) ansChar)) {
                surrogChar = (char) ansChar;
            } else return ansChar;
        }
        return -1;
    }

    /**
     * static method for searching for a substring.
     * @param fileName the name of the file in which the search was performed
     * @param pattern substring
     * @return array of occurrence indexes
     * @throws IOException
     */
    static public ArrayList<Long> find(String fileName, String pattern) throws IOException {
        if (fileName == null || pattern == null) {
            throw new NullPointerException();
        }
        if (pattern.isEmpty()) {
            throw new IllegalArgumentException("pattern is empty");
        }
        Reader fileReader = (new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8));
        int lastPrefix = 0;
        int[] charsPattern = pattern.codePoints().toArray();
        int[] prefixes = new int[charsPattern.length];
        for (int i = 1; i < charsPattern.length; i++) {
            while (lastPrefix > 0 && charsPattern[lastPrefix] != charsPattern[i]) {
                lastPrefix = prefixes[lastPrefix - 1];
            }
            if (charsPattern[lastPrefix] == charsPattern[i]) {
                lastPrefix++;
            }
            prefixes[i] = lastPrefix;
        }

        lastPrefix = 0;
        int thisChar;
        long index = 0;
        ArrayList<Long> ans = new ArrayList<Long>();

        while ((thisChar = nextChar(fileReader)) != -1) {
            index++;
            while (lastPrefix > 0 && charsPattern[lastPrefix] != thisChar) {
                lastPrefix = prefixes[lastPrefix - 1];
            }
            if (charsPattern[lastPrefix] == thisChar) {
                lastPrefix++;
            }
            if (lastPrefix == charsPattern.length) {
                ans.add(index - lastPrefix);
                lastPrefix = prefixes[lastPrefix - 1];
            }
        }
        return ans;
    }
}
