package com.craftinginterpreters.lox;

/**
 * The enum Token type.
 */
enum TokenType {
    /**
     * The Left paren.
     */
// Single-character tokens.
    LEFT_PAREN,
    /**
     * Right paren token type.
     */
    RIGHT_PAREN,
    /**
     * Left brace token type.
     */
    LEFT_BRACE,
    /**
     * Right brace token type.
     */
    RIGHT_BRACE,
    /**
     * Comma token type.
     */
    COMMA,
    /**
     * Dot token type.
     */
    DOT,
    /**
     * Minus token type.
     */
    MINUS,
    /**
     * Plus token type.
     */
    PLUS,
    /**
     * Semicolon token type.
     */
    SEMICOLON,
    /**
     * Slash token type.
     */
    SLASH,
    /**
     * Star token type.
     */
    STAR,

    /**
     * The Bang.
     */
// One or two character tokens.
    BANG,
    /**
     * Bang equal token type.
     */
    BANG_EQUAL,
    /**
     * Equal token type.
     */
    EQUAL,
    /**
     * Equal equal token type.
     */
    EQUAL_EQUAL,
    /**
     * Greater token type.
     */
    GREATER,
    /**
     * Greater equal token type.
     */
    GREATER_EQUAL,
    /**
     * Less token type.
     */
    LESS,
    /**
     * Less equal token type.
     */
    LESS_EQUAL,

    /**
     * Identifier token type.
     */
// Literals.
    IDENTIFIER,
    /**
     * String token type.
     */
    STRING,
    /**
     * Number token type.
     */
    NUMBER,

    /**
     * And token type.
     */
// Keywords.
    AND,
    /**
     * Class token type.
     */
    CLASS,
    /**
     * Else token type.
     */
    ELSE,
    /**
     * False token type.
     */
    FALSE,
    /**
     * Fun token type.
     */
    FUN,
    /**
     * For token type.
     */
    FOR,
    /**
     * If token type.
     */
    IF,
    /**
     * Nil token type.
     */
    NIL,
    /**
     * Or token type.
     */
    OR,
    /**
     * Return token type.
     */
    RETURN,
    /**
     * Super token type.
     */
    SUPER,
    /**
     * This token type.
     */
    THIS,
    /**
     * True token type.
     */
    TRUE,
    /**
     * Var token type.
     */
    VAR,
    /**
     * While token type.
     */
    WHILE,

    /**
     * Eof token type.
     */
    EOF
}
