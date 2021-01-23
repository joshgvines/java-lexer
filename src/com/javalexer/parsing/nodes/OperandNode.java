package com.javalexer.parsing.nodes;

import com.javalexer.analyzers.Token;

/**
 * An OperandNode cannot have a right or left node, it must always be a leaf.
 */
public class OperandNode implements MyNode {

    private Token operand;

    public OperandNode() {
    }

    public OperandNode(Token operand) {
        this.operand = operand;
    }

    @Override
    public Token getData() {
        return operand;
    }

    @Override
    public void setData(Token operand) {
        this.operand = operand;
    }

    @Override
    public String toString() {
        return "OperandNode: [ " + operand.toString() + " ] ";
    }

}
