package com.javalexer.parsing;

import com.javalexer.analyzers.Token;
import com.javalexer.enums.TokenType;

import java.util.List;
import java.util.Stack;

import static com.javalexer.enums.TokenType.*;

public class Parser {

    Stack<String> values = new Stack<>();
    Stack<String> ops = new Stack<>();

    public void parse(List<Token> tokens) {

        ExpressionTree expressionTree = new ExpressionTree(null);

        int level = 0;
        for (int i = 0; i < tokens.size(); i++) {

            Token token = tokens.get(i);
            System.out.println(token.toString());
            TokenType tokenType = token.tokenType;

            if (tokenType == SPACE) {
                continue;
            }

            if (tokenType == LPAREN) {
                level++;
                ops.push(token.token);
            } else if (tokenType == RPAREN) {
                level--;
                ops.push(token.token);
            } else if (tokenType == NUMBER) {
                values.push(token.token);
            } else if (isOperator(tokenType)) {
                ops.push(token.token);
            }
        }

        expressionTree.evaluate();
    }

    private boolean isOperator(TokenType type) {
        return (type == STAR || type == SLASH || type == PLUS || type == MINUS);
    }
}
