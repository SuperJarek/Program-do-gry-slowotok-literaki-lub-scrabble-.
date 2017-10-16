package com.company;

import com.company.findingWordsMechanism.Tree;
import com.company.findingWordsMechanism.TreeManager;

import java.io.Console;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Tree tree = new Tree();
        TreeManager manager = new TreeManager();
        manager.load(tree);

        System.out.println("Tree loaded");

        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                String line = scanner.nextLine();
                List<String> list = manager.getWordList(tree, line);
                for (String word : list) {
                    System.out.println(word);
                }
                System.out.println(list.size());
            }
        } catch(IllegalStateException e) {

        }

    }

}
