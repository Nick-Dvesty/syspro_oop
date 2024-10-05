package ru.nsu.syspro.muller;

public abstract class Operator extends Expression {
    protected Expression left;
    protected Expression right;
    public Operator(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    public Operator(String left, String right) {
        this.left =  Expression.convertor(left);
        this.right = Expression.convertor(right);
    }
    protected static boolean haveComputable(Expression exprLeft, Expression exprRight) {
        return (exprLeft.getClass() == Number.class) && (exprRight.getClass() == Number.class);
    }
}
