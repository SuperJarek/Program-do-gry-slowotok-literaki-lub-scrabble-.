package com.company.findingWordsMechanism;

/**
 * Created by Jarek on 2017-09-23.
 */
public class Tree {
    private Node root;

    public Tree (){
        root = root.createRoot();
    }

    public void addWord(String word)
    {
        root.checkNextNode(word);
    }
}
