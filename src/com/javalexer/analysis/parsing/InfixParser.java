package com.javalexer.analysis.parsing;

import com.javalexer.analysis.lexing.Token;
import com.javalexer.enums.SyntaxType;
import com.javalexer.analysis.parsing.nodes.*;

import java.util.ArrayList;
import java.util.List;

import static com.javalexer.enums.SyntaxType.*;

/**
 * Parse infix expressions into an expression tree.
 */
public class InfixParser {
    private List<Token> tokens;
    private int position = 0;
    private Token current;

    /**
     * TODO: Do not forget to check your are not getting extra token with off by one errors!
     * @param tokens
     */
    public InfixParser(final List<Token> tokens) {
        if (tokens.isEmpty()) {
            throw new IllegalStateException("Cannot Parse Without Tokens.");
        }
        this.tokens = new ArrayList<>();
        for (Token token : tokens) {
            if ((token.getSyntaxType() != WHITESPACE) && (token.getSyntaxType() != UNKNOWN)) {
                this.tokens.add(token);
            }
        }
    }

    public AbsNode parseForRoot() throws Exception {
        return parseExpression(0);
    }

    public AbsNode parseExpression(int parentPrecedence) throws Exception {
        AbsNode leftNode = decideExpressionType(parentPrecedence);
        while (true) {
            int precedence = PrecedenceUtil.binaryPrecedence(peek(0).getSyntaxType());
            if (precedence == 0 || precedence <= parentPrecedence) {
                return leftNode;
            }
            Token operatorToken = nextToken();
            AbsNode rightNode = parseExpression(precedence);
            leftNode = new BinaryExpressionNode(leftNode, operatorToken , rightNode);
        }
    }

    private AbsNode decideExpressionType(int parentPrecedence) throws Exception {
        int unaryPrecedence = PrecedenceUtil.unaryPrecedence(peek(0).getSyntaxType());
        if (unaryPrecedence != 0 && unaryPrecedence > parentPrecedence) {
            return parseUnaryExpression(unaryPrecedence);
        } else {
            return parsePrimaryExpression();
        }
    }

    private AbsNode parsePrimaryExpression() throws Exception {
        switch (peek(0).getSyntaxType()) {
            case OPEN_PAREN:
                return parseParentheses();
            case FALSE_KEYWORD: case TRUE_KEYWORD:
                return new LiteralNode(nextToken());
            case NUMBER:
                return new LiteralNode(match(NUMBER));
            default:
                throw new Exception("Unknown Token Found: " + peek(0));
        }
    }

    private AbsNode parseUnaryExpression(int unaryPrecedence) throws Exception {
        Token operatorToken = nextToken();
        AbsNode operandNode = parseExpression(unaryPrecedence);
        return new UnaryExpressionNode(operatorToken, operandNode);
    }

    private AbsNode parseParentheses() throws Exception {
        AbsNode leftNode = new LiteralNode(nextToken());
        AbsNode expressionRootNode = parseExpression(0);
        AbsNode rightNode = new LiteralNode(match(CLOSE_PAREN));
        return new ParenthesizedExpressionNode(leftNode, expressionRootNode, rightNode);
    }

    private Token match(SyntaxType type) {
        if (peek(0).getSyntaxType() == type) {
            return nextToken();
        }
        return new Token(current.getSyntaxType(), null, current.getPosition());
    }

    private Token nextToken() {
        current = peek(0);
        position++;
        if (current.getSyntaxType() == END) {
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
