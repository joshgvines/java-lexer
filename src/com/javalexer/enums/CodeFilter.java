package com.javalexer.enums;

/**
 * Defines Regex Expressions Used In Lexer.
 */
public enum CodeFilter {

    // Regex Expressions
    FIND_BRACES("(?<=\\{)|(?<=\\})"),
    FIND_SEMI_COL("(?<=\\;)"),
    IGNORE_DOUBLE_QUOTES("(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)"),
    IGNORE_SINGLE_QUOTES("(?=([^\\\']*\\\'[^\\\']*\\\')*[^\\\']*$)");

    private String filter;

    CodeFilter(String filter) {
        this.filter = filter;
    }

    public String get() {
        return filter;
    }

}
