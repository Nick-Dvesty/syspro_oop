package ru.nsu.syspro.muller;

import java.util.ArrayList;

/**
 * class a class of generalized player behavior.
 */
public class Gamer {

    /**
     * The player has a list of cards.
     */
    private final ArrayList<Card> cards;

    /**
     * default constructor.
 */
    public Gamer() {
        cards = new ArrayList<>();
    }

    /**
     * Print cards gamer.
     *
     * @return test for print
     */
    public String printCards() {
        StringBuilder printLine = new StringBuilder();
        boolean haveClosed = false;
        printLine.append("[ ");
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).isClosed()) {
                haveClosed = true;
            }
            printLine.append(cards.get(i).printText());
            if (i != cards.size() - 1) {
                printLine.append(", ");
            }
        }
        printLine.append(" ]");
        if (!haveClosed) {
            printLine.append(" => ").append(getSum());
        }
        return printLine.toString();
    }

    /**
     * add gamer's one card from desk.
     *
     * @param deskCard donor desk card
     * @param closed close condition
     * @return added card
     */
    public Card takeCard(DeskCard deskCard, boolean closed) {
        cards.add(deskCard.getOneCard(closed));
        int sum = getSum();
        if (sum > 21) {
            repairDesk();
        }
        return cards.getLast();
    }

    /**
     * open last card.
     *
     * @return text last card
     */
    public String openLastCards() {
        cards.getLast().open();
        return cards.getLast().printText();
    }

    /**
     * return sum cost.
     *
     * @return sum cost
     */
    public int getSum() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getCost();
        }
        return sum;
    }

    private void repairDesk() {
        int sum = getSum();
        if (sum > 21) {
            for (int i = cards.size() - 1; i != 0 && sum > 21; i--) {
                if (cards.get(i).cost == 11) {
                    cards.set(i, new Card(1, cards.get(i).label));
                    sum -= 10;
                }
            }
        }
    }

}
