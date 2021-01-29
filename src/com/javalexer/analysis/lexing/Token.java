package com.javalexer.analysis.lexing;

import com.javalexer.enums.TokenType;

public class Token {

    private TokenType type;
    private String value;
    private int position;

    public Token(TokenType type, String value, int position) {
        this.type = type;
        this.value = value;
        this.position = position;
    }

    public TokenType getType() {
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
                "Typ: "+ type,
                "Pos: "+position,
                "Val: "+ value);
    }

}
