package com.javalexer.analysis.parsing.trees;

import com.javalexer.analysis.lexing.Token;
import com.javalexer.analysis.parsing.nodes.AbsNode;
import com.javalexer.analysis.parsing.nodes.LiteralNode;
import com.javalexer.analysis.parsing.nodes.ExpressionNode;
import com.javalexer.analysis.parsing.nodes.UnaryNode;
import com.javalexer.analysis.parsing.InfixParser;

import java.util.List;

public class InfixExpressionTree {

    private AbsNode root;

    public InfixExpressionTree() {
        this.root = null;
    }

    public InfixExpressionTree(AbsNode root) {
        this.root = root;
    }

    public boolean buildTree(List<Token> tokenList) {
        InfixParser parser = new InfixParser(tokenList);
        root = parser.parseForRoot();
        return (root != null);
    }

    public AbsNode getRoot() {
        return root;
    }

    public boolean isLeaf(AbsNode absNode) {
        return (absNode instanceof LiteralNode);
    }

    public boolean isParentheses(AbsNode absNode) {
        return (absNode instanceof ExpressionNode);
    }

    public boolean isUnary(AbsNode absNode) {
        return (absNode instanceof UnaryNode);
    }

}
