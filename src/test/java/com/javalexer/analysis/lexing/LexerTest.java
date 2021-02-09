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

    @Test
    public void testBooleanKeywordTokensCreated() throws Exception {
        final String BOOLEAN_KEYWORDS = "true false";
        List<Token> tokenList = cut.lex(BOOLEAN_KEYWORDS);
        assertTrue(tokenList.get(0).getSyntaxType() == TRUE_KEYWORD);
        assertTrue(tokenList.get(1).getSyntaxType() == WHITESPACE);
        assertTrue(tokenList.get(2).getSyntaxType() == FALSE_KEYWORD);
    }

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

    @Test
    public void testUnknownTokenCreated() throws Exception {
        final String RANDOM_CHARACTERS = "~";
        List<Token> tokenList = cut.lex(RANDOM_CHARACTERS);
        assertTrue(tokenList.get(0).getSyntaxType() == UNKNOWN);
    }

}