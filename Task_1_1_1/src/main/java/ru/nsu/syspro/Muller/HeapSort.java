package ru.nsu.syspro.Muller;

import ru.nsu.syspro.Muller.Heap;

/**
 * static class for heapsort integer array
 */
public class HeapSort {

    /**
     * heap sort in ascending order
     * @param arr unsorted array
     * @return sorted array
     */
    static public int[] heapSort(int[] arr) {
        Heap heap = new Heap();
        int[] answer = new int[arr.length];
        for (int i : arr) {
            heap.Insert(i);
        }
        for (int i = 0; i <arr.length; i++) {
            answer[i] = heap.ExtractMin();
        }
        return answer;
    }
}
