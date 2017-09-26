package com.company;

import com.company.findingWordsMechanism.Tree;
import com.company.findingWordsMechanism.TreeLoader;


public class Main {

    public static void main(String[] args) {
        Tree tree = new Tree();
        TreeLoader loader = new TreeLoader();
        loader.loadTree(tree);
    }
}
