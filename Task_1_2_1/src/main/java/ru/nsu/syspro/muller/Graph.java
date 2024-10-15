package ru.nsu.syspro.muller;

import java.util.List;

/**
 * Interface base operation graphs.
 */
public interface Graph {

    int getSize();


    /**
     * Add vertex in graph.
     * @param keyVer vertex's key in hashMap
     */
    void addVertex(int keyVer);

    /**
     * Remove vertex from graph.
     * @param keyVer vertex's key in hashMap
     */
    void removeVertex(int keyVer);

    /**
     * Add edge between first and second argument's vertex.
     * @param keyVer1 first vertex's key in hashMap
     * @param keyVer2 second vertex's key in hashMap
     */
    void addEdge(int keyVer1, int keyVer2);

    /**
     * Remove edge between first and second argument's vertex.
     * @param keyVer1 first vertex's key in hashMap
     * @param keyVer2 second vertex's key in hashMap
     */
    void removeEdge(int keyVer1, int keyVer2);

    /**
     * Return adjacent vertex for vertex.
     * @param keyVer vertex's key in hashMap
     * @return list adjacent vertex
     */
    List<Integer> getAdjacentVertex(int keyVer);

    /**
     * Creates a graph based on the file.
     * @param fileName The file of the data new graph
     */
    void readFile(String fileName);

    /**
     * Compare graphs.
     * @param graph other graph
     * @return result compare
     */
    boolean equals(Graph graph);

    /**
     * Sort this graph.
     * @return sorted sequence vertex
     */
    List<Integer> TopologySort();
}
