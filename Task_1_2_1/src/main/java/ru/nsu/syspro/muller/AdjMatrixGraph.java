package ru.nsu.syspro.muller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdjMatrixGraph implements Graph {

    private int[][] matrix;

    private int nowSize;

    private HashMap<Integer, Integer> keyIndexMap;


    public AdjMatrixGraph() {
        nowSize = 0;
        matrix = new int[8][8];
        keyIndexMap = new HashMap<Integer, Integer>();
    }

    @Override
    public int getSize(){
        return nowSize;
    }

    @Override
    public boolean HaveVertex(int keyVer) {
        return keyIndexMap.containsKey(keyVer);
    }

    @Override
    public boolean HaveEdge(int keyVer1, int keyVer2) {
        if (!keyIndexMap.containsKey(keyVer1)) {
            throw new IllegalArgumentException("invalid key:" + keyVer1);
        }
        if (!keyIndexMap.containsKey(keyVer2)) {
            throw new IllegalArgumentException("invalid key:" + keyVer2);
        }
        return matrix[keyVer1][keyVer2] != 0;
    }

    @Override
    public void addVertex(int keyVer){
        if (keyIndexMap.containsKey(keyVer)) {
            throw new IllegalArgumentException("Duplicate key: " + keyVer);
        }
        if (nowSize == matrix.length) {
            incMatrix();
        }
        nowSize++;
        keyIndexMap.put(keyVer, nowSize-1);
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
        if (!keyIndexMap.containsKey(keyVer)) {
            throw new IllegalArgumentException("Key not found: " + keyVer);
        }
        var rem = keyIndexMap.remove(keyVer);
        if (rem != nowSize - 1) {
            for (Integer ind : keyIndexMap.keySet()) {
                if (keyIndexMap.get(ind) > keyIndexMap.get(keyVer)) {
                    keyIndexMap.replace(ind, keyIndexMap.get(ind) - 1);
                }
            }
            delStrMatrix(rem);
        }
        nowSize--;
        keyIndexMap.remove(keyVer);
    }

    private void delStrMatrix(Integer rem) {
        for (int i = rem; i < nowSize; i++) {
            for (int j = rem; j < nowSize; j++) {
                matrix[i][j] = matrix[i + 1][j + 1];
            }
        }
    }

    @Override
    public void addEdge(int keyVer1, int keyVer2) {
        var ind1 = keyIndexMap.get(keyVer1);
        var ind2 = keyIndexMap.get(keyVer2);
        matrix[ind1][ind2] = 1;
    }

    @Override
    public void removeEdge(int keyVer1, int keyVer2){
        var ind1 = keyIndexMap.get(keyVer1);
        var ind2 = keyIndexMap.get(keyVer2);
        matrix[ind1][ind2] = 0;
    }

    @Override
    public List<Integer> getAdjacentVertex(int keyVer) {
        List<Integer> ret = new ArrayList<>();
        var ind = keyIndexMap.get(keyVer);
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
    public void readBufferedReader(BufferedReader reader) throws IOException {

    }

    @Override
    public boolean equals(Graph graph) {
        if (graph.getSize() != nowSize) {
            return false;
        }
        for (var key : keyIndexMap.keySet()) {
            for (var key2 : keyIndexMap.keySet()) {
                if (graph.HaveVertex(key2)) {
                    return false;
                }
                if ((matrix[key][key2] != 0 && graph.HaveEdge(key, key2))
                    || (matrix[key][key2] != 1 && !graph.HaveEdge(key, key2))) {
                    return false;
                }
            }
        }
        return true;
    }
}
