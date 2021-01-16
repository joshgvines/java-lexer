package com.javalexer.analyzers;

import com.javalexer.enums.TokenType;

public class Token {

    private TokenType tokenType;
    private String token;

    public Token(TokenType tokenType, String token) {
        this.tokenType = tokenType;
        this.token = token;
    }

    @Override
    public String toString() {
        return tokenType.toString() + ", " + token;
    }

}
