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
    public double substitution(String variables) {
        return left.substitution(variables) - right.substitution(variables);
    }

    @Override
    public Expression dif(String variables) {
        return new Sub(left.dif(variables), right.dif(variables));
    }

    @Override
    public Expression simple() {
        var simpleLeft = left.simple();
        var simpleRight = right.simple();
        if (haveComputable(simpleLeft, simpleRight)){
            return new Number(simpleLeft.substitution("") - simpleRight.substitution(""));
        }
        if (Objects.equals(simpleLeft.print(), simpleRight.print())){
            return new Number("0");
        }
        return new Sub(simpleLeft, simpleRight);
    }
}
