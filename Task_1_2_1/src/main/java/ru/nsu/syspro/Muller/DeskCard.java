package ru.nsu.syspro.Muller;

import java.util.LinkedList;
import java.util.Random;

/**
 * Generated card deck
 */
public class DeskCard {

    private final LinkedList<Card> cards;

    /**
     * Generated constructor with count Desk
     *
     * @param countDeskCards counts desk
     */
    public DeskCard(int countDeskCards) {
        String[] masks = {"треф", "бубён", "пик", "червей"};
        String[] names = {"Двойка", "Тройка", "Четвёрка", "Пятёрка", "Шестёрка", "Семёрка",
            "Восьмёрка", "Девятка", "Десятка", "Валет", "Дама", "Король", "Туз"};
        int[] costs = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
        cards = new LinkedList<>();
        for (int i = 0; i < countDeskCards; i++) {
            for (String mask : masks) {
                for (int k = 0; k < costs.length; k++) {
                    cards.add(new Card(costs[k], names[k] + " " + mask));
                }
            }
        }
    }

    /**
     * return count card
     *
     * @return value count cards
     */
    public int GetCountCards() { return cards.size(); }

    /**
     * return and cut one card without parameter closed.
     *
     * @return random card from desk
     */
    public Card GetOneCard() {
        return GetOneCard(false);
    }

    /**
     * return and cut one card.
     *
     * @param IsClosed closed condition
     * @return random card from desk
     */
    public Card GetOneCard(boolean IsClosed) {
        if (cards.isEmpty()) return null;
        Random rand = new Random();
        var card = cards.remove(rand.nextInt(cards.size()));
        if (IsClosed) card.Close();
        return card;
    }
}
