package com.company.findingWordsMechanism;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jarek on 2017-09-23.
 */
public class Node {
    boolean isWord;
    Node parent;
    int depth;
    List<Node> children;
    final static int NUMBER_OF_LETTERS = 50; // Not sure how many polish alphabet has

    public int getDepth() {
        return depth;
    }
    public void setParent(Node parent) {
        this.parent = parent;
    }

    static public Node createNode(Node parent)
    {
        return new Node(parent);
    }
    private Node(Node parent)
    {
        isWord = false;
        this.parent = parent;
        setDepthFromParent(parent);
        children = new ArrayList<Node>();
        addEmptyChildren();
    }
    private void setDepthFromParent(Node parent) {
        this.depth = parent.getDepth() + 1;
    }
    private void addEmptyChildren(){
        for(int i=0; i<NUMBER_OF_LETTERS; i++ )
            children.add(null);
    }

    public static Node createRoot()
    {
        return new Node();
    }
    private Node()
    {
        setDepthForRoot(depth);
        children = new ArrayList<Node>();
        addEmptyChildren();
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
        return depth == indexes.length - 1; // KOMENT RÓŻNE POZIOMY ABSTRAKCJI
    }
    private void markWord()
    {
        isWord = true;
    }
    private void findNextNode(int indexes[])
    {
        int nextIndex = getNextIndex(indexes);
        Node childNode = children.get(nextIndex);

        if (isNodeNull(childNode)){
           childNode = createChildNode(nextIndex, this);
        }
        childNode.checkNextNode(indexes);
    }

    private boolean isNodeNull(Node node) {
        return node == null;
    }

    private int getNextIndex(int indexes[]) {
            return indexes[depth + 1];
    }

    private Node createChildNode(int index, Node parent){
        Node childNode = createNode(parent);
        return childNode;
    }
}
