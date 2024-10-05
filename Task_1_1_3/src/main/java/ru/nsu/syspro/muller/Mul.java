package ru.nsu.syspro.muller;

import java.util.Objects;

public class Mul extends Operator {
    public Mul(String left, String right) {
        super(left, right);
    }

    public Mul(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String print() {
        return "(" + left.print() + "*" + right.print() + ")";
    }

    @Override
    public double substitution(String variables) {
        return left.substitution(variables) * right.substitution(variables);
    }

    @Override
    public Expression dif(String variables) {
        return new Add(new Mul(left.dif(variables), right) , new Mul(left, right.dif(variables)));
    }

    @Override
    public Expression simple() {
        var simpleLeft = left.simple();
        var simpleRight = right.simple();
        if (haveComputable(simpleLeft, simpleRight)){
            return new Number(simpleLeft.substitution("") * simpleRight.substitution(""));
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
