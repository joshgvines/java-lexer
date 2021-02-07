package main.com.javalexer.analysis.semantics.nodes;

import main.com.javalexer.enums.NodeType;

public class BoundParenthesizedExpressionNode extends AbsBoundBinaryNode {

    public BoundParenthesizedExpressionNode(AbsBoundNode left, AbsBoundNode expression, AbsBoundNode right) {
        super(NodeType.PARENTHESES_EXPRESSION, left, expression, right);
    }

    @Override
    public AbsBoundNode getRightNode() {
        return super.getRightNode();
    }

    @Override
    public AbsBoundNode getExpressionNode() {
        return super.getExpressionNode();
    }

    @Override
    public AbsBoundNode getLeftNode() {
        return super.getRightNode();
    }

}
