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
        String specifiers = "%-14s %-10s %-5s";
        String out = String.format(specifiers,
                "Typ: "+tokenType.toString(),
                "Pos: "+position,
                "Val: "+token);
        return out;
    }

}
