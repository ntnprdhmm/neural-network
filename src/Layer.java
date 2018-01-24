import java.util.Arrays;

public class Layer {

    private int previousLayerNodes;
    private int nextLayerNodes;
    private Matrix weights;
    private Matrix bias;

    public Layer(int previousLayerNodes, int nextLayerNodes) {
        this.previousLayerNodes = previousLayerNodes;
        this.nextLayerNodes = nextLayerNodes;

        this.weights = new Matrix(previousLayerNodes, nextLayerNodes);
        this.weights.randomize();

        this.bias = new Matrix(previousLayerNodes, 1);
    }

    public Matrix getWeights() {
        return this.weights;
    }

    public Matrix getBias() {
        return this.bias;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < previousLayerNodes; i++) {
            str += "Neuron " + i + "\n";
            str += "- inputs weights: " + Arrays.toString(this.weights.getMatrix()[i]) + "\n";
        }
        return str;
    }

}
