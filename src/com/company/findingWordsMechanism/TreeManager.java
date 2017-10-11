package com.company.findingWordsMechanism;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Jarek on 2017-09-24.
 */
public class TreeManager {
    static private int START_OF_TABLE = 0;
    static private String LIST_OF_WORDS = "words/slowa.txt";
    final static private int NUMBER_OF_LETTERS_IN_LATIN_ALPHABET = 26;

    public void load(Tree tree) {
        try {
            loadTree(tree);
        } catch (Exception e) {
            printError(e);
        }
    }

    private void loadTree(Tree tree) throws Exception {
        FileInputStream fileStream = new FileInputStream(LIST_OF_WORDS);
        Scanner scanner = new Scanner(fileStream);
        while (scanner.hasNextLine()) {
            String word = scanner.nextLine();
            int path[] = translateStringToPath(word); //translation of letter in word to path which consists indexes of next nodes
            tree.loadPath(path);
            System.out.println(word);
        }
        scanner.close();
    }

    private int[] translateStringToPath(String word) {
        int path[] = new int[word.length()];
        int pointer = START_OF_TABLE;
        char[] charArray = word.toCharArray();
        for(char letter: charArray){
            path[pointer++] = translateLetterToIndex(letter);
        }
        return path;
    }

    private int translateLetterToIndex(char letter) {
        int index;
        if (isLatin(letter)) {
            index = latinLetterTranslation(letter);
        } else {
            index = specialLetterTranslation(letter);
        }
        return index;
    }

    private boolean isLatin(char letter) {
        return (letter >= 'a' && letter <= 'z');
    }

    private int latinLetterTranslation(char letter) {
        return letter - 'a';
    }

    private int specialLetterTranslation(char letter) {
        switch (letter) {
            case 0x0105:
                return NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 0; //ą
            case 0x0107:
                return NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 1; //ć
            case 0x0119:
                return NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 2; //ę
            case 0x0142:
                return NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 3; //ł
            case 0x0144:
                return NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 4; //ń
            case 0x00f3:
                return NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 5; //ó
            case 0x015b:
                return NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 6; //ś
            case 0x017a:
                return NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 7; //ź
            case 0x017c:
                return NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 8; //ż
            default:
                return NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 9; //garbage
        }
    }

    private void printError(Exception e) {
        System.err.println("Error: " + e.getMessage());
    }


    public List<String> getWordList(Tree tree, String randomLetters) {
        List<Integer> randomIndexes = getIndexesFromWord(randomLetters);
        List<int[]> paths = tree.getPaths(randomIndexes);
        List<String> words = translatePathListToStringList(paths);

        words.sort((w1, w2) -> {
            if(w1.length() > w2.length()){
                return 1;
            }
            else if(w1.length() < w2.length()){
                return  -1;
            }
            else{
                return 0;
            }
        });

        return words;
    }

    private List<Integer> getIndexesFromWord(String string) {
        int[] intArray = translateStringToPath(string);
        Integer[] integerArray = Arrays.stream( intArray ).boxed().toArray( Integer[]::new ); // int array to Integer array
        return new ArrayList<>(Arrays.asList(integerArray));
    }

    private List<String> translatePathListToStringList(List<int[]> indexesList) {
        List<String> stringList = new ArrayList<String>();
        for (int[] indexes : indexesList) {
            String string = translateIndexesToString(indexes);
            stringList.add(string);
        }
        return stringList;
    }

    private String translateIndexesToString(int[] indexes) {
        String string = new String();
        for(int index: indexes){
            char letter = translateIndexToCharacter(index);
            string = string + letter;
        }
        return string;
    }

    private char translateIndexToCharacter(int index) {
        if(isLatinIndex(index)) {
            return latinIndexTranslation(index);
        }
        else {
            return specialIndexTranslation(index);
        }

    }

    private boolean isLatinIndex(int index) {
        return index < NUMBER_OF_LETTERS_IN_LATIN_ALPHABET;
    }

    private char latinIndexTranslation(int index) {
        return (char)(index + 'a');
    }

    private char specialIndexTranslation(int index){
        switch (index) {
            case NUMBER_OF_LETTERS_IN_LATIN_ALPHABET:
                return 0x0105; //ą
            case NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 1:
                return 0x0107; //ć
            case NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 2:
                return 0x0119; //ę
            case NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 3:
                return 0x0142; //ł
            case NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 4:
                return 0x0144; //ń
            case NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 5:
                return 0x00f3; //ó
            case NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 6:
                return 0x015b; //ś
            case NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 7:
                return 0x017a; //ź
            case NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 8:
                return 0x017c; //ż
            default:
                return ' '; //garbage
        }
    }
}
