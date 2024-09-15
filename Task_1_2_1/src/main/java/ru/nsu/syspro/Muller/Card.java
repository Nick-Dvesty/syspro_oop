package ru.nsu.syspro.Muller;

public class Card {
    private boolean closed;
    private String label;
    protected int cost;

    public Card(int cost, String label) {
        this(cost, label, false);
    }
    public Card(int cost, String label, boolean closed) {
        this.closed = closed;
        this.cost = cost;
        this.label = label;
    }
    public void Open(){
        this.closed = false;
    }
    public void Close(){
        this.closed = true;
    }
    public String Print(){
        if (!closed) {
            return label + " (" + getCost() + ")";
        } else {
            return "<закрытая карта>";
        }
    }
    public int getCost() {
        return cost;
    }
    public boolean IsClosed(){
        return closed;
    }
}