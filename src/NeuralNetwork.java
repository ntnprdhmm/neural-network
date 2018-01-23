import java.util.Arrays;

public class NeuralNetwork {

    private int inputNodes;
    private int hiddenNodes;
    private int outputNodes;
    private Layer inputLayer;
    private Layer hiddenLayer;
    private Layer outputLayer;

    public NeuralNetwork(int inputNodes, int hiddenNodes, int outputNodes) {
        this.inputNodes = inputNodes;
        this.hiddenNodes = hiddenNodes;
        this.outputNodes = outputNodes;

        this.inputLayer = new Layer(inputNodes, hiddenNodes);
        this.hiddenLayer = new Layer(hiddenNodes, outputNodes);
        this.outputLayer = new Layer(outputNodes, 1);
    }

    public double[] feedForward(double[] inputs) {
        // TODO: feed forward
        return new double[]{};
    }

    public String toString() {
        String str = "";
        str += "==============================\n";
        str += "NEURAL NETWORK\n";
        str += "==============================\n";

        str += "InputLayer\n";
        str += inputLayer.toString() + "\n";

        str += "HiddenLayer\n";
        str += hiddenLayer.toString() + "\n";

        str += "OutputLayer\n";
        str += outputLayer.toString() + "\n";

        return str;
    }

}