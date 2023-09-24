package com.craftinginterpreters.lox;

/**
 * The type Return.
 */
class Return extends RuntimeException {
    /**
     * The Value.
     */
    final Object value;

    /**
     * Instantiates a new Return.
     *
     * @param value the value
     */
    Return(Object value) {
        super(null, null, false, false);
        this.value = value;
    }
}
