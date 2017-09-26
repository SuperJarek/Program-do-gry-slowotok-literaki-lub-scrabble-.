package com.company;

import com.company.findingWordsMechanism.Tree;
import com.company.findingWordsMechanism.TreeManager;

import java.io.Console;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        Tree tree = new Tree();
        TreeManager manager = new TreeManager();
        manager.load(tree);

        List<String> list = manager.findWords(tree, "jarek");
        for (String word : list) {
            System.out.println(word);
        }
    }

}
