package com.javalexer.parsing.parsers;

import com.javalexer.analyzers.Token;
import com.javalexer.enums.TokenType;
import com.javalexer.parsing.ExpressionTree;

import java.util.List;
import java.util.Stack;

import static com.javalexer.enums.TokenType.*;

public class Parser {

    Stack<Token> vals = new Stack<>();
    Stack<Token> tempVals = new Stack<>();
    Stack<Token> ops = new Stack<>();
    Stack<Token> tempOps = new Stack<>();

    ExpressionTree expressionTree = new ExpressionTree(null);

    int lastPrecedence = -1;

    public void parse(List<Token> tokens) throws Exception {
        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            if (token.tokenType == SPACE) {
                continue;
            } else if (token.tokenType == NUMBER) {
                vals.push(token);
            } else if (isOperator(token.tokenType)) {
                Token look = null;
                while (look == null || look.tokenType != NUMBER) {
                    look = tokens.get(++i);
                }
                vals.push(look);
                handleOperator(token);
            }
        }
        printStacks();
    }

    private void handleOperator(Token token) throws Exception {
        int precedence = precedence(token);
        if (precedence > lastPrecedence) {
            ops.push(token);
        } else {
            handleStack(token, precedence);
        }
        lastPrecedence = precedence;
    }

    private void handleStack(Token token, int precedence) throws Exception {
        Token temp1 = null;
        Token temp2 = null;
        while (true) {
            tempOps.push(ops.pop());
            if (temp1 == null && temp2 == null) {
                temp1 = vals.pop();
                temp2 = vals.pop();
            } else {
                for (int j = 0; j <= 1; j++) {
                    if (!vals.isEmpty()) {
                        tempVals.push(vals.pop());
                    }
                }
            }
            if (ops.isEmpty() || (precedence(ops.peek()) <= precedence)) {
                applyTempStack(token, temp1, temp2);
                return;
            }
        }
    }

    private void applyTempStack(Token token, Token temp1, Token temp2) {
        System.out.println(temp1 + " :: " + temp2);

        ops.push(token);
        vals.push(temp2);
        vals.push(temp1);

        while (!tempOps.isEmpty()) {
            ops.push(tempOps.pop());
        }
        while (!tempVals.isEmpty()) {
            vals.push(tempVals.pop());
        }
    }

    private int precedence(Token token) throws Exception {
        switch (token.tokenType) {
            case SLASH: case STAR: return 1;
            case PLUS: case MINUS: return 0;
            default: return -1;
        }
    }

    private boolean isOperator(TokenType type) {
        return (type == STAR || type == SLASH || type == PLUS || type == MINUS);
    }

    private void printStacks() {
        while (!vals.isEmpty()) {
            System.out.println("VALUES: [ " + vals.pop() + " ]");
        }
        while (!ops.isEmpty()) {
            System.out.println("OPERA: [ " + ops.pop() + " ]");
        }
    }
}
