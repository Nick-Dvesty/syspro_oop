package ru.nsu.syspro.muller;

public class Add extends Operators {
    public Add(String left, String right) {
        super(left, right);
    }

    public Add(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String print() {
        return "(" + left.print() + "+" + right.print() + ")";
    }

    @Override
    public double substitution(String variables) {
        return left.substitution(variables) + right.substitution(variables);
    }

    @Override
    public Expression dif(String variables) {
        return new Add(left.dif(variables), right.dif(variables));
    }

    @Override
    public Expression simple() {
        return null;
    }
}
