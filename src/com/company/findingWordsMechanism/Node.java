package com.company.findingWordsMechanism;

/**
 * Created by Jarek on 2017-09-23.
 */
public class Node {
    boolean isWord;
    Node parent;
    int depth;
    Node[] children;
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
        children = new Node[NUMBER_OF_LETTERS];
    }
    private void setDepthFromParent(Node parent) {
        this.depth = parent.getDepth() + 1;
    }

    public static Node createRoot()
    {
        return new Node();
    }
    private Node()
    {
        children = new Node[NUMBER_OF_LETTERS];
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
        return depth == indexes.length - 1; // KOMENT RÓŻNE POZIOMY ABSTRAKCJI
    }
    private void markWord()
    {
        isWord = true;
    }
    private void findNextNode(int indexes[])
    {
        int nextIndex = getNextIndex(indexes);
        Node childNode = children[nextIndex];

        if (isNodeNull(childNode)){
           childNode = createChildNode(nextIndex, this);
        }
        childNode.checkNextNode(indexes);
    }

    private boolean isNodeNull(Node node) {
        return node == null;
    }

    private int getNextIndex(int indexes[]) {
        if(isRoot())
            return indexes[0];
        else
            return indexes[depth + 1];
    }

    private boolean isRoot() {
        return depth == -1;
    }

    private Node createChildNode(int index, Node parent){
        children[index] = createNode(parent);
        return children[index];
    }
}
