package com.company.findingWordsMechanism;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Jarek on 2017-09-23.
 */
public class Tree {
    private Node root;

    public Tree() {
        root = new Node();
    }

    public void loadPath(int path[]) {
        root.loadPath(path);
    }

    public List<int[]> getPaths(List<Integer> randomIndexes) {
        List<int[]> paths = new ArrayList<>();
        root.findPaths(randomIndexes, paths);
        return paths;
    }
}
