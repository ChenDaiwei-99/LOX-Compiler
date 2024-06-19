package com.craftinginterpreters.lox;

import java.util.List;

// we need to create a new environment at each call, not at the function declaration.
// we don't want the runtime phase of the interpreter to bleed into
// the front end's syntax classes, so we don't want Stmt.Function itself
// to implement that.
class LoxFunction implements LoxCallable {
    private final Stmt.Function declaration;    // the variable can be assigned only once
    private final Environment closure;
    private final boolean isInitializer;

    LoxFunction(Stmt.Function declaration, Environment closure, boolean isInitializer) {
        this.closure = closure;
        this.declaration = declaration;
        this.isInitializer = isInitializer;
    }

    LoxFunction bind(LoxInstance instance) {
        Environment environment = new Environment(closure);
        environment.define("this", instance);
        return new LoxFunction(declaration, environment, isInitializer);
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        Environment environment = new Environment(closure);
        for (int i = 0; i < declaration.params.size(); i++) {
            environment.define(declaration.params.get(i).lexeme, arguments.get(i));
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
    public int arity() {
        return declaration.params.size();
    }

    @Override
    public String toString() {
        return "<fn " + declaration.name.lexeme + ">";
    }

}
