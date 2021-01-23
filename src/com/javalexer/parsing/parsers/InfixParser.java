package com.javalexer.parsing.parsers;

import com.javalexer.analyzers.Token;
import com.javalexer.enums.TokenType;
import com.javalexer.parsing.ExpressionTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.javalexer.enums.TokenType.*;

/**
 * Parse infix expressions into an infix expression tree, then optionally evaluate.
 */
public class InfixParser {
    private final List<Token> tokens;
    private int position = 0;

    public InfixParser(final List<Token> tokens) {
        this.tokens = tokens;
    }

    public void parse() {
        Token token;
        while((token = nextToken()) != null) {
            //token
        }

    }

    private Token nextToken() {
        if (!(position == tokens.size())) {
            return tokens.get(position++);
        }
        return null;
    }

    public void printAllTokens() {
        Token current;
        while((current = nextToken()) != null) {
            System.out.println(current);
        }
    }

}
