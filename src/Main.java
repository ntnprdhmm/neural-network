import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        NeuralNetwork n = new NeuralNetwork(3, 4, 1);
        //System.out.println(n);

        // train
        double[][] inputs = {{0, 0, 1}, {1, 0, 1}, {0, 1, 1}, {1, 1, 1}};
        double[][] expectedOutputs = {{0}, {1}, {1}, {0}};
        n.train(inputs, expectedOutputs);

        //System.out.println(n);

        // test
        double[] outputs = Matrix.vectorToArray(n.feedForward(inputs));

        System.out.println(Arrays.toString(outputs));
    }

}
