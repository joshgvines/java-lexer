package com.javalexer.parsing.parsers;

import com.javalexer.analyzers.Token;
import com.javalexer.enums.TokenType;
import com.javalexer.parsing.nodes.MyNode;
import com.javalexer.parsing.nodes.OperandNode;
import com.javalexer.parsing.nodes.OperatorMyNode;

import java.util.List;

import static com.javalexer.enums.TokenType.*;

/**
 * Parse infix expressions into an infix expression tree.
 */
public class InfixParser {
    private final List<Token> tokens;
    private int position = 0;
    private Token current;

    public InfixParser(final List<Token> tokens) {
        this.tokens = tokens;
        current = peek(0);
    }

    private Token match(TokenType type) {
        if (current.type == type) {
            return nextToken();
        }
        return new Token(type, null, position);
    }

    private OperandNode primaryExpression() {
        Token numberToken = match(NUMBER);
        return new OperandNode(numberToken);
    }

    public MyNode parse() {
        Token operator;
        System.out.println(current);
        MyNode left = primaryExpression();
        while (peek(1).type == PLUS || peek(1).type == MINUS) {
            operator = nextToken();
            System.out.println(operator);
            MyNode right = primaryExpression();
            left = new OperatorMyNode(operator, left, right);
        }
        return left;
    }

    private Token peek(int offset) {
        int i = position + offset;
        if (i >= tokens.size()) {
            return tokens.get(tokens.size() - 1);
        }
        return tokens.get(i);
    }

    private Token nextToken() {
        // Ignore spaces
        while ((current = tokens.get(position++)).type == SPACE) ;
        if (current.type == END) {
            position = 0;
            return null;
        }
        return current;
    }

    public void printAllTokens() {
        Token current;
        while ((current = nextToken()) != null) {
            System.out.println(current);
        }
    }

}
