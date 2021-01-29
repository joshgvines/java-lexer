package com.javalexer.analysis.parsing;

import com.javalexer.analysis.lexing.Token;
import com.javalexer.enums.TokenType;
import com.javalexer.analysis.parsing.nodes.*;
import com.javalexer.analysis.parsing.trees.InfixExpressionTree;

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
        for (Token token : tokens) {
            if (token.getType() != WHITESPACE) {
                this.tokens.add(token);
            }
        }
    }

    public InfixExpressionTree parseForTree() {
        return new InfixExpressionTree(parseExpression(0));
    }

    public AbsBinaryNode parseForRoot() {
        return parseExpression(0);
    }

    private int defaultPrecedence(int newPrecedence) {
        int parentPrecedence = 0;
        if (newPrecedence != 0) {
            parentPrecedence = newPrecedence;
        }
        return parentPrecedence;
    }

    public AbsBinaryNode parseExpression(int newPrecedence) {
        int parentPrecedence = defaultPrecedence(newPrecedence);
        AbsBinaryNode leftNode = decideExpressionType(parentPrecedence);
        while (true) {
            int precedence = Precedence.binaryPrecedence(peek(0).getType());
            if (precedence == 0 || precedence <= parentPrecedence) {
                break;
            }
            Token operatorToken = nextToken();
            AbsBinaryNode rightNode = parseExpression(precedence);
            leftNode = new OperatorNode(leftNode, operatorToken, rightNode);
        }
        return leftNode;
    }

    private AbsBinaryNode decideExpressionType(int parentPrecedence) {
        int unaryPrecedence = Precedence.unaryPrecedence(peek(0).getType());
        if (unaryPrecedence != 0 && unaryPrecedence > parentPrecedence) {
            return parseUnaryExpression(unaryPrecedence);
        } else {
            return parsePrimaryExpression();
        }
    }

    private AbsBinaryNode parsePrimaryExpression() {
        if (peek(0).getType() == OPEN_PAREN) {
            return parseParentheses();
        }
        Token numberToken = match(NUMBER);
        return new LiteralNode(numberToken);
    }

    private AbsBinaryNode parseUnaryExpression(int unaryPrecedence) {
        Token operatorToken = nextToken();
        AbsBinaryNode operandNode = parseExpression(unaryPrecedence);
        return new UnaryNode(operatorToken, operandNode);
    }

    private AbsBinaryNode parseParentheses() {
        AbsBinaryNode leftNode = new LiteralNode(nextToken());
        AbsBinaryNode expressionRootNode = parseExpression(0);
        AbsBinaryNode rightNode = new LiteralNode(match(CLOSE_PAREN));
        return new ParenthesesExpressionNode(leftNode, expressionRootNode, rightNode);
    }

    private Token match(TokenType type) {
        if (peek(0).getType() == type) {
            return nextToken();
        }
        return new Token(current.getType(), null, current.getPosition());
    }

    private Token nextToken() {
        current = peek(0);
        position++;
        if (current.getType() == END) {
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
