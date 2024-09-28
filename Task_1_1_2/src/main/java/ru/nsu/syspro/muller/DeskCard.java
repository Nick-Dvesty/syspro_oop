package ru.nsu.syspro.muller;

import java.util.Collections;
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
        Tuple[] dataCards = {new Tuple("Двойка", 2), new Tuple("Тройка", 3), new Tuple("Четвёрка", 4),
            new Tuple("Пятёрка", 5), new Tuple("Шестёрка", 6), new Tuple("Семёрка", 7),
            new Tuple("Восьмёрка", 8), new Tuple("Девятка", 9), new Tuple("Десятка", 10),
            new Tuple("Валет", 10), new Tuple("Дама", 10), new Tuple("Король", 10),
            new Tuple("Туз", 11)};
        cards = new LinkedList<>();
        for (int i = 0; i < countDeskCards; i++) {
            for (String mask : masks) {
                for (int k = 0; k < dataCards.length; k++) {
                    cards.add(new Card(dataCards[k].getItem2(), dataCards[k].getItem1() + " " + mask));
                }
            }
        }
    }

    /**
     * Constructor for test or cheaters.
     *
     * @param Cards array certain cards
     */
    public DeskCard(Card[] Cards){
        cards = new LinkedList<Card>();
        Collections.addAll(cards, Cards);
    }

    /**
     * return count card
     *
     * @return value count cards
     */
    public int getCountCards() { return cards.size(); }

    /**
     * return and cut one card without parameter closed.
     *
     * @return random card from desk
     */
    public Card getOneCard() {
        return getOneCard(false);
    }

    /**
     * return and cut one card.
     *
     * @param IsClosed closed condition
     * @return random card from desk
     */
    public Card getOneCard(boolean IsClosed) {
        if (cards.isEmpty()) return null;
        Random rand = new Random();
        var card = cards.remove(rand.nextInt(cards.size()));
        if (IsClosed) card.close();
        return card;
    }

    private class Tuple {

        private String  item1;
        private int item2;

        public Tuple(String item1, int item2) {
            this.item1 = item1;
            this.item2 = item2;
        }

        public String getItem1() {
            return item1;
        }

        public int getItem2() {
            return item2;
        }

    }
}
