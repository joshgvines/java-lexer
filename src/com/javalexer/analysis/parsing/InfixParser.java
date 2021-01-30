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

    public AbsNode parseForRoot() {
        return parseExpression(0);
    }

    public AbsNode parseExpression(int newPrecedence) {
        int parentPrecedence = Precedence.defaultPrecedence(newPrecedence);
        AbsNode leftNode = decideExpressionType(parentPrecedence);
        while (true) {
            int precedence = Precedence.binaryPrecedence(peek(0).getType());
            if (precedence == 0 || precedence <= parentPrecedence) {
                break;
            }
            Token operatorToken = nextToken();
            AbsNode rightNode = parseExpression(precedence);
            leftNode = new BinaryNode(leftNode, operatorToken, rightNode);
        }
        return leftNode;
    }

    private AbsNode decideExpressionType(int parentPrecedence) {
        int unaryPrecedence = Precedence.unaryPrecedence(peek(0).getType());
        if (unaryPrecedence != 0 && unaryPrecedence > parentPrecedence) {
            return parseUnaryExpression(unaryPrecedence);
        } else {
            return parsePrimaryExpression();
        }
    }

    private AbsNode parsePrimaryExpression() {
        if (peek(0).getType() == OPEN_PAREN) {
            return parseParentheses();
        }
        Token numberToken = match(NUMBER);
        return new LiteralNode(numberToken);
    }

    private AbsNode parseUnaryExpression(int unaryPrecedence) {
        Token operatorToken = nextToken();
        AbsNode operandNode = parseExpression(unaryPrecedence);
        return new UnaryNode(operatorToken, operandNode);
    }

    private AbsNode parseParentheses() {
        AbsNode leftNode = new LiteralNode(nextToken());
        AbsNode expressionRootNode = parseExpression(0);
        AbsNode rightNode = new LiteralNode(match(CLOSE_PAREN));
        return new ExpressionNode(leftNode, expressionRootNode, rightNode);
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
