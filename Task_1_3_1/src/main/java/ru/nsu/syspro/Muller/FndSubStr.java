package ru.nsu.syspro.Muller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FndSubStr {
    private final Reader fileReader;

    FndSubStr(Reader fileReader) {
        this.fileReader = fileReader;
    }

    FndSubStr(String fileName) throws IOException {
        this.fileReader = (new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8));
    }

    protected int nextChar() throws IOException {
        char surrogChar = 0;
        int ansChar;
        while ((ansChar = fileReader.read()) != -1) {
            if (surrogChar != 0) {
                return Character.toCodePoint(surrogChar, (char) ansChar);
            }
            if (Character.isHighSurrogate((char) ansChar)) {
                surrogChar = (char) ansChar;
            }
        }
        return -1;
    }

    public ArrayList<Long> find(String filename, String pattern) throws IOException {
        if (filename == null || pattern == null) {
            throw new NullPointerException();
        }
        if (pattern.isEmpty()) {
            throw new IllegalArgumentException("pattern is empty");
        }
        int lastPrefix = 0;
        int[] charsPattern = pattern.codePoints().toArray();
        int[] prefixes = new int[charsPattern.length];
        for (var  i : charsPattern) {
            while (lastPrefix > 0 && charsPattern[lastPrefix] != i) {
                lastPrefix = prefixes[lastPrefix - 1];
            }
            if (charsPattern[lastPrefix] == i) {
                lastPrefix++;
            }
            prefixes[i] = lastPrefix;
        }

        lastPrefix = 0;
        int thisChar;
        long index = 0;
        ArrayList<Long> ans = new ArrayList<>();

        while ((thisChar = nextChar()) != -1) {
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
