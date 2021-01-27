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
    private List<Token> tokens;
    private List<String> diagnostics = new ArrayList<>();
    private int position = 0;
    private Token current;

    public InfixParser(final List<Token> tokens) {
        if (tokens.isEmpty()) {
            throw new IllegalStateException("Cannot Parse Without Tokens.");
        }
        this.tokens = new ArrayList<>();
        for (Token t : tokens) {
            if (t.type != WHITESPACE) {
                this.tokens.add(t);
            }
        }
    }

    public InfixExpressionTree parseForTree() {
        return new InfixExpressionTree(parseTerm());
    }

    public AbsBinaryNode parseForRoot() {
        return parseTerm();
    }

    private AbsBinaryNode parseTerm() {
        AbsBinaryNode leftNode = parseFactor();
        while(peek(0).type == PLUS || peek(0).type == MINUS) {
            Token operatorToken = nextToken();
            AbsBinaryNode rightNode = parseFactor();
            leftNode = new OperatorNode(leftNode, operatorToken, rightNode);
        }
        return leftNode;
    }

    private AbsBinaryNode parseFactor() {
        AbsBinaryNode leftNode = parsePrimaryExpression();
        while(peek(0).type == FORWARD_SLASH || peek(0).type == STAR) {
            Token operatorToken = nextToken();
            AbsBinaryNode rightNode = parsePrimaryExpression();
            leftNode = new OperatorNode(leftNode, operatorToken, rightNode);
        }
        return leftNode;
    }

    private AbsBinaryNode parsePrimaryExpression() {
        if (peek(0).type == OPEN_PAREN) {
            return parseParentheses();
        }
        Token numberToken = match(NUMBER);
        return new NumberNode(numberToken);
    }

    private AbsBinaryNode parseParentheses() {
        AbsBinaryNode leftNode = new NumberNode(nextToken());
        AbsBinaryNode expressionRootNode = parseTerm();
        AbsBinaryNode rightNode = new NumberNode(match(CLOSE_PAREN));
        return new ParenthesesExpressionNode(leftNode, expressionRootNode, rightNode);
    }

    private Token match(TokenType type) {
        if (peek(0).type == type) {
            return nextToken();
        }
        return new Token(current.type, null, current.position);
    }

    private Token nextToken() {
        current = peek(0);
        position++;
        if (current.type == END) {
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
