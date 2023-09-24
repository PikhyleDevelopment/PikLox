package com.craftinginterpreters.lox;

import java.util.HashMap;
import java.util.Map;

/**
 * The `Environment` class represents a symbol table for storing variables and their values.
 * It is used to track variable bindings in a Lox program, including nested environments.
 */
class Environment {
    /**
     * The enclosing environment. This is used for handling variable scoping.
     */
    final Environment enclosing;

    /**
     * A map that associates variable names (as strings) with their corresponding values.
     */
    private final Map<String, Object> values = new HashMap<>();

    /**
     * Creates a new top-level environment with no enclosing environment.
     */
    Environment() {
        enclosing = null;
    }

    /**
     * Creates a new environment with the specified enclosing environment.
     *
     * @param enclosing The enclosing environment.
     */
    Environment(Environment enclosing) {
        this.enclosing = enclosing;
    }

    /**
     * Retrieves the value associated with a variable by its token name.
     *
     * @param name The token representing the variable name.
     * @return The value of the variable.
     * @throws RuntimeError If the variable is undefined in this and all enclosing environments.
     */
    Object get(Token name) {
        if (values.containsKey(name.lexeme)) {
            return values.get(name.lexeme);
        }

        if (enclosing != null) return enclosing.get(name);

        throw new RuntimeError(name, "Undefined variable '" + name.lexeme + "'.");
    }

    /**
     * Assigns a new value to an existing variable by its token name.
     * If the variable doesn't exist in this environment, it checks enclosing environments.
     *
     * @param name  The token representing the variable name.
     * @param value The new value to assign.
     * @throws RuntimeError If the variable is undefined in this and all enclosing environments.
     */
    void assign(Token name, Object value) {
        if (values.containsKey(name.lexeme)) {
            values.put(name.lexeme, value);
            return;
        }

        if (enclosing != null) {
            enclosing.assign(name, value);
            return;
        }

        throw new RuntimeError(name, "Undefined variable '" + name.lexeme + "'.");
    }

    /**
     * Defines a new variable in the current environment with the specified name and value.
     *
     * @param name  The name of the variable.
     * @param value The initial value of the variable.
     */
    void define(String name, Object value) {
        values.put(name, value);
    }

    /**
     * Retrieves the ancestor environment at a specified distance from the current environment.
     *
     * @param distance The number of ancestor environments to traverse.
     * @return The ancestor environment.
     */
    Environment ancestor(int distance) {
        Environment environment = this;
        for (int i = 0; i < distance; i++) {
            environment = environment.enclosing;
        }

        return environment;
    }

    /**
     * Retrieves the value of a variable at a specified distance and name.
     *
     * @param distance The distance to the ancestor environment where the variable is defined.
     * @param name     The name of the variable.
     * @return The value of the variable.
     */
    Object getAt(int distance, String name) {
        return ancestor(distance).values.get(name);
    }

    /**
     * Assigns a new value to a variable at a specified distance and name.
     *
     * @param distance The distance to the ancestor environment where the variable is defined.
     * @param name     The token representing the variable name.
     * @param value    The new value to assign.
     */
    void assignAt(int distance, Token name, Object value) {
        ancestor(distance).values.put(name.lexeme, value);
    }
}
