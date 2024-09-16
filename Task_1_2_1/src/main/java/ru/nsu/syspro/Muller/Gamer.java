package ru.nsu.syspro.Muller;

import java.util.ArrayList;


public class Gamer {
    private final ArrayList<Card> cards;

    public Gamer() {
        cards = new ArrayList<>();
    }
    public String PrintCards() {
        StringBuilder printLine = new StringBuilder();
        boolean HaveClosed = false;
        printLine.append("[ ");
        for (int i = 0; i < cards.size(); i++) {
            printLine.append(cards.get(i).PrintText());
            if (i != cards.size() - 1) printLine.append(", ");
        }
        printLine.append(" ]");
        if (!HaveClosed) printLine.append(" => ").append(GetSum());
        return printLine.toString();
    }
    public Card TakeCard(DeskCard deskCard, boolean closed) {
        cards.add(deskCard.GetOneCard(closed));
        int sum = GetSum();
        if (sum > 21) repairDesk();
        return cards.getLast();
    }
    public String OpenLastCards(){
            cards.getLast().Open();
            return cards.getLast().PrintText();
    }
    public int GetSum(){
        int sum = 0;
        for (Card card : cards) {
            sum += card.getCost();
        }
        return sum;
    }

    private void repairDesk() {
        int sum = GetSum();
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
