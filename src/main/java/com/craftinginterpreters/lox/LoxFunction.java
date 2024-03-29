package com.craftinginterpreters.lox;

import java.util.List;

/**
 * The type Lox function.
 */
class LoxFunction implements LoxCallable{
    /**
     * The Declaration.
     */
    private final Stmt.Function declaration;
    /**
     * The Closure.
     */
    private final Environment closure;

    private final boolean isInitializer;

    /**
     * Instantiates a new Lox function.
     *
     * @param declaration the declaration
     * @param closure     the closure
     */
    LoxFunction(Stmt.Function declaration, Environment closure,
                boolean isInitializer) {
        this.isInitializer = isInitializer;
        this.closure = closure;
        this.declaration = declaration;
    }

    LoxFunction bind(LoxInstance instance) {
        Environment environment = new Environment(closure);
        environment.define("this", instance);
        return new LoxFunction(declaration, environment, isInitializer);
    }
    @Override
    public int arity() {
        return declaration.params.size();
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        Environment environment = new Environment(closure);
        for (int i = 0; i < declaration.params.size(); i++) {
            environment.define(declaration.params.get(i).lexeme,
                    arguments.get(i));
        }

        try {
            interpreter.executeBlock(declaration.body, environment);
        } catch (Return returnValue) {
            if (isInitializer) return closure.getAt(0, "this");
            return returnValue.value;
        }

        if (isInitializer) return closure.getAt(0, "this");
        return null;
    }

    @Override
    public String toString() {
        return "<fn " + declaration.name.lexeme + ">";
    }
}
