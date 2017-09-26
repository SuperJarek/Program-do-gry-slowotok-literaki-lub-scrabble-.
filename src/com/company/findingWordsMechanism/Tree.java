package com.company.findingWordsMechanism;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        root.addWord(indexes);
    }
    public List<int[]> findWords(List<Integer> randomIndexes){
        List<int[]> indexesFromWords = new ArrayList<int[]>();
        root.findWord(randomIndexes, indexesFromWords);
        return indexesFromWords;
    }
}
