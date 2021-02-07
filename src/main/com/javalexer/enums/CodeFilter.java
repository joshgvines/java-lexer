package main.com.javalexer.enums;

/**
 * Defines Regex Expressions Used In Lexer.
 */
public enum CodeFilter {

    // Regex Expressions
    BRACES("(?<=\\{)|(?<=\\})"),
    SEMI_COL("(?<=\\;)"),
    SINGLE_COMMENT("//(.*?)"),
    MULTI_COMMENT("(?s)/\\*.*?\\*/"),
    IGNORE_DOUBLE_QUOTE("(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)"),
    IGNORE_SINGLE_QUOTE("(?=([^\\\']*\\\'[^\\\']*\\\')*[^\\\']*$)");

    private String filter;

    CodeFilter(String filter) {
        this.filter = filter;
    }

    public String get() {
        return filter;
    }

}
