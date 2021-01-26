package com.javalexer.parsing.parsers;

import com.javalexer.analyzers.Token;
import com.javalexer.enums.TokenType;
import com.javalexer.parsing.nodes.*;
import com.javalexer.parsing.trees.InfixExpressionTree;

import java.util.ArrayList;
import java.util.List;

import static com.javalexer.enums.TokenType.*;

/**
 * Parse infix expressions into an expression tree.
 */
public class InfixParser {
    private final List<Token> tokens;
    private List<String> diagnostics = new ArrayList<>();
    private int position = 0;
    private Token current;

    public InfixParser(final List<Token> tokens) {
        this.tokens = tokens;
    }

    public InfixExpressionTree parseForTree() {
        return new InfixExpressionTree(parseTerm());
    }

    public AbsBinaryNode parseForRoot() {
        return parseTerm();
    }

    private AbsBinaryNode parseTerm() {
        AbsBinaryNode left = parseFactor();
        while(peek(1).type == PLUS || peek(1).type == MINUS) {
            left = new OperatorNode(left, nextToken(), parseFactor());
        }
        return left;
    }

    private AbsBinaryNode parseFactor() {
        AbsBinaryNode left = parsePrimaryExpression();
        while(peek(1).type == FORWARD_SLASH || peek(1).type == STAR) {
            left = new OperatorNode(left, nextToken(), parsePrimaryExpression());
        }
        return left;
    }

    private AbsBinaryNode parsePrimaryExpression() {
        if (getCurrent().type == OPEN_PAREN) {
            return parseParentheses();
        }
        Token numberToken = match(NUMBER);
        return new OperandNode(numberToken);
    }

    private AbsBinaryNode parseParentheses() {
        AbsBinaryNode left = new OperandNode(nextToken());
        AbsBinaryNode nestedExpressionRoot = parseTerm();
        AbsBinaryNode right = new OperandNode(match(CLOSE_PAREN));
        return new ParenthesesExpressionNode(left, nestedExpressionRoot, right);
    }

    private Token match(TokenType type) {
        if (current.type == type) {
            return nextToken();
        }
        System.out.println(current);
        return new Token(type, current.value, current.position);
    }

    private Token getCurrent() {
        if (current == null) {
            return nextToken();
        }
        return current;
    }

    private Token nextToken() {
        while ((current = tokens.get(position++)).type == WHITESPACE);
        if (current.type == END) {
            position = 0;
            return null;
        }
        return current;
    }

    private Token peek(int offset) {
        int index = position + offset;
        if (index >= tokens.size()) {
            return tokens.get(tokens.size() - 1);
        }
        return tokens.get(index);
    }

}
