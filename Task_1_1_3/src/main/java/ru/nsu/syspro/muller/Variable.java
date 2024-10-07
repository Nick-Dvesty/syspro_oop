package ru.nsu.syspro.muller;


import java.util.Objects;

/**
 * class variables.
 */
public class Variable extends Expression {
    private final String name;

    /**
     * default constructor for string.
     *
     * @param name name variable
     */
    public Variable(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Empty variable name is set");
        }
        for (int i = 0; i < name.length(); i++) {
            if ((name.charAt(i) > 'Z' || name.charAt(i) < 'A')
                && (name.charAt(i) > 'z' || name.charAt(i) < 'a')) {
                throw new IllegalArgumentException("The variable name contains invalid characters");
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
        String[] parts = variables.split(";");
        try {
            for (String part : parts) {
                String[] partParts = part.split(" ");
                int i = Objects.equals(partParts[0], "") ? 1:0;
                if (Objects.equals(name, partParts[i])) return Double.parseDouble(partParts[2 + i]);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Incorrectly set signification");
        }
        throw new IllegalArgumentException("Variable " + name + " not found");
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
