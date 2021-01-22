package com.javalexer.analyzers;

import com.javalexer.enums.TokenType;

public class Token {

    public TokenType tokenType;
    public String token;
    public int position;

    public Token(TokenType tokenType, String token, int position) {
        this.tokenType = tokenType;
        this.token = token;
        this.position = position;
    }

    @Override
    public String toString() {
        return String.format("%-14s %-10s %-5s",
                "Typ: "+tokenType.toString(),
                "Pos: "+position,
                "Val: "+token);
    }

}
