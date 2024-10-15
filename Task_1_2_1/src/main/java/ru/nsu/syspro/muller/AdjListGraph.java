package ru.nsu.syspro.muller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import javax.management.ObjectName;

public class AdjListGraph implements Graph {
    private final List<List<Integer>> adjList;

    private final HashMap<Integer, Integer> keys;

    private final List<Integer> listKeys;

    public AdjListGraph() {
        adjList = new ArrayList<>();
        keys = new HashMap<>();
        listKeys = new ArrayList<>();
    }

    @Override
    public int getSize(){
        return adjList.size();
    }

    @Override
    public void addVertex(int keyVer){
        if (keys.containsKey(keyVer)) {
            throw new IllegalArgumentException("Duplicate key: " + keyVer);
        }
        adjList.add(new ArrayList<>());
        keys.put(keyVer, adjList.size() - 1);
        listKeys.add(keyVer);
    }

    @Override
    public void removeVertex(int keyVer) {
        if (!keys.containsKey(keyVer)) {
            throw new IllegalArgumentException("Key not found: " + keyVer);
        }
        listKeys.remove((Integer)keyVer);
        var rem = keys.remove(keyVer);
        adjList.remove((int)rem);
        for (Integer ind : listKeys) {
            if (keys.get(ind) > rem) {
                keys.replace(ind, keys.get(ind) - 1);
            }
        }
    }

    @Override
    public void addEdge(int keyVer1, int keyVer2) {
        if (!keys.containsKey(keyVer1)) {
            throw new IllegalArgumentException("invalid key:" + keyVer1);
        }
        if (!keys.containsKey(keyVer2)) {
            throw new IllegalArgumentException("invalid key:" + keyVer2);
        }
        if (adjList.get(keys.get(keyVer1)).contains(keyVer2)) {
            throw new IllegalArgumentException("duplicate edge between " + keyVer1 + " and " + keyVer2);
        }
        adjList.get(keys.get(keyVer1)).add(keyVer2);
    }

    @Override
    public void removeEdge(int keyVer1, int keyVer2){
        if (!keys.containsKey(keyVer1)) {
            throw new IllegalArgumentException("invalid key:" + keyVer1);
        }
        if (!keys.containsKey(keyVer2)) {
            throw new IllegalArgumentException("invalid key:" + keyVer2);
        }
        if (!adjList.get(keys.get(keyVer1)).contains(keyVer2)) {
            throw new IllegalArgumentException("don't be edge between " + keyVer1 + " and " + keyVer2);
        }
        adjList.get(keys.get(keyVer1)).remove((Integer)keyVer2);
    }

    @Override
    public List<Integer> getAdjacentVertex(int keyVer) {
        if (!keys.containsKey(keyVer)) {
            throw new IllegalArgumentException("invalid key:" + keyVer);
        }
        return adjList.get(keys.get(keyVer));
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
