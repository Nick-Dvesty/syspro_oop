import java.util.LinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.syspro.Muller.Card;
import ru.nsu.syspro.Muller.DeskCard;

/**
 * Tests for DeskCard class.
 */
public class DeskCardTest {
    @Test
    public void constructorTest() {
        DeskCard deskCard = new DeskCard(1);
        for (int i = 0; i < 52; i++){
            Assertions.assertNotNull(deskCard.GetOneCard());
        }
        Assertions.assertNull(deskCard.GetOneCard());
    }

    @Test
    public void testGetCountCard(){
        DeskCard deskCard = new DeskCard(1);
        for (int i = 0; i < 52; i++){
            deskCard.GetOneCard();
            Assertions.assertEquals(deskCard.GetCountCards(), 51 - i);
        }
        Assertions.assertEquals(deskCard.GetCountCards(), 0);
    }

    @Test
    public void testGetOneCard(){
        DeskCard deskCard = new DeskCard(1);
        var card = deskCard.GetOneCard();
        Assertions.assertFalse(card.IsClosed());
        card = deskCard.GetOneCard(true);
        Assertions.assertTrue(card.IsClosed());
    }
}
