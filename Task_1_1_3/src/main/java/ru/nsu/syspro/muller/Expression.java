package ru.nsu.syspro.muller;

public abstract class Expression {

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
    public abstract int substitution (String variables);

    /**
     * differentiation expression.
     *
     * @return differentiated expression
     */
    public abstract Expression dif();

    /**
     * simplify expression.
     *
     * @return simpled expression
     */
    public abstract Expression simple ();
}