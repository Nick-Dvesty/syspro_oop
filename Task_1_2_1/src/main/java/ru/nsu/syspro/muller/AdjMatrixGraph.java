package ru.nsu.syspro.muller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdjMatrixGraph implements Graph {

    private int[][] matrix;

    private int nowSize;

    private List<Integer> listKeys;

    private HashMap<Integer, Integer> keys;


    public AdjMatrixGraph() {
        nowSize = 0;
        matrix = new int[8][8];
        keys = new HashMap<Integer, Integer>();
        listKeys = new ArrayList<>();
    }

    @Override
    public int getSize(){
        return nowSize;
    }

    @Override
    public void addVertex(int keyVer){
        if (keys.containsKey(keyVer)) {
            throw new IllegalArgumentException("Duplicate key: " + keyVer);
        }
        if (nowSize == matrix.length) {
            incMatrix();
        }
        nowSize++;
        keys.put(keyVer, nowSize-1);
        listKeys.add(keyVer);
    }

    private void incMatrix() {
        var newMatrix = new int[matrix.length * 2][matrix.length * 2];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, matrix.length);
        }
        matrix = newMatrix;
    }

    @Override
    public void removeVertex(int keyVer) {
        if (!keys.containsKey(keyVer)) {
            throw new IllegalArgumentException("Key not found: " + keyVer);
        }
        var rem = keys.remove(keyVer);
        listKeys.remove((Integer)keyVer);
        if (rem == nowSize - 1) {
            nowSize--;
        } else {
            for (Integer ind : listKeys) {
                if (keys.get(ind) > keys.get(keyVer)) {
                    keys.replace(ind, keys.get(ind) - 1);
                }
            }
            delStrMatrix(rem);
        }
        keys.remove(keyVer);
    }

    private void delStrMatrix(Integer rem) {
    }

    @Override
    public void addEdge(int keyVer1, int keyVer2) {
        var ind1 = keys.get(keyVer1);
        var ind2 = keys.get(keyVer2);
        matrix[ind1][ind2] = 1;
    }

    @Override
    public void removeEdge(int keyVer1, int keyVer2){
        var ind1 = keys.get(keyVer1);
        var ind2 = keys.get(keyVer2);
        matrix[ind1][ind2] = 0;
    }

    @Override
    public List<Integer> getAdjacentVertex(int keyVer) {
        List<Integer> ret = new ArrayList<>();
        var ind = keys.get(keyVer);
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[ind][i] == 1) {
                ret.add(i);
            }
        }
        return ret;
    }

    @Override
    public void readFile(String fileName) {

    }

    @Override
    public boolean equals(Graph graph) {
        if (graph.getSize() == nowSize) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                }
            }
        }
        return false;
    }

    @Override
    public List<Integer> TopologySort() {
        return null;
    }

}
