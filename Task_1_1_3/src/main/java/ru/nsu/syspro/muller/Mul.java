package ru.nsu.syspro.muller;

import java.util.Objects;

/**
 * class multiplication operation.
 */
public class Mul extends Operator {

    /**
     *constructor for string format.
     * @param left the expression of the left side
     * @param right the expression of the right side
     */
    public Mul(String left, String right) {
        super(left, right);
    }

    /**
     *constructor for expression format.
     * @param left the expression of the left side
     * @param right the expression of the right side
     */
    public Mul(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String print() {
        return "(" + left.print() + "*" + right.print() + ")";
    }

    @Override
    public double eval(String variables) {
        return left.eval(variables) * right.eval(variables);
    }

    @Override
    public Expression derivative(String variables) {
        return new Add(new Mul(left.derivative(variables), right) , new Mul(left, right.derivative(variables)));
    }

    @Override
    public Expression simple() {
        var simpleLeft = left.simple();
        var simpleRight = right.simple();
        if (IsComputable(simpleLeft, simpleRight)){
            return new Number(simpleLeft.eval("") * simpleRight.eval(""));
        }
        if (Objects.equals(simpleLeft.print(), "0") || Objects.equals(simpleRight.print(), "0")){
            return new Number("0");
        }
        if (Objects.equals(left.print(), "1")){
            return simpleRight;
        }
        if (Objects.equals(right.print(), "1")){
            return simpleLeft;
        }
        return new Mul(simpleLeft, simpleRight);
    }
}
