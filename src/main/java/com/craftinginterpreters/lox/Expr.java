package com.craftinginterpreters.lox;

import java.util.List;

/**
 * The `Expr` class is an abstract base class for all expression types in the Lox programming language.
 */
abstract class Expr {
    /**
     * Accepts a visitor that performs an operation on the expression.
     *
     * @param <R>     The return type of the visitor.
     * @param visitor The visitor to accept.
     * @return The result of applying the visitor to the expression.
     */
    abstract <R> R accept(Visitor<R> visitor);

    /**
     * A visitor interface for expressions, allowing operations to be performed on specific expression types.
     *
     * @param <R> The return type of the visitor.
     */
    interface Visitor<R> {
        /**
         * Visit method for `Assign` expressions.
         *
         * @param expr The `Assign` expression to visit.
         * @return The result of visiting the `Assign` expression.
         */
        R visitAssignExpr(Assign expr);

        /**
         * Visit method for `Binary` expressions.
         *
         * @param expr The `Binary` expression to visit.
         * @return The result of visiting the `Binary` expression.
         */
        R visitBinaryExpr(Binary expr);

        /**
         * Visit method for `Call` expressions.
         *
         * @param expr The `Call` expression to visit.
         * @return The result of visiting the `Call` expression.
         */
        R visitCallExpr(Call expr);

        /**
         * Visit method for `Grouping` expressions.
         *
         * @param expr The `Grouping` expression to visit.
         * @return The result of visiting the `Grouping` expression.
         */
        R visitGroupingExpr(Grouping expr);

        /**
         * Visit method for `Literal` expressions.
         *
         * @param expr The `Literal` expression to visit.
         * @return The result of visiting the `Literal` expression.
         */
        R visitLiteralExpr(Literal expr);

        /**
         * Visit method for `Logical` expressions.
         *
         * @param expr The `Logical` expression to visit.
         * @return The result of visiting the `Logical` expression.
         */
        R visitLogicalExpr(Logical expr);

        /**
         * Visit method for `Unary` expressions.
         *
         * @param expr The `Unary` expression to visit.
         * @return The result of visiting the `Unary` expression.
         */
        R visitUnaryExpr(Unary expr);

        /**
         * Visit method for `Variable` expressions.
         *
         * @param expr The `Variable` expression to visit.
         * @return The result of visiting the `Variable` expression.
         */
        R visitVariableExpr(Variable expr);
    }

    /**
     * Represents an assignment expression.
     */
    static class Assign extends Expr {
        final Token name;
        final Expr value;

        /**
         * Creates a new `Assign` expression with the specified variable name and value.
         *
         * @param name  The token representing the variable name.
         * @param value The expression representing the value to assign.
         */
        Assign(Token name, Expr value) {
            this.name = name;
            this.value = value;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitAssignExpr(this);
        }
    }

    /**
     * Represents a binary expression (e.g., addition, subtraction).
     */
    static class Binary extends Expr {
        final Expr left;
        final Token operator;
        final Expr right;

        /**
         * Creates a new `Binary` expression with the specified left and right operands and operator.
         *
         * @param left     The left operand expression.
         * @param operator The token representing the binary operator.
         * @param right    The right operand expression.
         */
        Binary(Expr left, Token operator, Expr right) {
            this.left = left;
            this.operator = operator;
            this.right = right;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitBinaryExpr(this);
        }
    }

    /**
     * Represents a function call expression.
     */
    static class Call extends Expr {
        final Expr callee;
        final Token paren;
        final List<Expr> arguments;

        /**
         * Creates a new `Call` expression with the specified callee, parentheses token, and argument list.
         *
         * @param callee    The expression representing the callee (function or method).
         * @param paren     The token representing the opening parenthesis.
         * @param arguments The list of argument expressions.
         */
        Call(Expr callee, Token paren, List<Expr> arguments) {
            this.callee = callee;
            this.paren = paren;
            this.arguments = arguments;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitCallExpr(this);
        }
    }

    /**
     * Represents a grouping expression (e.g., parentheses around an expression).
     */
    static class Grouping extends Expr {
        final Expr expression;

        /**
         * Creates a new `Grouping` expression with the specified grouped expression.
         *
         * @param expression The expression inside the grouping.
         */
        Grouping(Expr expression) {
            this.expression = expression;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitGroupingExpr(this);
        }
    }

    /**
     * Represents a literal value expression (e.g., numbers, strings, boolean values).
     */
    static class Literal extends Expr {
        final Object value;

        /**
         * Creates a new `Literal` expression with the specified literal value.
         *
         * @param value The literal value.
         */
        Literal(Object value) {
            this.value = value;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitLiteralExpr(this);
        }
    }

    /**
     * Represents a logical expression (e.g., `and`, `or`).
     */
    static class Logical extends Expr {
        final Expr left;
        final Token operator;
        final Expr right;

        /**
         * Creates a new `Logical` expression with the specified left and right operands and operator.
         *
         * @param left     The left operand expression.
         * @param operator The token representing the logical operator (`and` or `or`).
         * @param right    The right operand expression.
         */
        Logical(Expr left, Token operator, Expr right) {
            this.left = left;
            this.operator = operator;
            this.right = right;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitLogicalExpr(this);
        }
    }

    /**
     * Represents a unary expression (e.g., negation).
     */
    static class Unary extends Expr {
        final Token operator;
        final Expr right;

        /**
         * Creates a new `Unary` expression with the specified unary operator and operand.
         *
         * @param operator The token representing the unary operator.
         * @param right    The operand expression.
         */
        Unary(Token operator, Expr right) {
            this.operator = operator;
            this.right = right;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitUnaryExpr(this);
        }
    }

    /**
     * Represents a variable expression.
     */
    static class Variable extends Expr {
        final Token name;

        /**
         * Creates a new `Variable` expression with the specified variable name.
         *
         * @param name The token representing the variable name.
         */
        Variable(Token name) {
            this.name = name;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitVariableExpr(this);
        }
    }
}
