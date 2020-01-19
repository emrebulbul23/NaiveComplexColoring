package main;

import graph.Graph;

public class Main {
    public static void main(String[] args) {
        Graph g = new Graph(3,true);
        g.visualizeGraph();
        g.complexColoringInputStep();
        g.visualizeGraph();
        g.complexColoringOutputStep();
        g.visualizeGraphOutputSorted();
        g.complexColoringInputStep();
        g.visualizeGraph();
        g.complexColoringOutputStep();
        g.visualizeGraphOutputSorted();
        g.complexColoringInputStep();
        g.visualizeGraph();
        g.complexColoringOutputStep();
        g.visualizeGraphOutputSorted();
        g.complexColoringInputStep();
        g.visualizeGraph();
        g.complexColoringOutputStep();
        g.visualizeGraphOutputSorted();
    }
}
