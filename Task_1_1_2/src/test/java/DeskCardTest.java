import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.syspro.muller.DeskCard;

/**
 * Tests for DeskCard class.
 */
public class DeskCardTest {
    @Test
    public void constructorTest() {
        DeskCard deskCard = new DeskCard(1);
        for (int i = 0; i < 52; i++) {
            Assertions.assertNotNull(deskCard.getOneCard());
        }
        Assertions.assertNull(deskCard.getOneCard());
    }

    @Test
    public void testGetCountCard() {
        DeskCard deskCard = new DeskCard(1);
        for (int i = 0; i < 52; i++) {
            deskCard.getOneCard();
            Assertions.assertEquals(deskCard.getCountCards(), 51 - i);
        }
        Assertions.assertEquals(deskCard.getCountCards(), 0);
    }

    @Test
    public void testGetOneCard() {
        DeskCard deskCard = new DeskCard(1);
        var card = deskCard.getOneCard();
        Assertions.assertFalse(card.isClosed());
        card = deskCard.getOneCard(true);
        Assertions.assertTrue(card.isClosed());
    }
}
