package com.javalexer.analysis.lexing;

import com.javalexer.enums.SyntaxType;

/**
 * Tokens represent a character or sequence of characters which have meaning in the source.
 */
public class Token {
    private SyntaxType type;
    private Object value;
    private int position;

    public Token(SyntaxType type, Object value, int position) {
        this.type = type;
        this.value = value;
        this.position = position;
    }

    public SyntaxType getSyntaxType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return String.format(
                "%-22s %-11s %-6s",
                "[ Type: " + type, "Pos: " + position, "Val: " + value + " ]");
    }

}
