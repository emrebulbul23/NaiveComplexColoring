package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Graph {
    private int n;
    private List<Edge> edgeList;
    private Random random = new Random();

    /**
     * Generates an NxN consistently colored graph;
     *
     * @param n number of input nodes
     */
    public Graph(int n, boolean generateRandGraph) {
        this.n = n;
        this.edgeList = new ArrayList<>();
        this.random = new Random();

        if (generateRandGraph) {
            //color left links
            List<Integer> colors;
            List<Integer> connections = this.generateConnectionsArray();
            for (int i = 0; i < n; i++) {
                colors = this.generateShuffledSequenceArr();
                for (int j = 0; j < n; j++) {
                    int out = connections.get(i * n + j);
                    edgeList.add(new Edge(i, out, colors.get(j), 0, n)); //right color is placeholder
                }
            }

            //color right links
            for (int i = 0; i < n; i++) {
                int ind = 0;
                colors = this.generateShuffledSequenceArr();
                for (Edge e : edgeList) {
                    if (e.getOutputNode() == i) {
                        e.setRightLinkColor(colors.get(ind));
                        ind++;
                        if (ind == n)
                            break;
                    }
                }
            }
        }
    }

    public void addEdge(Edge e) {
        edgeList.add(e);
    }

    public void visualizeGraph() {
        System.out.println("Visualization of the graph.\n" +
                "It is input sorted and numbers in \n" +
                "the middle of links represent colors.\n");
        for (int i = 0; i < this.n; i++) {
            for (Edge e : edgeList) {
                if (e.getInputNode() == i)
                    System.out.println(e.toString());
            }
            System.out.println(" ");
        }
    }

    public void visualizeGraphOutputSorted() {
        System.out.println("Visualization of the graph.\n" +
                "It is output sorted and numbers in \n" +
                "the middle of links represent colors.\n");
        for (int i = 0; i < this.n; i++) {
            for (Edge e : edgeList) {
                if (e.getOutputNode() == i)
                    System.out.println(e.toString());
            }
            System.out.println(" ");
        }
    }

    public void complexColoring(int iteration){
        for (int i = 0; i < iteration; i++) {
            complexColoringInputStep();
            complexColoringOutputStep();
        }
    }


    public void complexColoringInputStep() {
        for (int i = 0; i < n; i++) {
            for (Edge e : edgeList) {
                if (e.getInputNode() == i && e.isVariable()) {
                    for (Edge pair : edgeList) {
                        if(!e.equals(pair)&&(e.getInputNode()==pair.getInputNode())){
                            int rightLinkColor = e.getRightLinkColor();
                            int leftLinkColor = e.getLeftLinkColor();
                            if(pair.isVariable()){
                                if(e.getColorPairIdentifier() == pair.getColorPairIdentifier()){
                                    int leftLinkColorPair = pair.getLeftLinkColor();
                                    e.setLeftLinkColor(leftLinkColorPair);
                                    pair.setLeftLinkColor(leftLinkColor);
                                }
                            }else{
                                if((rightLinkColor*n+rightLinkColor)==pair.getColorPairIdentifier()||
                                        (leftLinkColor*n+leftLinkColor)==pair.getColorPairIdentifier()){
                                    int leftLinkColorPair = pair.getLeftLinkColor();
                                    e.setLeftLinkColor(leftLinkColorPair);
                                    pair.setLeftLinkColor(leftLinkColor);

                                }
                            }
                        }

                    }
                }
            }
        }
    }

    public void complexColoringOutputStep() {
        for (int i = 0; i < n; i++) {
            for (Edge e : edgeList) {
                if (e.getOutputNode() == i && e.isVariable()) {
                    for (Edge pair : edgeList) {
                        if(!e.equals(pair)&&(e.getOutputNode()==pair.getOutputNode())){
                            int rightLinkColor = e.getRightLinkColor();
                            int leftLinkColor = e.getLeftLinkColor();
                            if(pair.isVariable()){
                                if(e.getColorPairIdentifier() == pair.getColorPairIdentifier()){
                                    int rightLinkColorPair = pair.getRightLinkColor();
                                    e.setRightLinkColor(rightLinkColorPair);
                                    pair.setRightLinkColor(rightLinkColor);
                                }
                            }else{
                                if((rightLinkColor*n+rightLinkColor)==pair.getColorPairIdentifier()||
                                        (leftLinkColor*n+leftLinkColor)==pair.getColorPairIdentifier()){
                                    int rightLinkColorPair = pair.getRightLinkColor();
                                    e.setRightLinkColor(rightLinkColorPair);
                                    pair.setRightLinkColor(rightLinkColor);
                                }
                            }
                        }

                    }
                }
            }
        }
    }



    private List<Integer> generateShuffledSequenceArr() {
        List<Integer> range = IntStream.rangeClosed(0, n - 1)
                .boxed().collect(Collectors.toList());
        Collections.shuffle(range);
        return range;
    }

    private List<Integer> generateConnectionsArray() {
        List<Integer> connections = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            connections.addAll(this.generateShuffledSequenceArr());
        }
        Collections.shuffle(connections);
        return connections;
    }
}
