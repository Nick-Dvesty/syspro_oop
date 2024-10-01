package ru.nsu.syspro.muller;

public class Sub extends Operators {
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
        return null;
    }

    @Override
    public Expression simple() {
        return null;
    }
}
