import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.syspro.muller.HeapSort;


public class HeapSortTest {

    @Test
    public void testHeapSort1() {
        var data = new int[]{};
        var sorted = HeapSort.heapSort(data);
        Assertions.assertArrayEquals(new int[]{}, sorted);
    }

    @Test
    public void testHeapSort2() {
        var data = new int[]{1};
        var sorted = HeapSort.heapSort(data);
        Assertions.assertArrayEquals(new int[]{1}, sorted);
    }

    @Test
    public void testHeapSort3() {
        var data = new int[]{7, 6, 5, 4, 3, 2, 1};
        var sorted = HeapSort.heapSort(data);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7}, sorted);
    }

    @Test
    public void testHeapSort4() {
        var data = new int[]{7, 6, 1, 4, 1, 2, 1};
        var sorted = HeapSort.heapSort(data);
        Assertions.assertArrayEquals(new int[]{1, 1, 1, 2, 4, 6, 7}, sorted);
    }

    @Test
    public void testHeapSort5() {
        var data = new int[]{1, 2, 1, 2, 1, 2, 1};
        var sorted = HeapSort.heapSort(data);
        Assertions.assertArrayEquals(new int[]{1, 1, 1, 1, 2, 2, 2}, sorted);
    }

    @Test
    public void testHeapSort6() {
        var data = new int[]{1, 23, 32, 12, 31, 52, 14};
        var sorted = HeapSort.heapSort(data);
        Assertions.assertArrayEquals(new int[]{1, 12, 14, 23, 31, 32, 52}, sorted);
    }

    @Test
    public void testHeapSort7() {
        var data = new int[]{1, 2, 3, 4, 5, 6, 7};
        var sorted = HeapSort.heapSort(data);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7}, sorted);
    }
}