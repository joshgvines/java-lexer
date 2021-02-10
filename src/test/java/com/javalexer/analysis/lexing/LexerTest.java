package com.javalexer.analysis.lexing;

import org.junit.*;

import java.util.List;

import static com.javalexer.enums.SyntaxType.*;
import static org.junit.Assert.assertTrue;

public class LexerTest {

    private Lexer cut;

    @Before
    public void setUp() {
        cut = new Lexer();
    }

    @After
    public void tearDown() {
        cut = null;
    }

    /**
     * Confirm true and false tokens are created regardless or order or validity.
     */
    @Test
    public void testBooleanKeywordTokensCreated() throws Exception {
        final String BOOLEAN_KEYWORDS = "true false false true";
        List<Token> tokenList = cut.lex(BOOLEAN_KEYWORDS);
        assertTrue(tokenList.get(0).getSyntaxType() == TRUE_KEYWORD);
        assertTrue(tokenList.get(1).getSyntaxType() == WHITESPACE);
        assertTrue(tokenList.get(2).getSyntaxType() == FALSE_KEYWORD);
        assertTrue(tokenList.get(3).getSyntaxType() == WHITESPACE);
        assertTrue(tokenList.get(4).getSyntaxType() == FALSE_KEYWORD);
        assertTrue(tokenList.get(5).getSyntaxType() == WHITESPACE);
        assertTrue(tokenList.get(6).getSyntaxType() == TRUE_KEYWORD);
    }

    /**
     * Confirm boolean operator tokens are created.
     */
    @Test
    public void testBooleanOperatorTokensCreated() throws Exception {
        final String BOOLEAN_OUT_OPERATORS = "|| && ! != ==";
        List<Token> tokenList = cut.lex(BOOLEAN_OUT_OPERATORS);
        assertTrue(tokenList.get(0).getSyntaxType() == OR);
        assertTrue(tokenList.get(1).getSyntaxType() == WHITESPACE);
        assertTrue(tokenList.get(2).getSyntaxType() == AND);
        assertTrue(tokenList.get(3).getSyntaxType() == WHITESPACE);
        assertTrue(tokenList.get(4).getSyntaxType() == BANG);
        assertTrue(tokenList.get(5).getSyntaxType() == WHITESPACE);
        assertTrue(tokenList.get(6).getSyntaxType() == BANG_NOT_EQUALS);
        assertTrue(tokenList.get(7).getSyntaxType() == WHITESPACE);
        assertTrue(tokenList.get(8).getSyntaxType() == EQUALS_COMPARE);
    }

    /**
     * Confirm sign/possible numerical operator tokens are created.
     */
    @Test
    public void testSignOperatorTokensCreated() throws Exception {
        final String BOOLEAN_OUT_OPERATORS = "+ - * / %";
        List<Token> tokenList = cut.lex(BOOLEAN_OUT_OPERATORS);
        assertTrue(tokenList.get(0).getSyntaxType() == PLUS);
        assertTrue(tokenList.get(1).getSyntaxType() == WHITESPACE);
        assertTrue(tokenList.get(2).getSyntaxType() == MINUS);
        assertTrue(tokenList.get(3).getSyntaxType() == WHITESPACE);
        assertTrue(tokenList.get(4).getSyntaxType() == STAR);
        assertTrue(tokenList.get(5).getSyntaxType() == WHITESPACE);
        assertTrue(tokenList.get(6).getSyntaxType() == FORWARD_SLASH);
        assertTrue(tokenList.get(7).getSyntaxType() == WHITESPACE);
        assertTrue(tokenList.get(8).getSyntaxType() == MODULO);
    }

    /**
     * Confirm sign/possible numerical operator tokens are created in a binary expression.
     */
    @Test
    public void testExpressionTokensCreated() throws Exception {
        final String BINARY_EXPRESSION = "1 + 2";
        List<Token> tokenList = cut.lex(BINARY_EXPRESSION);

        assertTrue(tokenList.get(0).getSyntaxType() == NUMBER);
        assertTrue(Double.valueOf((String) tokenList.get(0).getValue()) == 1);

        assertTrue(tokenList.get(1).getSyntaxType() == WHITESPACE);
        assertTrue(tokenList.get(2).getSyntaxType() == PLUS);
        assertTrue(tokenList.get(3).getSyntaxType() == WHITESPACE);

        assertTrue(tokenList.get(4).getSyntaxType() == NUMBER);
        assertTrue(Double.valueOf((String) tokenList.get(4).getValue()) == 2);
    }

    /**
     * Confirm characters with no meaning are converted to unknown tokens.
     */
    @Test
    public void testUnknownTokenCreated() throws Exception {
        final String RANDOM_CHARACTERS = "~ @ `";
        List<Token> tokenList = cut.lex(RANDOM_CHARACTERS);
        assertTrue(tokenList.get(0).getSyntaxType() == UNKNOWN);
        assertTrue(tokenList.get(2).getSyntaxType() == UNKNOWN);
        assertTrue(tokenList.get(4).getSyntaxType() == UNKNOWN);
    }

}