package com.javalexer.analysis.parsing.nodes;

import com.javalexer.analysis.lexing.Token;
import com.javalexer.enums.NodeType;

public final class UnaryExpressionNode extends AbsUnaryNode {

    public UnaryExpressionNode(Token operator, AbsNode operand) {
        super(NodeType.UNARY_EXPRESSION, operator, operand);
    }

    @Override
    public Token getOperatorToken() {
        return super.getOperatorToken();
    }

    @Override
    public AbsNode getOperandNode() {
        return super.getOperandNode();
    }

}
