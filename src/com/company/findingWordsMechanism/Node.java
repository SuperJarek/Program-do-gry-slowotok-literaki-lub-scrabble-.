package com.company.findingWordsMechanism;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Jarek on 2017-09-23.
 */
public class Node {
    boolean isWord;
    Node parent;
    int depth;
    List<Node> children;
    int index;
    final static int NUMBER_OF_LETTERS = 50; // Not sure how many polish alphabet has

    public int getDepth() {
        return depth;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    static public Node createNode(Node parent, int index) {
        return new Node(parent, index);
    }

    private Node(Node parent, int index) {
        isWord = false;
        this.parent = parent;
        setDepthFromParent(parent);
        children = new ArrayList<Node>();
        addEmptyChildren();
        this.index = index;
    }

    private void setDepthFromParent(Node parent) {
        this.depth = parent.getDepth() + 1;
    }

    private void addEmptyChildren() {
        for (int i = 0; i < NUMBER_OF_LETTERS; i++)
            children.add(null);
    }

    public static Node createRoot() {
        return new Node();
    }

    private Node() {
        setDepthForRoot();
        children = new ArrayList<Node>();
        addEmptyChildren();
    }

    private void setDepthForRoot() {
        depth = -1; // Root nie zawiera żadnej litery, więc jego indeks/głebokość musi być poza tablicą wyrazu ENG!!
    }

    public void addWord(int indexes[]) {
        if (isFinalLetter(indexes)) {
            markWord();
            return;
        } else {
            findNextNode(indexes);
        }
    }

    private boolean isFinalLetter(int indexes[]) {
        return depth == indexes.length - 1; // Depth counts from 0, length counts from 1
    }

    private void markWord() {
        isWord = true;
    }

    private void findNextNode(int indexes[]) {
        int nextIndex = getNextIndex(indexes);
        Node childNode = children.get(nextIndex);

        if (isNodeNull(childNode)) {
            childNode = createChildNode(nextIndex, this);
            children.set(nextIndex, childNode);
        }
        childNode.addWord(indexes);
    }

    private boolean isNodeNull(Node node) {
        return node == null;
    }

    private int getNextIndex(int indexes[]) {
        return indexes[depth + 1];
    }

    private Node createChildNode(int index, Node parent) {
        Node childNode = createNode(parent, index);
        return childNode;
    }

    public void findWord(List<Integer> randomIndexes, List<int[]> indexesFromWords) {
        if (isWord) {
            addWordToList(indexesFromWords);
        }
        findWordInNextNode(randomIndexes, indexesFromWords);
    }

    private void addWordToList(List<int[]> indexesFromWords) {
        int[] indexes = new int[getLengthOfIndexes()];
        collectIndexes(indexes);
        indexesFromWords.add(indexes);
    }

    private int getLengthOfIndexes() {
        return depth + 1; //depth should be last index number
    }

    private void collectIndexes(int[] indexes) {
        if (isRoot())
            return;
        indexes[depth] = index;
        parent.collectIndexes(indexes);
    }

    private boolean isRoot() {
        return depth == -1;
    }

    private void findWordInNextNode(List<Integer> randomIndexes, List<int[]> words) {

        Set<Integer> uniqueIndexes = new HashSet<Integer>(randomIndexes);
        for (Integer index : uniqueIndexes) {
            List<Integer> newRandomIndexes = new ArrayList<Integer>(randomIndexes);
            newRandomIndexes.remove(index);
            Node nextNode = children.get(index);
            if(nodeExists(nextNode)) {
                nextNode.findWord(newRandomIndexes, words);
            }
        }
    }

    private boolean nodeExists(Node node) {
        return node != null;
    }
}

