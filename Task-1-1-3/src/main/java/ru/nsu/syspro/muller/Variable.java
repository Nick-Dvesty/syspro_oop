package ru.nsu.syspro.muller;

public class Variable extends Expression {
    private final String name;

    /**
     * default constructor for string
     * @param name name variable
     */
    public Variable(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < name.length(); i++) {
            if ((name.charAt(i) > 'Z' || name.charAt(i) < 'A')
                && (name.charAt(i) > 'z' || name.charAt(i) < 'a')){
                throw new IllegalArgumentException();
            }
        }
        this.name = name;
    }

    @Override
    public String print() {
        return name;
    }

    @Override
    public double eval(String variables) {
        int answer = 0;
        if (!variables.contains(name)) throw new IllegalArgumentException();
        for (int i = variables.indexOf(name) + name.length() + 3; i < variables.length(); i++) {
            if (variables.charAt(i) == ';') {
                break;
            }
            if (variables.charAt(i) >= '0' && variables.charAt(i) <= '9') {
                answer = (answer * 10 + (variables.charAt(i) - '0'));
                continue;
            }
            throw new IllegalArgumentException();
        }
        return answer;
    }

    @Override
    public Expression derivative(String variables) {
        return name.equals(variables) ? new Number("1") : new Number("0");
    }

    @Override
    public Expression simple() {
        return new Variable(name);
    }

}
