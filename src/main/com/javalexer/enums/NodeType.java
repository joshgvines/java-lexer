package main.com.javalexer.enums;

/**
 * Defines Node types used in type checking and evaluation.
 */
public enum NodeType {
    LITERAL,

    UNARY_EXPRESSION,
    BINARY_EXPRESSION,
    PARENTHESES_EXPRESSION,

    ASSIGNMENT_NODE,
    UNKNOWN_NODE
}
