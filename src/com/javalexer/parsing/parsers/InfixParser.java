package com.javalexer.parsing.parsers;

import com.javalexer.analyzers.Precedence;
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
        return new InfixExpressionTree(parseExpression(0));
    }

    public AbsBinaryNode parseForRoot() {
        return parseExpression(0);
    }

    public AbsBinaryNode parseExpression(int newPrecedence) {
        int parentPrecedence = 0;
        if (newPrecedence != 0) {
            parentPrecedence = newPrecedence;
        }
        AbsBinaryNode leftNode = parsePrimaryExpression();
        while(true) {
            int precedence = Precedence.precedence(peek(0).type);
            if (precedence == 0 || precedence <= parentPrecedence) {
                break;
            }
            Token operatorToken = nextToken();
            AbsBinaryNode rightNode = parseExpression(precedence);
            leftNode = new OperatorNode(leftNode, operatorToken, rightNode);
        }
        return leftNode;
    }

    private AbsBinaryNode parsePrimaryExpression() {
        if (peek(0).type == OPEN_PAREN) {
            return parseParentheses();
        }
        Token numberToken = match(NUMBER);
        return new LiteralNode(numberToken);
    }

    private AbsBinaryNode parseParentheses() {
        AbsBinaryNode leftNode = new LiteralNode(nextToken());
        AbsBinaryNode expressionRootNode = parseExpression(0);
        AbsBinaryNode rightNode = new LiteralNode(match(CLOSE_PAREN));
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
