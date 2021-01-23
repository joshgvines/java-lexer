package com.javalexer;

import com.javalexer.analyzers.LexicalAnalyzer;
import com.javalexer.analyzers.Token;
import com.javalexer.parsing.parsers.InfixParser;

import java.util.List;

public class InfixParserMainDemo {

    public static void main(String[] args) throws Exception {

        LexicalAnalyzer la = new LexicalAnalyzer();
        List<Token> tokenList = la.lex("1 + 2 + 3 - 1");

        if (tokenList != null) {
            InfixParser infixParser = new InfixParser(tokenList);
            infixParser.printAllTokens();
        }

    }

}
