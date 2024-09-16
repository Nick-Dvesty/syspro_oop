package ru.nsu.syspro.Muller;

import java.util.LinkedList;
import java.util.Random;
import javax.xml.stream.FactoryConfigurationError;
import ru.nsu.syspro.Muller.Card;

public class DeskCard {
    private int countDeskCards;
    private LinkedList<Card> cards;

    public DeskCard(int countDeskCards) {
        String[] masks = {"треф", "бубён", "пик", "червей"};
        String[] names = {"Двойка", "Тройка", "Четвёрка", "Пятёрка", "Шестёрка", "Семёрка",
            "Восьмёрка", "Девятка", "Десятка", "Валет", "Дама", "Король", "Туз"};
        int[] costs = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
        cards = new LinkedList<Card>();
        for (int i = 0; i < countDeskCards; i++) {
            for (String mask : masks) {
                for (int k = 0; k < costs.length; k++) {
                    cards.add(new Card(costs[k], names[k] + " " + mask));
                }
            }
        }
    }
    public int GetCountCards() { return cards.size(); }
    public Card GetOneCard() {
        return GetOneCard(false);
    }
    public Card GetOneCard(boolean IsClosed) {
        if (cards.isEmpty()) return null;
        Random rand = new Random();
        var card = cards.remove(rand.nextInt(cards.size()));
        if (IsClosed) card.Close();
        return card;
    }
}
