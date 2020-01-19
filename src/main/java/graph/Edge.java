package graph;

public class Edge {
    private int leftLinkColor;
    private int rightLinkColor;
    private int inputNode;
    private int outputNode;
    private int numberOfColors;
    private int colorPairIdentifier;
    private boolean isVariable = true;

    public Edge(int inputNode, int outputNode, int leftLinkColor, int rightLinkColor, int numberOfColors) {
        this.inputNode = inputNode;
        this.outputNode = outputNode;
        this.leftLinkColor = leftLinkColor;
        this.rightLinkColor = rightLinkColor;
        this.numberOfColors = numberOfColors;

        if (this.rightLinkColor == this.leftLinkColor)
            isVariable = false;
        updateColorPairIdentifier();
    }

    public String toString() {
        return "(" + inputNode + ")---" + leftLinkColor + "---o---" + rightLinkColor + "---(" + outputNode + ")";
    }

    public int getLeftLinkColor() {
        return leftLinkColor;
    }

    public void setLeftLinkColor(int leftLinkColor) {
        this.leftLinkColor = leftLinkColor;
        updateColorPairIdentifier();
        setVariable(true);
        if(leftLinkColor==rightLinkColor)
            setVariable(false);
    }

    public int getRightLinkColor() {
        return rightLinkColor;
    }

    public void setRightLinkColor(int rightLinkColor) {
        this.rightLinkColor = rightLinkColor;
        updateColorPairIdentifier();
        setVariable(true);
        if(leftLinkColor==rightLinkColor)
            setVariable(false);
    }

    public int getInputNode() {
        return inputNode;
    }

    public void setInputNode(int inputNode) {
        this.inputNode = inputNode;
    }

    public int getOutputNode() {
        return outputNode;
    }

    public void setOutputNode(int outputNode) {
        this.outputNode = outputNode;
    }

    public boolean isVariable() {
        return isVariable;
    }

    public void setVariable(boolean variable) {
        isVariable = variable;
    }

    public int getColorPairIdentifier() {
        return colorPairIdentifier;
    }

    @Override
    public boolean equals(Object obj) {
        Edge e = (Edge) obj;
        return (this.inputNode == e.inputNode)
                &&(this.outputNode==e.outputNode)
                &&(this.leftLinkColor==e.leftLinkColor)
                &&(this.rightLinkColor==e.rightLinkColor);
    }

    public void setColorPairIdentifier(int colorPairIdentifier) {
        this.colorPairIdentifier = colorPairIdentifier;
    }

    private void updateColorPairIdentifier() {
        colorPairIdentifier = leftLinkColor + numberOfColors * rightLinkColor;
        if (rightLinkColor > leftLinkColor)
            colorPairIdentifier = rightLinkColor + numberOfColors * leftLinkColor;
    }

}
