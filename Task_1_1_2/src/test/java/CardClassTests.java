import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.syspro.Muller.Card;


/**
 * Test for Card class.
 */
public class CardClassTests {

    @Test
    public void defaultOpenTest() {
        Card card = new Card(2, "Двойка треф");
        card.IsClosed();
        Assertions.assertFalse(card.IsClosed());
    }

    @Test
    public void closedConstructorTest() {
        Card card = new Card(2, "Двойка треф", true);
        card.IsClosed();
        Assertions.assertTrue(card.IsClosed());
    }

    @Test
    public void chainsOpenClosedTest() {
        Card card = new Card(2, "Двойка треф", true);
        card.IsClosed();
        Assertions.assertTrue(card.IsClosed());
        card.Open();
        Assertions.assertFalse(card.IsClosed());
        card.Close();
        Assertions.assertTrue(card.IsClosed());
    }

    @Test
    public void getCostTest() {
        Card card = new Card(2, "Двойка треф");
        Assertions.assertEquals(card.getCost(), 2);
        card = new Card(3, "Тройка треф", true);
        Assertions.assertEquals(card.getCost(), 3);
        card = new Card(4, "Четвёрка треф", false);
        Assertions.assertEquals(card.getCost(), 4);
    }
}
