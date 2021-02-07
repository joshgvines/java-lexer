package main.com.javalexer.analysis.semantics.nodes;

import main.com.javalexer.enums.BoundOperatorType;
import main.com.javalexer.enums.NodeType;

public final class BoundUnaryExpressionNode extends AbsBoundUnaryNode {

    public BoundUnaryExpressionNode(BoundOperatorType operatorType, AbsBoundNode operand) {
        super(NodeType.UNARY_EXPRESSION, operatorType, operand);
    }

    @Override
    public BoundOperatorType getOperatorType() {
        return super.getOperatorType();
    }

    @Override
    public AbsBoundNode getOperandNode() {
        return super.getOperandNode();
    }

}
