package com.javalexer.analysis.semantics;

import com.javalexer.analysis.lexing.Token;
import com.javalexer.analysis.parsing.nodes.AbsNode;
import com.javalexer.analysis.parsing.nodes.BinaryExpressionNode;
import com.javalexer.analysis.parsing.nodes.LiteralNode;
import com.javalexer.analysis.semantics.nodes.BoundBinaryExpressionNode;
import com.javalexer.analysis.semantics.nodes.BoundLiteralNode;
import com.javalexer.enums.BoundOperatorType;

import static com.javalexer.enums.SyntaxType.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NodeTypeBinderTest {

    private NodeTypeBinder cut;

    @Before
    public void setUp() {
        cut = new NodeTypeBinder();
    }

    @After
    public void tearDown() {
        cut = null;
    }

    @Test
    public void testBindNumericalExpression() throws Exception {
        AbsNode unboundRoot = buildNumericalInfixExpression();
        BoundBinaryExpressionNode boundRoot = (BoundBinaryExpressionNode) cut.bind(unboundRoot);

        BoundLiteralNode leftBoundNode = (BoundLiteralNode) boundRoot.getLeftNode();
        BoundOperatorType operator = boundRoot.getOperatorType();
        BoundLiteralNode rightBoundNode = (BoundLiteralNode) boundRoot.getRightNode();

        assertTrue(leftBoundNode.getToken().getSyntaxType() == NUMBER);
        /*
         * This assertion will matter the most when adding more operation types.
         * First, it detects the expression is truly numerical in nature.
         * Then knows to apply the correct math operation, not concatenation or other operations later on.
         */
        assertTrue(operator == BoundOperatorType.ADDITION);

        assertTrue(rightBoundNode.getToken().getSyntaxType() == NUMBER);
    }

    private AbsNode buildNumericalInfixExpression() {
        Token leftToken = new Token(NUMBER, 1, 0);
        Token operatorToken = new Token(PLUS, "+", 1);
        Token rightToken = new Token(NUMBER, 2, 2);

        LiteralNode leftNode = new LiteralNode(leftToken);
        LiteralNode rightNode = new LiteralNode(rightToken);
        return new BinaryExpressionNode(leftNode, operatorToken, rightNode);
    }
}