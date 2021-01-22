package com.javalexer.parsing.trees;

import com.javalexer.analyzers.Token;
import com.javalexer.parsing.nodes.Node;
import com.javalexer.parsing.nodes.OperandNode;
import com.javalexer.parsing.nodes.OperatorNode;

import java.util.List;

public class InfixExpressionTree {

    private OperatorNode root = null;

    public boolean isLeaf(Node node) {
        return (node instanceof OperandNode);
    }

//    public Node parse(List<Token> tokens) {
//
//        for (Token token : tokens) {
//
//
//
//
//
//        }
//
//    }

}
