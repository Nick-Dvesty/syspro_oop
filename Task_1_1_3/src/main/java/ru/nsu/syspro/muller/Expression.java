package ru.nsu.syspro.muller;

public abstract class Expression {
    public abstract String print ();
    public abstract int substitution (String variables);
    public abstract Expression dif();
    public abstract Expression simple ();
}