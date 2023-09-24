package com.craftinginterpreters.lox;

/**
 * The type Runtime error.
 */
class RuntimeError extends RuntimeException {
    /**
     * The Token.
     */
    final Token token;

    /**
     * Instantiates a new Runtime error.
     *
     * @param token   the token
     * @param message the message
     */
    RuntimeError(Token token, String message) {
        super(message);
        this.token = token;
    }
}
