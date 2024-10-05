package ru.nsu.syspro.muller;

public class Add extends Operator {
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
        var simpleLeft = left.simple();
        var simpleRight = right.simple();
        if (haveComputable(simpleLeft, simpleRight)){
            return new Number(simpleLeft.substitution("") + simpleRight.substitution(""));
        }
        return new Add(simpleLeft, simpleRight);
    }
}
