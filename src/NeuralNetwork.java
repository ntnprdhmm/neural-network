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

        this.hiddenLayer = new Layer(hiddenNodes, inputNodes);
        this.outputLayer = new Layer(outputNodes, hiddenNodes);
    }

    /**
     * Pass the inputs to the NN and return the NN's outputs
     * @param inputsArr the inputs values
     * @return the NN's outputs
     */
    public double[] feedForward(double[] inputsArr) {
        Matrix inputs = new Matrix(inputsArr);

        // input layer -> hidden layer
        Matrix hidden = this.hiddenLayer.getWeights().multiply(inputs);
        hidden = NeuralNetwork.activationFunction(hidden);

        // hidden layer -> output layer
        Matrix output = this.outputLayer.getWeights().multiply(hidden);
        output = NeuralNetwork.activationFunction(output);
        double[][] outputMatrix = output.getMatrix();

        // transform the output Matrix to a 1D array of double
        double[] outputArr = new double[output.getMatrix().length];
        for (int i = 0; i < outputArr.length; i++) {
            outputArr[i] = outputMatrix[i][0];
        }

        return outputArr;
    }

    public static Matrix activationFunction(Matrix m) {
        double[][] matrix = m.getMatrix();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++ ) {
                matrix[i][j] = NeuralNetwork.sigmoid(matrix[i][j]);
            }
        }

        return m;
    }

    public static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    public String toString() {
        String str = "";
        str += "==============================\n";
        str += "NEURAL NETWORK\n";
        str += "==============================\n";

        str += "Inputs: " + this.inputNodes + "\n\n";

        str += "HiddenLayer\n";
        str += this.hiddenLayer.toString() + "\n";

        str += "OutputLayer\n";
        str += this.outputLayer.toString() + "\n";

        return str;
    }

}