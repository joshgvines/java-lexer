package com.javalexer.analysis.semantics.nodes;

import com.javalexer.analysis.lexing.Token;
import com.javalexer.enums.NodeType;

/**
 * Represents nodes in a tree during the semantic analysis phase.
 * The nodes and structure become 'bound' after type checking.
 */
public abstract class AbsBoundNode {

    private Token data;
    private NodeType type;
    private AbsBoundNode expression;
    private AbsBoundNode left;
    private AbsBoundNode right;

    public AbsBoundNode(NodeType type) {
        this.type = type;
    }

    public AbsBoundNode(Token data, AbsBoundNode operand) {
        this.data = data;
        this.expression = operand;
    }

    public AbsBoundNode(AbsBoundNode left, Token data, AbsBoundNode right) {
        this.right = right;
        this.data = data;
        this.left = left;
    }

    public AbsBoundNode(AbsBoundNode left, AbsBoundNode expression, AbsBoundNode right) {
        this.right = right;
        this.expression = expression;
        this.left = left;
    }

    public AbsBoundNode getExpression() {
        return expression;
    }

    public Token data() {
        return data;
    }

    public AbsBoundNode getLeft() {
        return left;
    }

    public AbsBoundNode getRight() {
        return right;
    }

}
