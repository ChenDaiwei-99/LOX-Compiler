//package com.craftinginterpreters.lox;
//
//class AstPrinter implements Expr.Visitor<String> {
//
//    String print(Expr expr) {
//        return expr.accept(this);
//    }
//
//    @Override
//    public String visitBinaryExpr(Expr.Binary expr) {
//        return parenthesis(expr.operator.lexeme, expr.left, expr.right);
//    }
//
//    @Override
//    public String visitGroupingExpr(Expr.Grouping expr) {
//        return parenthesis("group", expr.expression);
//    }
//
//    @Override
//    public String visitLiteralExpr(Expr.Literal expr) {
//        if (expr.value == null) return "nil";
//        return expr.value.toString();
//    }
//
//    @Override
//    public String visitUnaryExpr(Expr.Unary expr) {
//        return parenthesis(expr.operator.lexeme, expr.right);
//    }
//
//    private String parenthesis(String name, Expr... exprs) {
//        StringBuilder builder = new StringBuilder();
//        builder.append("(").append(name);
//        for (Expr expr: exprs) {
//            builder.append(" ");
//            builder.append(expr.accept(this));  // this recursive step let us print an entire tree
//        }
//        builder.append(")");
//        return builder.toString();
//    }
//
//    // a simple test to do the pretty printing
//    public static void main(String[] args) {
//        Expr expression = new Expr.Binary(
//            new Expr.Unary(
//                new Token(TokenType.MINUS, "-", null, 1),
//                new Expr.Literal(123)
//            ),
//            new Token(TokenType.STAR, "*", null, 1),
//            new Expr.Grouping(new Expr.Literal(45.67))
//        );
//        System.out.println(new AstPrinter().print(expression));
//    }
//}
