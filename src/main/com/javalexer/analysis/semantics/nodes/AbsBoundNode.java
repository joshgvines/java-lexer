package main.com.javalexer.analysis.semantics.nodes;

import main.com.javalexer.enums.BoundOperatorType;
import main.com.javalexer.enums.NodeType;

/**
 * Represents nodes in a tree during the semantic analysis phase.
 * The nodes and structure become 'bound' after type checking.
 */
public abstract class AbsBoundNode {
    private NodeType type;

    public AbsBoundNode(NodeType type) {
        this.type = type;
    }

    public NodeType getBoundNodeType() {
        return type;
    }

}

abstract class AbsBoundBinaryNode extends AbsBoundNode {
    private AbsBoundNode left;
    private AbsBoundNode right;
    private AbsBoundNode expression;
    private BoundOperatorType operatorType;

    protected AbsBoundBinaryNode(NodeType type,
                                 AbsBoundNode left,
                                 BoundOperatorType operatorType,
                                 AbsBoundNode right) {
        super(type);
        this.left = left;
        this.operatorType = operatorType;
        this.right = right;
    }

    protected AbsBoundBinaryNode(NodeType type,
                                 AbsBoundNode left,
                                 AbsBoundNode expression,
                                 AbsBoundNode right) {
        super(type);
        this.left = left;
        this.expression = expression;
        this.right = right;
    }

    AbsBoundNode getLeftNode() {
        return left;
    }

    BoundOperatorType getOperatorType() {
        return operatorType;
    }

    AbsBoundNode getExpressionNode() {
        return expression;
    }

    AbsBoundNode getRightNode() {
        return right;
    }
}

abstract class AbsBoundUnaryNode extends AbsBoundNode {
    private BoundOperatorType operatorType;
    private AbsBoundNode operand;

    protected AbsBoundUnaryNode(NodeType type, BoundOperatorType operatorType, AbsBoundNode operand) {
        super(type);
        this.operatorType = operatorType;
        this.operand = operand;
    }

    BoundOperatorType getOperatorType() {
        return operatorType;
    }

    AbsBoundNode getOperandNode() {
        return operand;
    }
}
