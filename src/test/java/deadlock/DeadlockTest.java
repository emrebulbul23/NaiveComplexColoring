package deadlock;

import graph.Edge;
import graph.Graph;
import org.junit.jupiter.api.Test;

public class DeadlockTest {
    @Test
    public void deadlockTest(){
        Graph g = new Graph(3,false);
        g.addEdge(new Edge(0,0,0,0,3));
        g.addEdge(new Edge(0,1,1,1,3));
        g.addEdge(new Edge(1,1,1,0,3));
        g.addEdge(new Edge(1,2,0,0,3));
        g.addEdge(new Edge(2,0,1,1,3));
        g.addEdge(new Edge(2,2,0,1,3));
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
    }

}
