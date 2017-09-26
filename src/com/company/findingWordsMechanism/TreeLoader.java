package com.company.findingWordsMechanism;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by Jarek on 2017-09-24.
 */
public class TreeLoader {
    static private int START_OF_TABLE = 0;
    static private int NUMBER_OF_LETTERS_IN_LATIN_ALPHABET = 26;

    public void loadTree(Tree tree) {
        try {
            FileInputStream fileStream = new FileInputStream("words/slowa.txt");
            Scanner scanner = new Scanner(fileStream);
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                int indexes[] = translateWordToIndexes(word);
                tree.addWord(indexes);
            }
            scanner.close();
        } catch (Exception e) {
            printError(e);
        }
    }

    private int[] translateWordToIndexes(String word) {
        int indexes[] = initializeTableForIndexes(word);
        int pointer = START_OF_TABLE;
        while(pointer < word.length()){
            char letter = word.charAt(pointer);
            indexes[pointer] = translateLetterToIndex(letter);
            pointer++;
        }
        return indexes;
    }

    private int translateLetterToIndex(char letter){
        int index;
        if(isLatin(letter)){
            index = latinLetterTranslation(letter);
        }
        else
            index = specialLetterTranslation(letter);
        return index;
    }

    private boolean isLatin(char letter) {
        return ( letter >= 'a' && letter <= 'z' );
    }

    private int latinLetterTranslation(char letter) {
        return letter - 'a';
    }

    private int specialLetterTranslation(char letter) {
        switch(letter){
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

    private int[] initializeTableForIndexes(String word){
        int length = word.length();
        return new int[length];
    }

    private void read(Scanner scanner){
        System.out.println(scanner.nextLine());
    }
    private void printError(Exception e){
        System.err.println("Error: " + e.getMessage());
    }
}
