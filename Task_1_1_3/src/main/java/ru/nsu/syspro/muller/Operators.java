package ru.nsu.syspro.muller;

public abstract class Operators extends Expression {
    protected Expression left;
    protected Expression right;
    public Operators(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    public Operators(String left, String right) {
        this.left =  Expression.convertor(left);
        this.right = Expression.convertor(right);
    }
}
