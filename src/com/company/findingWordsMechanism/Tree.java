package com.company.findingWordsMechanism;

import java.util.ArrayList;

/**
 * Created by Jarek on 2017-09-23.
 */
public class Tree<T> {
    private Node<T> root;

    public Tree (T rootData){
        root = new Node<T>();
        root.data = rootData;
        root.children = new ArrayList<Node<T>>();
    }
}
