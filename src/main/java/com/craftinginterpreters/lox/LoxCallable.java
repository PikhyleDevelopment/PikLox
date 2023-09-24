package com.craftinginterpreters.lox;

import java.util.List;

/**
 * The interface Lox callable.
 */
interface LoxCallable {
    /**
     * Arity int.
     *
     * @return the int
     */
    int arity();

    /**
     * Call object.
     *
     * @param interpreter the interpreter
     * @param arguments   the arguments
     * @return the object
     */
    Object call(Interpreter interpreter, List<Object> arguments);
}
