package com.javalexer.parsing;

public class BinaryNode {
    char data;
    public BinaryNode left, right;

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }
}
