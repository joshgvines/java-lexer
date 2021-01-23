package com.javalexer.parsing.trees;

import com.javalexer.analyzers.Token;
import com.javalexer.enums.TokenType;
import com.javalexer.parsing.nodes.Node;
import com.javalexer.parsing.nodes.OperandNode;
import com.javalexer.parsing.nodes.OperatorNode;

import java.util.List;

import static com.javalexer.enums.TokenType.*;

public class InfixExpressionTree {

    private OperatorNode root = null;

    public boolean isLeaf(Node node) {
        return (node instanceof OperandNode);
    }

}
