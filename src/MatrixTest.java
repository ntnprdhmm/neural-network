import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MatrixTest {

    @Test
    void addN() {
        double n = 1;
        int rows = 2;
        int cols = 3;

        Matrix m = new Matrix(rows, cols);
        double[][] originalMatrix = new double[][]{{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}};

        m.setMatrix(originalMatrix);
        Matrix result = m.add(n);

        double[][] resultMatrix = result.getMatrix();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                assertEquals(resultMatrix[i][j], originalMatrix[i][j]+n);
            }
        }
    }

    @Test
    void addMatrix() {
        int rows = 2;
        int cols = 3;

        Matrix a = new Matrix(rows, cols);
        double[][] aMatrix = new double[][]{{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}};
        a.setMatrix(aMatrix);

        Matrix b = new Matrix(rows, cols);
        double[][] bMatrix = new double[][]{{2.0, 3.5, 1.0}, {1.2, 1.3, 0.8}};
        b.setMatrix(bMatrix);

        Matrix result = a.add(b);
        double[][] resultMatrix = result.getMatrix();
        double[][] expected = new double[][]{{3.0, 5.5, 4.0}, {5.2, 6.3, 6.8}};

        for (int i = 0; i < resultMatrix.length; i++) {
            for (int j = 0; j < resultMatrix[0].length; j++) {
                assertEquals(resultMatrix[i][j], expected[i][j]);
            }
        }
    }

    @Test
    void multiplyByN() {
        double n = 2;
        int rows = 2;
        int cols = 3;

        Matrix m = new Matrix(rows, cols);
        double[][] originalMatrix = new double[][]{{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}};

        m.setMatrix(originalMatrix);
        Matrix result = m.multiply(n);

        double[][] resultMatrix = result.getMatrix();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                assertEquals(resultMatrix[i][j], originalMatrix[i][j]*n);
            }
        }
    }

    @Test
    void multiplyByMatrix() {
        int rows = 2;
        int cols = 3;

        Matrix a = new Matrix(rows, cols);
        double[][] aMatrix = new double[][]{{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}};
        a.setMatrix(aMatrix);

        Matrix b = new Matrix(cols, rows);
        double[][] bMatrix = new double[][]{{7.0, 8.0}, {9.0, 10.0}, {11.0, 12.0}};
        b.setMatrix(bMatrix);

        Matrix result = a.multiply(b);
        double[][] resultMatrix = result.getMatrix();
        double[][] expected = new double[][]{{58, 64}, {139, 154}};

        for (int i = 0; i < resultMatrix.length; i++) {
            for (int j = 0; j < resultMatrix[0].length; j++) {
                assertEquals(resultMatrix[i][j], expected[i][j]);
            }
        }
    }

}
