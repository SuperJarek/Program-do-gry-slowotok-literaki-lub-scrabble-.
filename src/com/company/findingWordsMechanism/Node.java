package com.company.findingWordsMechanism;

import java.util.List;

/**
 * Created by Jarek on 2017-09-23.
 */
public class Node {
    boolean isWord;
    Node[] children;
    Node parent;
    int depth;
    final static int QUANTITY_OF_LETTERS = 50; // Not sure how many polish alphabet has

    public int getDepth() {
        return depth;
    }
    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node createNode(Node parent)
    {
        return new Node(parent);
    }
    private Node(Node parent)
    {
        isWord = false;
        this.parent = parent;
        setDepthFromParent(parent);
        children = new Node[QUANTITY_OF_LETTERS];
    }
    private void setDepthFromParent(Node parent) {
        this.depth = parent.getDepth() + 1;
    }

    public Node createRoot()
    {
        return new Node();
    }
    private Node()
    {
        setDepthForRoot(depth);
    }
    private void setDepthForRoot(int depth){
        depth = -1; // Root nie zawiera żadnej litery, więc jego indeks/głebokość musi być poza tablicą wyrazu ENG!!
    }

    public void checkNextNode(int indexes[]){
        if(isFinalLetter(indexes)){
            markWord();
            return;
        }
        else {
            findNextNode(indexes);
        }
    }
    private boolean isFinalLetter(int indexes[]){
        return depth == indexes.length;
    }
    private void markWord()
    {
        isWord = true;
    }
    private void findNextNode(int indexes[])
    {
        int nextIndex = getNextIndex(indexes);
        Node childNode = children[nextIndex];

        if (childNode.isNull()){
           childNode = createChildNode(nextIndex, this);
        }
        childNode.checkNextNode(indexes);
    }
    private int getNextIndex(int indexes[]) {
        return indexes[depth + 1];
    }
    private boolean isNull() {
        return this == null;
    }
    private Node createChildNode(int index, Node parent){
        children[index] = createNode(parent);
        return children[index];
    }
}
