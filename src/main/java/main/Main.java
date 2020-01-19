package main;

import graph.Graph;

public class Main {
    public static void main(String[] args) {
        Graph g = new Graph(3,true);
        g.visualizeGraph();

        g.complexColoring(7);
        g.visualizeGraph();
        g.visualizeGraphOutputSorted();

    }
}
