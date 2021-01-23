package com.javalexer.parsing.trees;

import com.javalexer.parsing.nodes.MyNode;
import com.javalexer.parsing.nodes.OperandNode;
import com.javalexer.parsing.nodes.OperatorNode;

public class InfixExpressionTree {

    private OperatorNode root = null;

    public boolean isLeaf(MyNode myNode) {
        return (myNode instanceof OperandNode);
    }

}
