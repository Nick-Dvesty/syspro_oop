package ru.nsu.syspro.muller;

public abstract class Operator extends Expression {

    /**
     * left part expression
     */
    protected Expression left;

    /**
     * right part expression
     */
    protected Expression right;

    /**
     * base constructor for all operation.
     * @param left left part expression format
     * @param right right part expression format
     */
    public Operator(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * base constructor for all operation.
     * @param left left part string format
     * @param right right part string format
     */
    public Operator(String left, String right) {
        this.left =  Expression.convertor(left);
        this.right = Expression.convertor(right);
    }

    /**
     * checking for computability.
     * @param exprLeft left part expression
     * @param exprRight right part expression
     * @return computability
     */
    protected static boolean IsComputable(Expression exprLeft, Expression exprRight) {
        return (exprLeft.getClass() == Number.class) && (exprRight.getClass() == Number.class);
    }
}
