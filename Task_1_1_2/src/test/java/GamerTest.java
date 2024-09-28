import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.syspro.muller.Card;
import ru.nsu.syspro.muller.DeskCard;
import ru.nsu.syspro.muller.Gamer;

/**
 * Test for gamer class.
 */
public class GamerTest {
    @Test
    public void testTakeCard() {
        Gamer gamer = new Gamer();
        Card[] cards = new Card[]{new Card(2, "Двойка треф")};
        DeskCard deskCard = new DeskCard(cards);
        gamer.takeCard(deskCard, false);
        Assertions.assertEquals(gamer.printCards(), "[ Двойка треф (2) ] => 2");
        Assertions.assertEquals(gamer.getSum(), 2);
    }

    @Test
    public void testTakeSomeCard() {
        Gamer gamer = new Gamer();
        Card[] cards = new Card[]{new Card(2, "Двойка треф")};
        DeskCard deskCard = new DeskCard(cards);
        gamer.takeCard(deskCard, false);
        cards = new Card[]{new Card(3, "Тройка треф")};
        deskCard = new DeskCard(cards);
        gamer.takeCard(deskCard, false);
        Assertions.assertEquals(gamer.printCards(), "[ Двойка треф (2), Тройка треф (3) ] => 5");
        Assertions.assertEquals(gamer.getSum(), 5);
    }

    @Test
    public void testTakeClosedCard() {
        Gamer gamer = new Gamer();
        Card[] cards = new Card[]{new Card(2, "Двойка треф")};
        DeskCard deskCard = new DeskCard(cards);
        gamer.takeCard(deskCard, true);
        Assertions.assertEquals(gamer.printCards(), "[ <закрытая карта> ]");
        Assertions.assertEquals(gamer.getSum(), 2);
        gamer.openLastCards();
        Assertions.assertEquals(gamer.printCards(), "[ Двойка треф (2) ] => 2");
    }

    @Test
    public void testBlackJack() {
        Gamer gamer = new Gamer();
        Card[] cards = new Card[]{new Card(10, "Десятка треф"), new Card(11, "Туз треф")};
        DeskCard deskCard = new DeskCard(cards);
        gamer.takeCard(deskCard, false);
        gamer.takeCard(deskCard, false);
        Assertions.assertEquals(gamer.getSum(), 21);
    }

    @Test
    public void testOverflow() {
        Gamer gamer = new Gamer();
        Card[] cards = new Card[]{new Card(11, "Туз червей"), new Card(11, "Туз треф")};
        DeskCard deskCard = new DeskCard(cards);
        gamer.takeCard(deskCard, false);
        gamer.takeCard(deskCard, false);
        Assertions.assertEquals(gamer.getSum(), 12);
    }

    @Test
    public void testOverflowClosed() {
        Gamer gamer = new Gamer();
        Card[] cards = new Card[]{new Card(11, "Туз червей"), new Card(11, "Туз треф")};
        DeskCard deskCard = new DeskCard(cards);
        gamer.takeCard(deskCard, false);
        gamer.takeCard(deskCard, true);
        Assertions.assertEquals(gamer.getSum(), 12);
    }
}
