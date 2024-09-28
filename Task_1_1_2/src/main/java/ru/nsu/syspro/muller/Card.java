package ru.nsu.syspro.muller;

/**
 * the class of the game card.
 */
public class Card {

    private boolean closed;

    /**
     * name of the card.
     */
    protected String label;

    /**
     * the cost of the card.
     */
    protected int cost;

    /**
     * the default constructor of the open card.
     *
     * @param cost the cost of the card
     * @param label name of the card
     */
    public Card(int cost, String label) {
        this(cost, label, false);
    }

    /**
     * the constructor with a publicly set hiding value.
     *
     * @param cost the cost of the card
     * @param label name of the card
     * @param closed value closed
     */
    public Card(int cost, String label, boolean closed) {
        this.closed = closed;
        this.cost = cost;
        this.label = label;
    }

    /**
     * open card.
     */
    public void open() {
        this.closed = false;
    }

    /**
     * close card.
     */
    public void close() {
        this.closed = true;
    }

    /**
     * prints the text of the card with its value.
     *
     * @return text
     */
    public String printText() {
        if (!closed) {
            return label + " (" + getCost() + ")";
        } else {
            return "<закрытая карта>";
        }
    }

    /**
     * return value cost.
     *
     * @return value cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * return value closed.
     *
     * @return value closed
     */
    public boolean isClosed() {
        return closed;
    }
}