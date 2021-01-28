package com.javalexer.analyzers;

import com.javalexer.enums.TokenType;

public class Precedence {

    public static int precedence(TokenType type) {
        switch (type) {
            case PLUS: case MINUS:
                return 1;
            case FORWARD_SLASH: case STAR:
                return 2;
            default:
                return 0;
        }
    }

}
