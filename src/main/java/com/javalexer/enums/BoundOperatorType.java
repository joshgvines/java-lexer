package com.javalexer.enums;

/**
 * Represents the operation type for bound nodes which have been type checked.
 */
public enum BoundOperatorType {
    ADDITION,
    SUBTRACTION,

    MULTIPLICATION,
    DIVISION,
    MODULO,

    LOGIC_AND, LOGIC_OR, LOGIC_NOT,
    LOGIC_NOT_EQUALS, LOGIC_EQUALS_COMPARE
}
