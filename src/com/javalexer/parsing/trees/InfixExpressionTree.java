package com.javalexer.parsing.trees;

import com.javalexer.parsing.nodes.MyNode;
import com.javalexer.parsing.nodes.OperandNode;
import com.javalexer.parsing.nodes.OperatorMyNode;

public class InfixExpressionTree {

    private OperatorMyNode root = null;

    public boolean isLeaf(MyNode myNode) {
        return (myNode instanceof OperandNode);
    }

}
