package com.javalexer.analysis.semantics.nodes;

import com.javalexer.analysis.lexing.Token;
import com.javalexer.analysis.parsing.nodes.AbsNode;
import com.javalexer.enums.NodeType;

public final class BoundUnaryExpressionNode extends AbsBoundUnaryNode {

    public BoundUnaryExpressionNode(Token operator, AbsBoundNode operand) {
        super(NodeType.UNARY_EXPRESSION, operator, operand);
    }

    @Override
    public Token getOperator() {
        return super.getOperator();
    }

    @Override
    public AbsBoundNode getOperand() {
        return super.getOperand();
    }

}
