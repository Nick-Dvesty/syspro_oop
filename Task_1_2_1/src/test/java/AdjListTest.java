import org.junit.jupiter.api.Test;
import ru.nsu.syspro.muller.AdjListGraph;

public class AdjListTest {
    @Test
    public void test() {
        AdjListGraph graph = new AdjListGraph();
        graph.addVertex(3);
        graph.addVertex(2);
        graph.addVertex(1);
        graph.addEdge(3,1);
        graph.addEdge(1,2);
        graph.addEdge(1,3);
        graph.removeEdge(1,2);
        graph.removeEdge(1,3);
        graph.removeVertex(2);
    }
}
