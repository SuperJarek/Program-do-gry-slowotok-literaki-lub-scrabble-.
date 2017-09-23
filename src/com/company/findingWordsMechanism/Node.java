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

    public void checkNextNode(String word){
        if(isFinalLeter(word)){ // !!! poziom abstrakcj
            markWord();
            return;
        }
        else {
            findNextNode(word);
        }
    }
    private boolean isFinalLeter(String word){
        return depth == word.length();
    }
    private void markWord()
    {
        isWord = true;
    }
    private void findNextNode(String word)
    {
        char nextLetterFromWord = word.charAt(depth + 1); // już nie ten poziom abstrakcji, zmień
        int index = createIndexFromLetter(nextLetterFromWord);
        Node childNode = children[index];

        if (childNode.isNull()){
           childNode = createChildNode(index, this);
        }
        childNode.checkNextNode(word);
    }
    private int createIndexFromLetter(char letter){
        if(isLetterFromLatinAlphabet(letter)) {
            return createIndexFromLatinLetter(letter);
        }
        else
            return createIndexFromSpecialLetter(letter);
    }
    private boolean isLetterFromLatinAlphabet(char letter){
        return (letter >= 'a' && letter <= 'z');
    }
    private int createIndexFromLatinLetter(char letter){
        return letter - 'a';
    }
    private int createIndexFromSpecialLetter(char letter){ // TO DO!
        return 0;
    }
    private boolean isNull() {
        return this == null;
    }
    private Node createChildNode(int index, Node parent){
        children[index] = createNode(parent);
        return children[index];
    }
}
