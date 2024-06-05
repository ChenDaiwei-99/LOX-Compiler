package com.craftinginterpreters.lox;

import java.util.List;

abstract class stmt {
    interface Visitor<R> {
        R visitExpressionstmt(Expression stmt);
        R visitPrintstmt(Print stmt);
        }
    static class Expression extends stmt {
        Expression(Expr expression) {
        this.expression = expression;
    }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitExpressionstmt(this);
        }

        final Expr expression;
}
    static class Print extends stmt {
        Print(Expr expression) {
        this.expression = expression;
    }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitPrintstmt(this);
        }

        final Expr expression;
}

    abstract <R> R accept(Visitor<R> visitor);
}
