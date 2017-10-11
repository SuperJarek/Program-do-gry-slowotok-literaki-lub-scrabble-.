package com.company.findingWordsMechanism;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Jarek on 2017-09-23.
 */

public class Node {
    /* Each node holds one letter of the word, the next letter of the word
    is placed in the child of this node, since children are kept in arrayList,
    letters are translated to indexes of arrayList, each letter has unique index
    Depth of nodes defines the index of letter in a string.
    Nodes which contains end of some word, are marked by "isWord".
    Root doesn't hold any index.
//    Root in finding/adding a word operates on array called "indexes",
//    which is a word translated to indexes.
    */
    private int index;
    private int depth;
    private boolean endOfPath;
    private Node parent;
    private List<Node> children;


    private static int NUMBER_OF_LETTERS = 36; // Number of letters in polish alphabet
    static int ROOT_DEPTH = -1; // Root depth should out of range of array with a word

    private int getDepth() {
        return depth;
    }

    private Node(Node parent, int index) {
        this.index = index;
        depth = parent.getDepth() + 1;
        endOfPath = false;
        this.parent = parent;
        children = new ArrayList<>();
        addEmptyChildren();

        parent.children.set(index, this);
    }

    private void addEmptyChildren() {
        for (int i = 0; i < NUMBER_OF_LETTERS; i++) {
            children.add(null);
        }
    }


    public Node() {
        depth = ROOT_DEPTH;
        children = new ArrayList<Node>();
        addEmptyChildren();
    }


    public void loadPath(int path[]) {
        if (isEndOfPath(path)) {
            endOfPath = true;
        }else { // ensures that path exists in tree - creates necessary node if not
            int nextIndex = path[depth + 1];
            Node nextNode = children.get(nextIndex);
            if (nextNode == null) {
                nextNode = new Node(this, nextIndex);
            }
            nextNode.loadPath(path);
        }
    }

    private boolean isEndOfPath(int path[]) {
        return depth == path.length - 1; // is depth of Node equal last index of path
    }


    /*Find paths containing indexes from randomIndexes list only.
    Same index can appear in path maximum as many times as it does in randomIndexes list. */
    public void findPaths(List<Integer> randomIndexes, List<int[]> paths) {
        if (endOfPath) {
            addPathToList(paths);
        } // Check if there is longer path
        findPathsInNextNode(randomIndexes, paths);
    }

    private void addPathToList(List<int[]> paths) {
        int[] path = new int[depth + 1];
        composePath(path);
        paths.add(path);
    }

    private void composePath(int[] path) {
        if (depth == ROOT_DEPTH) {
            return;
        }
        path[depth] = index;
        parent.composePath(path);
    }

    private void findPathsInNextNode(List<Integer> randomIndexes, List<int[]> paths) {
        Set<Integer> uniqueRandomIndexes = new HashSet<>(randomIndexes);
        for (Integer index : uniqueRandomIndexes) {
            List<Integer> newRandomIndexes = new ArrayList<>(randomIndexes);
            newRandomIndexes.remove(index);
            Node nextNode = children.get(index);
            if (nextNode != null) {
                nextNode.findPaths(newRandomIndexes, paths);
            }
        }
    }

    private boolean nodeExists(Node node) {
        return node != null;
    }
}

