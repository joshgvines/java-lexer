package com.javalexer.analysis.semantics.nodes;

import com.javalexer.analysis.lexing.Token;
import com.javalexer.analysis.parsing.nodes.AbsNode;
import com.javalexer.enums.BoundOperatorType;
import com.javalexer.enums.NodeType;

public final class BoundUnaryExpressionNode extends AbsBoundUnaryNode {

    public BoundUnaryExpressionNode(BoundOperatorType operatorType, AbsBoundNode operand) {
        super(NodeType.UNARY_EXPRESSION, operatorType, operand);
    }

    @Override
    public BoundOperatorType getOperator() {
        return super.getOperator();
    }

    @Override
    public AbsBoundNode getOperand() {
        return super.getOperand();
    }

}
