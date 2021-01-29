package com.javalexer.analysis.parsing.trees;

import com.javalexer.analysis.lexing.Token;
import com.javalexer.analysis.parsing.nodes.AbsBinaryNode;
import com.javalexer.analysis.parsing.nodes.LiteralNode;
import com.javalexer.analysis.parsing.nodes.ParenthesesExpressionNode;
import com.javalexer.analysis.parsing.nodes.UnaryNode;
import com.javalexer.analysis.parsing.InfixParser;

import java.util.List;

public class InfixExpressionTree {

    private AbsBinaryNode root;

    public InfixExpressionTree() {
        this.root = null;
    }

    public InfixExpressionTree(AbsBinaryNode root) {
        this.root = root;
    }

    public boolean buildTree(List<Token> tokenList) {
        InfixParser parser = new InfixParser(tokenList);
        root = parser.parseForRoot();
        return (root != null);
    }

    public AbsBinaryNode getRoot() {
        return root;
    }

    public boolean isLeaf(AbsBinaryNode absBinaryNode) {
        return (absBinaryNode instanceof LiteralNode);
    }

    public boolean isParentheses(AbsBinaryNode absBinaryNode) {
        return (absBinaryNode instanceof ParenthesesExpressionNode);
    }

    public boolean isUnary(AbsBinaryNode absBinaryNode) {
        return (absBinaryNode instanceof UnaryNode);
    }

}
