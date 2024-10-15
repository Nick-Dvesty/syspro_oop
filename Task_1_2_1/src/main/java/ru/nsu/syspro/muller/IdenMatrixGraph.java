package ru.nsu.syspro.muller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IdenMatrixGraph implements Graph {
    private int[][] matrix;

    private int vertexSize;

    private int edgeSize;

    private HashMap<Integer, Integer> keys;

    public IdenMatrixGraph() {
        vertexSize = 0;
        edgeSize = 0;
        matrix = new int[8][8];
        keys = new HashMap<Integer, Integer>();
    }

    @Override
    public int getSize(){
        return vertexSize;
    }

    @Override
    public void addVertex(int keyVer){
        if (vertexSize == matrix.length) {
            incVerMatrix();
            vertexSize++;
        }
        keys.put(keyVer, vertexSize-1);
    }

    private void incVerMatrix() {
    }

    @Override
    public void removeVertex(int keyVer) {
        var rem = keys.remove(keyVer);
        if (rem == vertexSize - 1) {
            vertexSize--;
        } else {
            delVerMatrix(rem);
        }
        keys.remove(keyVer);
    }

    private void delVerMatrix(Integer rem) {
    }

    @Override
    public void addEdge(int keyVer1, int keyVer2) {
        var ind1 = keys.get(keyVer1);
        var ind2 = keys.get(keyVer2);
        int indEdge;
        if (edgeSize == matrix[0].length){
            incEdgeMatrix();
            indEdge = edgeSize-1;
            edgeSize++;
        } else {
            indEdge = edgeSize-1;
        }
        matrix[ind1][indEdge] = 1;
        matrix[ind2][indEdge] = -1;
    }

    private void incEdgeMatrix() {
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
        return false;
    }

    @Override
    public List<Integer> TopologySort() {
        return null;
    }
}
