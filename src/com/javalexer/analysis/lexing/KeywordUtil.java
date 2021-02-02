package com.javalexer.analysis.lexing;

import com.javalexer.enums.SyntaxType;

import static com.javalexer.enums.SyntaxType.*;

public class KeywordUtil {

    public static SyntaxType getKeyword(String found) throws Exception {
        switch (found) {
            case "false":
                return FALSE_KEYWORD;
            case "true":
                return TRUE_KEYWORD;
            case "public":
                return PUBLIC_KEYWORD;
            default:
                return null;
        }
    }

}
