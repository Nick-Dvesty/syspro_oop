import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.syspro.muller.AdjListGraph;
import ru.nsu.syspro.muller.Graph;

public class AdjListTest {
    @Test
    public void constructorTest() {
        Graph graph = new AdjListGraph();
    }

    @Test
    public void haveNotVertexTest(){
        Graph graph = new AdjListGraph();
        Assertions.assertFalse(graph.HaveVertex(1));
    }

    @Test
    public void addVertexTest() {
        Graph graph = new AdjListGraph();
        int[] namesVertex = {1,3,4,6,8,9,12,2,87};
        int[] namesExcessVertex = {34, 32 ,5,7, 31};
        for (int vertex : namesVertex) {
            graph.addVertex(vertex);
        }
        for (int vertex : namesVertex) {
            Assertions.assertTrue(graph.HaveVertex(vertex));
        }
        for (int excessVertex : namesExcessVertex) {
           Assertions.assertFalse(graph.HaveVertex(excessVertex));
        }
    }

    @Test
    public void addVertexDuplicateKeyTest(){
        Graph graph = new AdjListGraph();
        graph.addVertex(1);
        graph.addVertex(2);
        assertThrows(IllegalArgumentException.class, () -> graph.addVertex(1));
    }

    @Test
    public void haveVertexTest(){
        Graph graph = new AdjListGraph();
        graph.addVertex(1);
        Assertions.assertTrue(graph.HaveVertex(1));
        graph.addVertex(2);
        Assertions.assertTrue(graph.HaveVertex(2));
        Assertions.assertFalse(graph.HaveVertex(3));
    }

    @Test
    public void getSizeTest(){
        Graph graph = new AdjListGraph();
        for (int i = 0; i < 10; i++) {
            Assertions.assertEquals(graph.getSize(), i);
            graph.addVertex(i);
        }
    }

    @Test
    public void haveNotEdgeTest(){
        Graph graph = new AdjListGraph();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        Assertions.assertFalse(graph.HaveEdge(1, 2));
        Assertions.assertFalse(graph.HaveEdge(1, 3));
        assertThrows(IllegalArgumentException.class, () -> graph.HaveEdge(1, 4));
        assertThrows(IllegalArgumentException.class, () -> graph.HaveEdge(5, 2));

    }

    @Test
    public void addEdgeTest(){
        Graph graph = new AdjListGraph();
        for (int i = 0; i < 10; i++) {
            graph.addVertex(i);
        }
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                graph.addEdge(i, j);
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j < 7 && i < 7) {
                    Assertions.assertTrue(graph.HaveEdge(i, j));
                } else {
                    Assertions.assertFalse(graph.HaveEdge(i, j));
                }
            }
        }
    }

    @Test
    public void addEdgeInvalidVertexTest(){
        Graph graph = new AdjListGraph();
        graph.addVertex(1);
        graph.addVertex(2);
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(1, 4));
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(5, 2));
    }

    @Test
    public void addEdgeDuplicateKeyTest(){
        Graph graph = new AdjListGraph();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2);
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(1, 2));
    }

    @Test
    public void removeEdgeTest(){
        Graph graph = new AdjListGraph();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        Assertions.assertTrue(graph.HaveEdge(1, 2));
        graph.removeEdge(1, 2);
        Assertions.assertFalse(graph.HaveEdge(1, 2));
    }

    @Test
    public void removeEdgeInvalidVertexTest(){
        Graph graph = new AdjListGraph();
        graph.addVertex(1);
        graph.addVertex(2);
        assertThrows(IllegalArgumentException.class, () -> graph.removeEdge(1, 4));
        assertThrows(IllegalArgumentException.class, () -> graph.removeEdge(5, 2));
    }

    @Test
    public void removeEdgeDuplicateKeyTest(){
        Graph graph = new AdjListGraph();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2);
        graph.removeEdge(1, 2);
        assertThrows(IllegalArgumentException.class, () -> graph.removeEdge(1, 2));
    }

}
