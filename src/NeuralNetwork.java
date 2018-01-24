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
     * @return the NN's outputs for each layer
     */
    public Matrix[] feedForward(double[][] inputsArr) {
        Matrix inputs = new Matrix(inputsArr);
        return this.feedForward(inputs);
    }

    /**
     * Pass the inputs to the NN and return the NN's outputs
     * @param l0 the inputs values
     * @return the NN's outputs for each layer
     */
    public Matrix[] feedForward(Matrix l0) {

        // input layer -> hidden layer
        Matrix hidden = l0.multiply(this.hiddenLayer.getWeights());
        Matrix l1 = NeuralNetwork.activationFunction(hidden);
        // hidden layer -> output layer
        Matrix output = l1.multiply(this.outputLayer.getWeights());
        Matrix l2 = NeuralNetwork.activationFunction(output);

        return new Matrix[]{l0, l1, l2};
    }

    /**
     * Train the NN
     * @param inputs training inputs
     * @param expectedOutputs expected outputs for the given inputs
     */
    public void train(double[][] inputs, double[][] expectedOutputs) {
        // there must be k array of length this.inputsNodes in inputs
        if (inputs.length > 0 && inputs[0].length != this.inputNodes) {
            throw new RuntimeException("the number of values in each input must be equal to the number of neurons " +
                    "of the input layer");
        }
        // there must be k array of length this.outputNodes in expectedOutputs
        if (expectedOutputs.length < inputs.length) {
            throw new RuntimeException("each input must have an expected output");
        }
        if (expectedOutputs.length > 0 && expectedOutputs[0].length != this.outputNodes) {
            throw new RuntimeException("the number of values in each expected output must be equal to the number of " +
                    "neurons of the output layer");
        }

        for (int i = 0; i < 100; i++) {
            // predict
            Matrix[] layersOutputs = feedForward(inputs);
            Matrix l0 = layersOutputs[0];
            Matrix l1 = layersOutputs[1];
            Matrix l2 = layersOutputs[2];

            Matrix Y = new Matrix(expectedOutputs);

            // calculate the error
            Matrix l2Error = Y.subtract(l2);
            Matrix l2Delta = l2Error.hadamardProduct(NeuralNetwork.errorDelta(l2));
            Matrix l1Error = l2.multiply(outputLayer.getWeights().transpose());
            Matrix l1Delta = l1Error.hadamardProduct(NeuralNetwork.errorDelta(l1));

            // update the weights
            outputLayer.setWeights(outputLayer.getWeights().add(l1.transpose().multiply(l2Delta)));
            hiddenLayer.setWeights(hiddenLayer.getWeights().add(l0.transpose().multiply(l1Delta)));
        }
    }

    public static Matrix errorDelta(Matrix m) {
        double[][] matrix = m.getMatrix();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++ ) {
                matrix[i][j] = NeuralNetwork.sigmoidDeriv(matrix[i][j]);
            }
        }

        return m;
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

    public static double sigmoidDeriv(double x) {
        return x * (1-x);
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