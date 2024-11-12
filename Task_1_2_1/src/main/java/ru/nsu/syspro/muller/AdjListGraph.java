package ru.nsu.syspro.muller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdjListGraph implements Graph {
    private final List<List<Integer>> adjList;

    private final HashMap<Integer, Integer> keys;


    public AdjListGraph() {
        adjList = new ArrayList<>();
        keys = new HashMap<>();
    }

    @Override
    public int getSize(){
        return adjList.size();
    }

    @Override
    public boolean HaveVertex(int keyVer) {
        return keys.containsKey(keyVer);
    }

    @Override
    public boolean HaveEdge(int keyVer1, int keyVer2) {
        if (!keys.containsKey(keyVer1)) {
            throw new IllegalArgumentException("invalid key:" + keyVer1);
        }
        if (!keys.containsKey(keyVer2)) {
            throw new IllegalArgumentException("invalid key:" + keyVer2);
        }
        return adjList.get(keys.get(keyVer1)).contains(keyVer2);
    }

    @Override
    public void addVertex(int keyVer){
        if (keys.containsKey(keyVer)) {
            throw new IllegalArgumentException("Duplicate key: " + keyVer);
        }
        adjList.add(new ArrayList<>());
        keys.put(keyVer, adjList.size() - 1);
    }

    @Override
    public void removeVertex(int keyVer) {
        if (!keys.containsKey(keyVer)) {
            throw new IllegalArgumentException("Key not found: " + keyVer);
        }
        var rem = keys.remove(keyVer);
        adjList.remove((int)rem);
        for (Integer ind : keys.keySet()) {
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
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            readBufferedReader(br);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public void readBufferedReader(BufferedReader reader) throws IOException {
        keys.clear();
        adjList.clear();
        int firstNumber, secondNumber;
        String[] parts = reader.readLine().split(" "); // Разделяем строку по пробелам
        if (parts.length == 2) {
            try {
                firstNumber = Integer.parseInt(parts[0]);
                secondNumber = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("");
            }
        } else {
            throw new IllegalArgumentException("");
        }
        for (int i = 0; i < firstNumber; i++){
            var line =  reader.readLine();
            int num;
            try {
                num = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid vertex name: " + line);
            }
            addVertex(num);
        }
        for (int i = 0; i < secondNumber; i++) {
            var line = reader.readLine(); // Разделяем строку по пробелам
            parts = line.split(" ");
            int fromVertex, toVertex;
            try {
                fromVertex  = Integer.parseInt(parts[0]);
                toVertex = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid line: " + line);
            }
            addEdge(fromVertex, toVertex);
        }
    }

    @Override
    public boolean equals(Graph graph) {
        if (graph.getSize() != getSize()) {
            return false;
        }
        for(Integer key : keys.keySet()) {
            for(Integer key2 : keys.keySet()) {
                if (!graph.HaveVertex(key2)) return false;
                if (adjList.get(keys.get(key)).contains(key2) && !graph.HaveEdge(key, key2) ||
                (!adjList.get(keys.get(key)).contains(key2) && graph.HaveEdge(key, key2))) {
                    return false;
                }
            }
        }
        return true;
    }

}
