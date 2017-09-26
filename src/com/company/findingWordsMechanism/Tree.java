package com.company.findingWordsMechanism;

/**
 * Created by Jarek on 2017-09-23.
 */
public class Tree {
    private Node root;

    public Tree (){
        root = Node.createRoot();
    }

    public void addWord(int indexes[])
    {
        root.checkNextNode(indexes);
    }
}
