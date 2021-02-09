package com.javalexer.analysis.parsing;

import com.javalexer.analysis.lexing.Lexer;
import com.javalexer.analysis.lexing.Token;
import com.javalexer.analysis.parsing.nodes.BinaryExpressionNode;

import com.javalexer.analysis.parsing.nodes.LiteralNode;

import static com.javalexer.enums.SyntaxType.*;

import com.javalexer.enums.SyntaxType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class InfixParserTest {

    private Lexer lexer;
    private InfixParser cut;

    @Before
    public void setUp() {
        lexer = new Lexer();
    }

    @After
    public void tearDown() {
        lexer = null;
        cut = null;
    }

    @Test
    public void testParsingBooleanExpression() throws Exception {
        final String BOOLEAN_EXPRESSION = "false || true";
        List<Token> tokenList = lexer.lex(BOOLEAN_EXPRESSION);
        cut = new InfixParser(tokenList, false);
        assertExpressionNodeTypes(FALSE_KEYWORD, OR, TRUE_KEYWORD);
    }

    @Test
    public void testParsingNumericalExpression() throws Exception {
        final String NUMERICAL_EXPRESSION = "1 - 2";
        List<Token> tokenList = lexer.lex(NUMERICAL_EXPRESSION);
        cut = new InfixParser(tokenList, false);
        assertExpressionNodeTypes(NUMBER, MINUS, NUMBER);
    }

    private void assertExpressionNodeTypes(SyntaxType leftType,
                                           SyntaxType operatorType,
                                           SyntaxType rightType) throws Exception {

        BinaryExpressionNode rootNode = (BinaryExpressionNode) cut.parseForRoot();
        LiteralNode left = (LiteralNode) rootNode.getLeftNode();
        Token operator = rootNode.getToken();
        LiteralNode right = (LiteralNode) rootNode.getRightNode();

        assertTrue(left.getToken().getSyntaxType() == leftType);
        assertTrue(operator.getSyntaxType() == operatorType);
        assertTrue(right.getToken().getSyntaxType() == rightType);
    }

}