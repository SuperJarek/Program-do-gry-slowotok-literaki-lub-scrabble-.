package com.company.findingWordsMechanism;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

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
            int indexes[] = translateWordToIndexes(word);
            tree.addWord(indexes);
        }
        scanner.close();
    }

    private int[] translateWordToIndexes(String word) {
        int indexes[] = initializeTableForIndexes(word);
        int pointer = START_OF_TABLE;
        while (pointer < word.length()) {
            char letter = word.charAt(pointer);
            indexes[pointer] = translateLetterToIndex(letter);
            pointer++;
        }
        return indexes;
    }

    private int translateLetterToIndex(char letter) {
        int index;
        if (isLatin(letter)) {
            index = latinLetterTranslation(letter);
        } else
            index = specialLetterTranslation(letter);
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
            case 0x0114:
                return NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 4; //ń
            case 0x00f3:
                return NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 5; //ó
            case 0x015b:
                return NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 6; //ś
            case 0x017c:
                return NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 7; //ź
            case 0x0104:
                return NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 8; //ż
            default:
                return NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 9; //garbage
        }
    }

    private int[] initializeTableForIndexes(String word) {
        int length = word.length();
        return new int[length];
    }

    private void printError(Exception e) {
        System.err.println("Error: " + e.getMessage());
    }

    public List<String> findWords(Tree tree, String randomLetters) {
        List<Integer> randomIndexes = getIndexesFromString(randomLetters);
        List<int[]> indexesFromWords = tree.findWords(randomIndexes);
        List<String> words = translateIndexesListToStringList(indexesFromWords);
        return words;
    }

    private List<Integer> getIndexesFromString(String string) {
        int[] intArray = translateWordToIndexes(string);
        Integer[] integerArray = Arrays.stream( intArray ).boxed().toArray( Integer[]::new );
        return new ArrayList<>(Arrays.asList(integerArray));
    }

    private List<String> translateIndexesListToStringList(List<int[]> indexesList) {
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
        if(isLatinIndex(index))
            return latinIndexTranslation(index);
        else
            return specialIndexTranslation(index);

    }

    private boolean isLatinIndex(int index) {
        return index < NUMBER_OF_LETTERS_IN_LATIN_ALPHABET;
    }

    private char latinIndexTranslation(int index) {
        return (char)(index + 'a');
    }

    private char specialIndexTranslation(int index){
        switch (index) {
            case NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 0:
                return 0x0105; //ą
            case NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 1:
                return 0x0107; //ć
            case NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 2:
                return 0x0119; //ę
            case NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 3:
                return 0x0142; //ł
            case NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 4:
                return 0x0114; //ń
            case NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 5:
                return 0x00f3; //ó
            case NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 6:
                return 0x015b; //ś
            case NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 7:
                return 0x017c; //ź
            case NUMBER_OF_LETTERS_IN_LATIN_ALPHABET + 8:
                return 0x0104; //ż
            default:
                return ' '; //garbage
        }
    }
}
