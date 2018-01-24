import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        NeuralNetwork n = new NeuralNetwork(2, 2, 1);
        System.out.println(n);

        // train
        double[][] inputs = {{0, 0}, {1, 0}, {0, 1}, {1, 1}};
        double[][] expectedOutputs = {{0}, {1}, {1}, {0}};
        n.train(inputs, expectedOutputs);

        // test
        double[] input = new double[]{1, 0};
        double[] output = Matrix.vectorToArray(n.feedForward(input));

        System.out.println(Arrays.toString(output));
    }

}
