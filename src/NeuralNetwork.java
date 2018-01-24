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
    public Matrix feedForward(double[] inputsArr) {
        Matrix inputs = new Matrix(inputsArr);
        return this.feedForward(inputs);
    }

    /**
     * Pass the inputs to the NN and return the NN's outputs
     * @param inputs the inputs values
     * @return the NN's outputs
     */
    public Matrix feedForward(Matrix inputs) {

        // input layer -> hidden layer
        Matrix hidden = this.hiddenLayer.getWeights().multiply(inputs);
        hidden.add(hiddenLayer.getBias());
        hidden = NeuralNetwork.activationFunction(hidden);

        // hidden layer -> output layer
        Matrix output = this.outputLayer.getWeights().multiply(hidden);
        output.add(outputLayer.getBias());
        output = NeuralNetwork.activationFunction(output);

        return output;
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

        for (int i = 0; i < inputs.length; i++) {
            // convert input and expected output to Matrix
            Matrix inputMatrix = new Matrix(inputs[i]);
            Matrix expectedOutput = new Matrix(expectedOutputs[i]);
            // prediction
            Matrix Y = this.feedForward(inputMatrix);
            // calculate the error
            Matrix error = Y.subtract(expectedOutput);
            System.out.println(error);
        }
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