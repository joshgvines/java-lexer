package com.javalexer.analysis;

import com.javalexer.analysis.parsing.trees.BoundInfixExpressionTree;
import com.javalexer.analysis.semantics.nodes.*;
import com.javalexer.enums.BoundOperatorType;
import com.javalexer.enums.SyntaxType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Evaluator {

    private static final Logger log = LogManager.getLogger(Evaluator.class);

    private BoundInfixExpressionTree expressionTree;

    public Evaluator(BoundInfixExpressionTree expressionTree) {
        this.expressionTree = expressionTree;
    }

    public Object evaluate() throws Exception {
        return evaluate(expressionTree.getRoot());
    }

    /**
     * Takes root node, traverses the tree recursively to evaluate expressions.
     *
     * @param node
     * @return solution
     * @throws Exception
     */
    private Object evaluate(AbsBoundNode node) throws Exception {
        if (node == null) {
            log.error("Bound Node was found null during evaluation.");
            throw new Exception("Expression node was null");
        }
        switch (node.getBoundNodeType()) {
            case LITERAL:
                BoundLiteralNode boundLiteralNode = (BoundLiteralNode) node;
                if (boundLiteralNode.getSyntaxType() != SyntaxType.NUMBER) {
                    return boundLiteralNode.getToken().getValue();
                }
                return toDouble(boundLiteralNode.getToken().getValue());
            case BINARY_EXPRESSION:
                return buildBinaryComputation((BoundBinaryExpressionNode) node);
            case PARENTHESES_EXPRESSION:
                return evaluate(((BoundParenthesizedExpressionNode) node).getExpressionNode());
            case UNARY_EXPRESSION:
                return buildUnaryComputation((BoundUnaryExpressionNode) node);
            default:
                throw new UnsupportedOperationException("Unknown node type: " + node.getBoundNodeType());
        }
    }

    private Object buildUnaryComputation(BoundUnaryExpressionNode boundUnaryExpression) throws Exception {
        BoundOperatorType operatorType = boundUnaryExpression.getOperatorType();
        Object operand = evaluate(boundUnaryExpression.getOperandNode());
        return computeUnary(operatorType, operand);
    }

    private Object buildBinaryComputation(BoundBinaryExpressionNode binaryExpression) throws Exception {
        BoundOperatorType operatorType = binaryExpression.getOperatorType();
        Object a = evaluate(binaryExpression.getLeftNode());
        Object b = evaluate(binaryExpression.getRightNode());
        return computeBinary(operatorType, a, b);
    }

    private Object computeUnary(BoundOperatorType operator, Object operand) {
        switch (operator) {
            case ADDITION:
                return +((double) operand);
            case SUBTRACTION:
                return -((double) operand);
            case LOGIC_NOT:
                return !Boolean.parseBoolean(String.valueOf(operand));
        }
        return 0;
    }

    private Object computeBinary(BoundOperatorType operator, Object a, Object b) {
        switch (operator) {
            case ADDITION: return (double) a + (double) b;
            case SUBTRACTION: return (double) a - (double) b;
            case MULTIPLICATION: return (double) a * (double) b;
            case MODULO: return (double) a % (double) b;
            case DIVISION:
                if ((double) b == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                return (double) a / (double) b;

            case LOGIC_AND: return (boolean) a && (boolean) b;
            case LOGIC_OR: return (boolean) a || (boolean) b;
            case LOGIC_NOT_EQUALS:
                if (a instanceof Double) {
                    return (double) a != (double) b;
                }
                return (a != b);
            case LOGIC_EQUALS_COMPARE:
                if (a instanceof Double) {
                    return (double) a == (double) b;
                }
                return (a == b);
        }
        return 0;
    }

    private double toDouble(Object number) {
        if (number == null || !(number instanceof String)) {
            return 0;
        }
        return Double.parseDouble((String) number);
    }

}
