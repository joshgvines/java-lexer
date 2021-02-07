package main.com.javalexer.analysis.parsing.nodes;

import main.com.javalexer.analysis.lexing.Token;
import main.com.javalexer.enums.NodeType;

/**
 * An OperatorNode has a right and left node, both of which can be an OperandNode or another OperatorNode.
 */
public final class BinaryExpressionNode extends AbsBinaryNode {

    public BinaryExpressionNode(AbsNode left, Token operator, AbsNode right) {
        super(NodeType.BINARY_EXPRESSION, left, operator, right);
    }

    @Override
    public NodeType getNodeType() {
        return super.getNodeType();
    }

    public AbsNode getLeftNode() {
        return super.getLeftNode();
    }

    public Token getToken() {
        return super.getToken();
    }

    public AbsNode getRightNode() {
        return super.getRightNode();
    }

}
