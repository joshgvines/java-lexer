package com.javalexer.analyzers;

import com.javalexer.enums.TokenType;

public class Token {

    public TokenType type;
    public String token;
    public int position;

    public Token(TokenType type, String token, int position) {
        this.type = type;
        this.token = token;
        this.position = position;
    }

    @Override
    public String toString() {
        return String.format("%-14s %-10s %-5s",
                "Typ: "+ type.toString(),
                "Pos: "+position,
                "Val: "+token);
    }

}
