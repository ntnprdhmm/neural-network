import java.util.Arrays;

public class Layer {

    private int previousLayerNodes;
    private int nextLayerNodes;
    private Matrix weights;

    public Layer(int previousLayerNodes, int nextLayerNodes) {
        this.previousLayerNodes = previousLayerNodes;
        this.nextLayerNodes = nextLayerNodes;

        this.weights = new Matrix(nextLayerNodes, previousLayerNodes);
        this.weights.randomize();
    }

    public Matrix getWeights() {
        return this.weights;
    }

    public void setWeights(Matrix weights) {
        this.weights = weights;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < nextLayerNodes; i++) {
            str += "Neuron " + i + "\n";
            str += "- inputs weights: " + Arrays.toString(this.weights.getMatrix()[i]) + "\n";
        }
        return str;
    }

}
