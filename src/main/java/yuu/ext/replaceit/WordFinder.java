package yuu.ext.replaceit;

import java.util.ArrayList;
import java.util.List;

public class WordFinder {
    public List<Integer> findWord(String textString, String word) {
        List<Integer> indexes = new ArrayList<Integer>();
        String lowerCaseTextString = textString.toLowerCase();
        String lowerCaseWord = word.toLowerCase();

        int index = 0;
        while(index != -1){
            index = lowerCaseTextString.indexOf(lowerCaseWord, index);
            if (index != -1) {
                indexes.add(index);
                index++;
            }
        }
        return indexes;
    }

    public List<String> WordList(String textString, List<Integer> indexes, String word) {
        List<String> words = new ArrayList<String>();
        for (int index : indexes) {
            words.add(textString.substring(index, index + word.length()));
        }
        return words;
    }
}
