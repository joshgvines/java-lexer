package com.javalexer.analysis.parsing;

import com.javalexer.enums.TokenType;

public class Precedence {

    public static int binaryPrecedence(TokenType type) {
        switch (type) {
            case PLUS: case MINUS:
                return 1;
            case FORWARD_SLASH: case STAR: case MODULO:
                return 2;
            default:
                return 0;
        }
    }

    public static int unaryPrecedence(TokenType type) {
        switch (type) {
            case PLUS: case MINUS:
                return 3;
            default:
                return 0;
        }
    }

}
