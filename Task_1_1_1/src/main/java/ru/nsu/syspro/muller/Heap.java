package ru.nsu.syspro.muller;

import static java.util.Collections.swap;

import java.util.Vector;

/**
 * ordinary Binary Heap.
 */
public class Heap {

    private Vector<Integer> heap;

    /**
     * empty constructor.
     */
    public Heap() {
        heap = new Vector<Integer>();
    }

    /**
     * insert the new element.
     *
     * @param value new element
     */
    public void Insert(int value) {
        heap.add(value);
        shiftUp(heap.size() - 1);
    }

    /**
     * returns the minimum element.
     *
     * @return minimum element
     */
    public int PeekMin(){
        return heap.get(0);
    }

    /**
     * returns and remove the minimum element.
     *
     * @return minimum element
     */
    public int ExtractMin(){
        int min = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        shiftDown(0);
        return min;
    }

    /**
     * restores the order if the last element is incorrect
     */
    private void shiftUp(int index){
        while (index > 0) {
            if (heap.get(index) < heap.get((index - 1)/2)) {
                swap(heap, index, (index - 1)/2);
            }
            index = (index - 1)/2;
        }
    }

    /**
     * restores the order if the first element is incorrect
     */
    private void shiftDown(int index) {
        if (index * 2 + 1 < heap.size()) {
            if (index * 2 + 2 >= heap.size()
                || heap.get(index * 2 + 1) < (heap.get(index * 2 + 2))
                || heap.get(index) < (heap.get(index * 2 + 2))) {
                if (heap.get(index) > heap.get(index * 2 + 1)) {
                    swap(heap, index, (index * 2 + 1));
                    shiftDown(index * 2 + 1);
                }
            }
            if (index * 2 + 2 < heap.size() && heap.get(index) > heap.get(index * 2 + 2)) {
                swap(heap, index, (index * 2 + 2));
                shiftDown(index * 2 + 2);
            }
        }
    }
}