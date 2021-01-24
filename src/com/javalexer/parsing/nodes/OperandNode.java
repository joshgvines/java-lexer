package com.javalexer.parsing.nodes;

import com.javalexer.analyzers.Token;

/**
 * An OperandNode cannot have a right or left node, it must always be a leaf.
 */
public class OperandNode extends AbsBinaryNode {

    private Token operand;

    public OperandNode(Token operand) {
        super(operand);
        this.operand = operand;
    }

    @Override
    public Token data() {
        return operand;
    }

    @Override
    public void setData(Token operand) {
        this.operand = operand;
    }

    @Override
    public String toString() {
        return "OperandNode:[ " + operand + " ]";
    }

}
