package ru.nsu.syspro.muller;

import java.util.Objects;

public class Sub extends Operator {
    public Sub(String left, String right) {
        super(left, right);
    }

    public Sub(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String print() {
        return "(" + left.print() + "-" + right.print() + ")";
    }

    @Override
    public double eval(String variables) {
        return left.eval(variables) - right.eval(variables);
    }

    @Override
    public Expression derivative(String variables) {
        return new Sub(left.derivative(variables), right.derivative(variables));
    }

    @Override
    public Expression simple() {
        var simpleLeft = left.simple();
        var simpleRight = right.simple();
        if (haveComputable(simpleLeft, simpleRight)){
            return new Number(simpleLeft.eval("") - simpleRight.eval(""));
        }
        if (Objects.equals(simpleLeft.print(), simpleRight.print())){
            return new Number("0");
        }
        return new Sub(simpleLeft, simpleRight);
    }
}
