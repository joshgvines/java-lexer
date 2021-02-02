package com.javalexer.analysis.lexing;

import com.javalexer.enums.SyntaxType;

/**
 * Tokens represent a character or sequence of characters which have meaning in the source.
 */
public class Token {
    private SyntaxType type;
    private String value;
    private int position;

    public Token(SyntaxType type, String value, int position) {
        this.type = type;
        this.value = value;
        this.position = position;
    }

    public SyntaxType getSyntaxType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return String.format("%-14s %-10s %-5s",
                "Typ: " + type,
                "Pos: " + position,
                "Val: " + value);
    }

}
