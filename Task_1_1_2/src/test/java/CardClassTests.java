import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.syspro.Muller.Card;

public class CardClassTests {

    @Test
    public void DefaultOpenTest() {
        Card card = new Card(2,"Двойка треф");
        card.IsClosed();
        Assertions.assertFalse(card.IsClosed());
    }

    @Test
    public void ClosedConstructorTest() {
        Card card = new Card(2,"Двойка треф", true);
        card.IsClosed();
        Assertions.assertTrue(card.IsClosed());
    }

    @Test
    public void ChainsOpenClosedTest() {
        Card card = new Card(2,"Двойка треф", true);
        card.IsClosed();
        Assertions.assertTrue(card.IsClosed());
        card.Open();
        Assertions.assertFalse(card.IsClosed());
        card.Close();
        Assertions.assertTrue(card.IsClosed());
    }

    @Test
    public void GetCostTest() {
        Card card = new Card(2,"Двойка треф");
        Assertions.assertEquals(card.getCost(), 2);
        card = new Card(3,"Тройка треф",true);
        Assertions.assertEquals(card.getCost(), 3);
        card = new Card(4,"Четвёрка треф", false);
        Assertions.assertEquals(card.getCost(), 4);
    }
}
