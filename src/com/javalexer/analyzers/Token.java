package com.javalexer.analyzers;

import com.javalexer.enums.TokenType;

public class Token {

    public TokenType type;
    public String value;
    public int position;

    public Token(TokenType type, String value, int position) {
        this.type = type;
        this.value = value;
        this.position = position;
    }

    @Override
    public String toString() {
        return String.format("%-14s %-10s %-5s",
                "Typ: "+ type.toString(),
                "Pos: "+position,
                "Val: "+ value);
    }

}
