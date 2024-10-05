package ru.nsu.syspro.muller;

/**
 * class for number.
 */
public class Number extends Expression {
    private final int value;

    /**
     * default constructor for int.
     *
     * @param value value const
     */
    public Number(int value) {
        this.value = value;
    }

    /**
     * default constructor for double.
     *
     * @param value value const
     */
    public Number(double value) {
        this.value = (int)value;
    }

    /**
     * default constructor with const.
     *
     * @param value const value
     */
    public Number(String value) {

        try {
            this.value = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage());
        }
    }

    @Override
    public String print() {
        return String.valueOf(value);
    }

    @Override
    public double eval(String variables) {
        return value;
    }

    @Override
    public Expression derivative(String variables) {
        return new Number("0");
    }

    @Override
    public Expression simple() {
        return new Number(value);
    }

}