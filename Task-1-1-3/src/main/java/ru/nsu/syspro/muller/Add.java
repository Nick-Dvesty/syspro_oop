package ru.nsu.syspro.muller;

/**
 * class addition operation.
 */
public class Add extends Operator {

    /**
     *constructor for string format.
     * @param left the expression of the left side
     * @param right the expression of the right side
     */
    public Add(String left, String right) {
        super(left, right);
    }

    /**
     *constructor for expression format.
     * @param left the expression of the left side
     * @param right the expression of the right side
     */
    public Add(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String print() {
        return "(" + left.print() + "+" + right.print() + ")";
    }

    @Override
    public double eval(String variables) {
        return left.eval(variables) + right.eval(variables);
    }

    @Override
    public Expression derivative(String variables) {
        return new Add(left.derivative(variables), right.derivative(variables));
    }

    @Override
    public Expression simple() {
        var simpleLeft = left.simple();
        var simpleRight = right.simple();
        if (IsComputable(simpleLeft, simpleRight)){
            return new Number(simpleLeft.eval("") + simpleRight.eval(""));
        }
        return new Add(simpleLeft, simpleRight);
    }
}
