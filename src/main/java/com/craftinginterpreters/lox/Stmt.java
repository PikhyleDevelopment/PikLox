package com.craftinginterpreters.lox;

import java.util.List;

/**
 * The type Stmt.
 */
abstract class Stmt {
    /**
     * Accept r.
     *
     * @param <R>     the type parameter
     * @param visitor the visitor
     * @return the r
     */
    abstract <R> R accept(Visitor<R> visitor);

    /**
     * The interface Visitor.
     *
     * @param <R> the type parameter
     */
    interface Visitor<R> {
        /**
         * Visit block stmt r.
         *
         * @param stmt the stmt
         * @return the r
         */
        R visitBlockStmt(Block stmt);

        /**
         * Visit expression stmt r.
         *
         * @param stmt the stmt
         * @return the r
         */
        R visitExpressionStmt(Expression stmt);

        /**
         * Visit function stmt r.
         *
         * @param stmt the stmt
         * @return the r
         */
        R visitFunctionStmt(Function stmt);

        /**
         * Visit if stmt r.
         *
         * @param stmt the stmt
         * @return the r
         */
        R visitIfStmt(If stmt);

        /**
         * Visit print stmt r.
         *
         * @param stmt the stmt
         * @return the r
         */
        R visitPrintStmt(Print stmt);

        /**
         * Visit return stmt r.
         *
         * @param stmt the stmt
         * @return the r
         */
        R visitReturnStmt(Return stmt);

        /**
         * Visit var stmt r.
         *
         * @param stmt the stmt
         * @return the r
         */
        R visitVarStmt(Var stmt);

        /**
         * Visit while stmt r.
         *
         * @param stmt the stmt
         * @return the r
         */
        R visitWhileStmt(While stmt);
    }

    /**
     * The type Block.
     */
    static class Block extends Stmt {
        /**
         * The Statements.
         */
        final List<Stmt> statements;

        /**
         * Instantiates a new Block.
         *
         * @param statements the statements
         */
        Block(List<Stmt> statements) {
            this.statements = statements;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitBlockStmt(this);
        }
    }

    /**
     * The type Expression.
     */
    static class Expression extends Stmt {
        /**
         * The Expression.
         */
        final Expr expression;

        /**
         * Instantiates a new Expression.
         *
         * @param expression the expression
         */
        Expression(Expr expression) {
            this.expression = expression;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitExpressionStmt(this);
        }
    }

    /**
     * The type Function.
     */
    static class Function extends Stmt {
        /**
         * The Name.
         */
        final Token name;
        /**
         * The Params.
         */
        final List<Token> params;
        /**
         * The Body.
         */
        final List<Stmt> body;

        /**
         * Instantiates a new Function.
         *
         * @param name   the name
         * @param params the params
         * @param body   the body
         */
        Function(Token name, List<Token> params, List<Stmt> body) {
            this.name = name;
            this.params = params;
            this.body = body;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitFunctionStmt(this);
        }
    }

    /**
     * The type If.
     */
    static class If extends Stmt {
        /**
         * The Condition.
         */
        final Expr condition;
        /**
         * The Then branch.
         */
        final Stmt thenBranch;
        /**
         * The Else branch.
         */
        final Stmt elseBranch;

        /**
         * Instantiates a new If.
         *
         * @param condition  the condition
         * @param thenBranch the then branch
         * @param elseBranch the else branch
         */
        If(Expr condition, Stmt thenBranch, Stmt elseBranch) {
            this.condition = condition;
            this.thenBranch = thenBranch;
            this.elseBranch = elseBranch;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitIfStmt(this);
        }
    }

    /**
     * The type Print.
     */
    static class Print extends Stmt {
        /**
         * The Expression.
         */
        final Expr expression;

        /**
         * Instantiates a new Print.
         *
         * @param expression the expression
         */
        Print(Expr expression) {
            this.expression = expression;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitPrintStmt(this);
        }
    }

    /**
     * The type Return.
     */
    static class Return extends Stmt {
        /**
         * The Keyword.
         */
        final Token keyword;
        /**
         * The Value.
         */
        final Expr value;

        /**
         * Instantiates a new Return.
         *
         * @param keyword the keyword
         * @param value   the value
         */
        Return(Token keyword, Expr value) {
            this.keyword = keyword;
            this.value = value;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitReturnStmt(this);
        }
    }

    /**
     * The type Var.
     */
    static class Var extends Stmt {
        /**
         * The Name.
         */
        final Token name;
        /**
         * The Initializer.
         */
        final Expr initializer;

        /**
         * Instantiates a new Var.
         *
         * @param name        the name
         * @param initializer the initializer
         */
        Var(Token name, Expr initializer) {
            this.name = name;
            this.initializer = initializer;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitVarStmt(this);
        }
    }

    /**
     * The type While.
     */
    static class While extends Stmt {
        /**
         * The Condition.
         */
        final Expr condition;
        /**
         * The Body.
         */
        final Stmt body;

        /**
         * Instantiates a new While.
         *
         * @param condition the condition
         * @param body      the body
         */
        While(Expr condition, Stmt body) {
            this.condition = condition;
            this.body = body;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitWhileStmt(this);
        }
    }
}
