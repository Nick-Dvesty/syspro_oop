package ru.nsu.syspro.muller;

import java.util.Objects;

/**
 * class division operation.
 */
public class Div extends Operator {

    /**
     *constructor for string format.
     *
     * @param left the expression of the left side
     * @param right the expression of the right side
     */
    public Div(String left, String right) {
        super(left, right);
    }

    /**
     *constructor for expression format.
     *
     * @param left the expression of the left side
     * @param right the expression of the right side
     */
    public Div(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String print() {
        return "(" + left.print() + "/" + right.print() + ")";
    }

    @Override
    public double eval(String variables) {
        var answer = left.eval(variables) / right.eval(variables);
        if (answer == Double.NEGATIVE_INFINITY || answer == Double.POSITIVE_INFINITY
            || Double.isNaN(answer)) {
            throw new ArithmeticException("Division by zero");
        }
        return answer;
    }

    @Override
    public Expression derivative(String variables) {
        var leftDif =  new Mul(left.derivative(variables), right);
        var rightDif = new Mul(left, right.derivative(variables));
        return new Div(new Sub(leftDif, rightDif), new Mul(right, right));
    }

    @Override
    public Expression simple() {
        var simpleLeft = left.simple();
        var simpleRight = right.simple();
        if (Objects.equals(simpleRight.print(), "0")) {
            throw new ArithmeticException("Division by zero");
        }
        if (isComputable(simpleLeft, simpleRight)) {
            return new Number(simpleLeft.eval("") / simpleRight.eval(""));
        }
        if (Objects.equals(left.print(), "0")) {
            return new Number("0");
        }
        if (Objects.equals(right.print(), "1")) {
            return simpleLeft;
        }
        return new Div(simpleLeft, simpleRight);
    }
}
