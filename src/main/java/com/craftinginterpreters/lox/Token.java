package com.craftinginterpreters.lox;

/**
 * The type Token.
 */
class Token {
    /**
     * The Type.
     */
    final TokenType type;
    /**
     * The Lexeme.
     */
    final String lexeme;
    /**
     * The Literal.
     */
    final Object literal;
    /**
     * The Line.
     */
    final int line;

    /**
     * Instantiates a new Token.
     *
     * @param type    the type
     * @param lexeme  the lexeme
     * @param literal the literal
     * @param line    the line
     */
    Token(TokenType type, String lexeme, Object literal, int line) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
    }

    @Override
    public String toString() {
        return type + " " + lexeme + " " + literal;
    }
}
