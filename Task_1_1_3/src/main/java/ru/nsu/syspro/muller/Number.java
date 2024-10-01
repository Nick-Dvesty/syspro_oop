package ru.nsu.syspro.muller;

/**
 * class for number.
 */
public class Number extends Expression {
    private final int value;

    /**
     * default constructor with const.
     *
     * @param value const value
     */
    public Number(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) < '0'|| value.charAt(i) > '9' ) {
                throw new IllegalArgumentException();
            }
        }
        this.value = Integer.parseInt(value);
    }

    @Override
    public String print() {
        return String.valueOf(value);
    }

    @Override
    public double substitution(String variables){
        return value;
    }

    @Override
    public Expression dif(String variables) {
        return new Number("0");
    }

    @Override
    public Expression simple() {
        return new Number(String.valueOf(value));
    }

}