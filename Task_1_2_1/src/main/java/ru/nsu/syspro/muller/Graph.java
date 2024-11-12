package ru.nsu.syspro.muller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * Interface base operation graphs.
 */
public interface Graph {

    int getSize();
    boolean HaveVertex(int keyVer);
    boolean HaveEdge(int keyVer1, int keyVer2);

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
     * Creates a graph based on the input stream.
     * @param reader the main data input stream
     */
    void readBufferedReader(BufferedReader reader) throws IOException;

    /**
     * Compare graphs.
     * @param graph other graph
     * @return result compare
     */
    boolean equals(Graph graph);

}
