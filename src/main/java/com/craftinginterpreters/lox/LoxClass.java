package com.craftinginterpreters.lox;

import java.util.List;
import java.util.Map;

/**
 * Represents a class in the Lox programming language.
 * <p>
 * This class represents a user-defined class in the Lox programming language.
 * Each class has a name, which is stored in the 'name' field. The class name
 * is typically used for identifying and referencing the class.
 *
 * @see com.craftinginterpreters.lox - The package containing this class.
 */
class LoxClass implements LoxCallable {
    /**
     * The name of the Lox class.
     */
    final String name;
    final LoxClass superclass;
    private final Map<String, LoxFunction> methods;

    /**
     * Constructs a new LoxClass object with the given name.
     *
     * @param name The name of the class.
     */
    LoxClass(String name, LoxClass superclass,Map<String, LoxFunction> methods) {
        this.superclass = superclass;
        this.name = name;
        this.methods = methods;
    }

    LoxFunction findMethod(String name) {
        if (methods.containsKey(name)) {
            return methods.get(name);
        }

        if (superclass != null) {
            return superclass.findMethod(name);
        }

        return null;
    }

    /**
     * Returns a string representation of the Lox class.
     *
     * @return The name of the Lox class as a string.
     */
    @Override
    public String toString() {
        return name;
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        LoxInstance instance = new LoxInstance(this);
        LoxFunction initializer = findMethod("init");
        if (initializer != null) {
            initializer.bind(instance).call(interpreter, arguments);
        }
        return instance;
    }

    @Override
    public int arity() {
        LoxFunction initializer = findMethod("init");
        if (initializer == null) return 0;
        return initializer.arity();
    }
}

