package ru.nsu.syspro.muller;


public abstract class Expression {

    public static Expression convertor(String expr) {
        boolean part = false;
        StringBuilder partLeft = new StringBuilder();
        StringBuilder partRight = new StringBuilder();
        int countBrackets = 0;
        ExpOperation oper = null;
        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == '+'  || expr.charAt(i) == '-' || expr.charAt(i) == '*' || expr.charAt(i) == '/') {
                int prior = expr.charAt(i) == '+' || expr.charAt(i) == '-' ? 0 : 1;
                if (oper == null || countBrackets < oper.countBrackets || (countBrackets == oper.countBrackets && prior <= oper.prior)) {
                    if (oper != null) {
                        partLeft.append(oper.operator).append(partRight);
                        partRight = new StringBuilder();
                    }
                    part = true;
                    oper = new ExpOperation(expr.charAt(i), countBrackets, prior);
                    continue;
                }
            }
            if (expr.charAt(i) == '(') {
                countBrackets++;
            }
            if (expr.charAt(i) == ')') {
                countBrackets--;
            }
            if (!part) {
                partLeft.append(expr.charAt(i));
            } else {
                partRight.append(expr.charAt(i));
            }
        }
        if (oper == null){
            return switchConstConstruct(delBrackets(partLeft.toString()));
        } else {
            return switchOperConstruct(delBrackets(partLeft.toString()),
                delBrackets(partRight.toString()), oper.operator);
        }
    }

    private static class ExpOperation {
        public ExpOperation (char operator, int countBrackets, int prior) {
            this.operator = operator;
            this.countBrackets = countBrackets;
            this.prior = prior;
        }
        public char operator;
        public int prior;
        public int countBrackets;
    }

    private static Expression switchOperConstruct(String left, String right, char operator) {
        return switch (operator) {
            case '+' -> new Add(left, right);
            case '-' -> new Sub(left, right);
            case '*' -> new Mul(left, right);
            case '/' -> new Div(left, right);
            default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
        };
    }

    private static Expression switchConstConstruct(String value) {
        if (value.charAt(0) >= '0' && value.charAt(0) <= '9') {
            return new Number(value);
        } else {
            return new Variable(value);
        }
    }

    private static String delBrackets(String expr){
        if (expr.charAt(0) == '(' && expr.charAt(expr.length()-1) != ')') {
            return expr.substring(1);
        }
        if (expr.charAt(0) != '(' && expr.charAt(expr.length()-1) == ')') {
            return expr.substring(0,expr.length()-1);
        }
        return expr;
    }

    /**
     * Convert expression in string.
     *
     * @return expression string
     */
    public abstract String print ();

    /**
     * return value expression.
     *
     * @param variables string with values variables
     * @return value expression
     */
    public abstract double eval(String variables);

    /**
     * differentiation expression.
     *
     * @return differentiated expression
     */
    public abstract Expression derivative(String variables);

    /**
     * simplify expression.
     *
     * @return simpled expression
     */
    public abstract Expression simple ();
}