package com.javalexer.analysis.parsing;

import com.javalexer.enums.SyntaxType;

public class PrecedenceUtil {

    public static int binaryPrecedence(SyntaxType type) {
        switch (type) {
            case PLUS: case MINUS:
                return 1;
            case FORWARD_SLASH: case STAR: case MODULO:
                return 2;
            default:
                return 0;
        }
    }

    public static int unaryPrecedence(SyntaxType type) {
        switch (type) {
            case PLUS: case MINUS: case BANG:
                return 3;
            default:
                return 0;
        }
    }

}
