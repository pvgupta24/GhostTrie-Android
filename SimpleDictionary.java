package com.google.engedu.ghost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class SimpleDictionary implements GhostDictionary {
    private ArrayList<String> words;
    ArrayList<String> test = new ArrayList<String>();
    Random random;

    public SimpleDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        words = new ArrayList<>();
        String line;
        while ((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() >= MIN_WORD_LENGTH)
                words.add(line.trim());
        }
    }

    @Override
    public boolean isWord(String word) {
        return words.contains(word);
    }

    @Override
    public String getAnyWordStartingWith(String prefix) {
        int i;
        boolean flag = false;
        for (String str : words) {
            if (str.startsWith(prefix)) {
                flag = true;
                test.add(str);
                return  str;
            }
        }


        return null;
    }

    @Override
    public String getGoodWordStartingWith(String prefix) {
        return null;

    }
    public String getWord() {
        int i = random.nextInt(words.size());
        String selected = words.get(i);
        return selected;
    }

}