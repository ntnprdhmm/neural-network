public class Matrix {

    private int rows;
    private int cols;
    private double[][] matrix;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new double[rows][cols];
    }

    /**
     * Create a new Matrix from a 1D array
     * @param arr a 1D array to transform into a Matrix
     */
    public Matrix(double[] arr) {
        this.rows = arr.length;
        this.cols = 1;
        this.matrix = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            this.matrix[i][0] = arr[i];
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        if (matrix.length != this.rows || matrix[0].length != this.cols) {
            throw new RuntimeException("The matrix must have the same size");
        }
        this.matrix = matrix;
    }

    /**
     * Init the matrix with double between -1 and 1
     */
    public void randomize() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.matrix[i][j] = (Math.random()*2) - 1;
            }
        }
    }

    /**
     * Add each element of the matrix passed in parameter to the matching element of the matrice
     * @param matrix the matrix to add
     * @return a new Matrix
     * @throws RuntimeException
     */
    public Matrix add(Matrix matrix) {
        if (matrix.getRows() != this.rows) {
            throw new RuntimeException("Matrices must have the same number of rows");
        } else if (matrix.getCols() != this.cols) {
            throw new RuntimeException("Matrices must have the same number of columns");
        }

        Matrix result = new Matrix(this.rows, this.cols);
        double[][] resultMatrix = result.getMatrix();
        double[][] matrixToAdd = matrix.getMatrix();

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                resultMatrix[i][j] = this.matrix[i][j] + matrixToAdd[i][j];
            }
        }

        result.setMatrix(resultMatrix);
        return result;
    }

    /**
     * Add n to each element in the matrix
     * @param n the number to add
     * @return a new Matrix
     */
    public Matrix add(double n) {
        Matrix result = new Matrix(this.rows, this.cols);
        double[][] resultMatrix = result.getMatrix();

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                resultMatrix[i][j] = this.matrix[i][j] + n;
            }
        }

        result.setMatrix(resultMatrix);
        return result;
    }

    /**
     * Multiply the matrix with a matrix given as parameter, and return the result
     * @param matrix the matrix to multiply
     * @return a new Matrix
     * @throws RuntimeException
     */
    public Matrix multiply(Matrix matrix) {
        if (this.cols != matrix.getRows()) {
            throw new RuntimeException("To do 'a*b', the number of columns of matrix 'a' should be equal to the number" +
                    "of rows of matrix 'b'");
        }

        Matrix result = new Matrix(this.rows, matrix.getCols());
        double[][] resultMatrix = result.getMatrix();

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                double sum = 0;

                for (int k = 0; k < this.cols; k++) {
                    sum += this.matrix[i][k] * matrix.getMatrix()[k][j];
                }

                resultMatrix[i][j] = sum;
            }
        }

        result.setMatrix(resultMatrix);
        return result;
    }

    /**
     * Multiply the matrix by a double
     * @param n the number to multiply
     * @return a new Matrix
     * @throws RuntimeException
     */
    public Matrix multiply(double n) {
        Matrix result = new Matrix(this.rows, this.cols);
        double[][] resultMatrix = result.getMatrix();

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                resultMatrix[i][j] = this.matrix[i][j] * n;
            }
        }

        result.setMatrix(resultMatrix);
        return result;
    }

    /**
     * Subtract the matrix by a number
     * @param n the number to subtract
     * @return a new Matrix
     */
    public Matrix subtract(double n) {
        return this.add(n * -1);
    }

    /**
     * Subtract the matrix by another matrix
     * @param m the Matrix to subtract
     * @return a new Matrix
     */
    public Matrix subtract(Matrix m) {
        return this.add(m.multiply(-1));
    }

    /**
     * Transpose the current Matrix
     * @return a new Matrix
     */
    public Matrix transpose() {
        Matrix transposed = new Matrix(this.cols, this.rows);
        double[][] transposedMatrix = transposed.getMatrix();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposedMatrix[j][i] = this.matrix[i][j];
            }
        }

        transposed.setMatrix(transposedMatrix);
        return transposed;
    }

    /**
     * Check if the given Matrix is a vector
     * @param m the Matrix to check
     * @return true if it's a vector, or false
     */
    private static boolean isVector(Matrix m) {
        return m.cols == 1;
    }

    /**
     * If the Matrix is a vector (only one col), convert it to a simple array
     * @param m the Matrix to convert
     * @return a simple array of double
     */
    public static double[] vectorToArray(Matrix m) {
        // check if it's a vector
        if (!Matrix.isVector(m)) {
            throw new RuntimeException("the given Matrix is not a vector");
        }

        double[][] matrix = m.getMatrix();

        // transform the output Matrix to a 1D array of double
        double[] arr = new double[m.rows];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = matrix[i][0];
        }

        return arr;
    }

    @Override
    public String toString() {
        String str = "";

        str += "----------------------------------------\n";
        str += "Matrix: rows=" + this.rows + " - cols=" + this.cols + "\n";
        str += "----------------------------------------\n";

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                str += this.matrix[i][j] + " ";
            }
            str += "\n";
        }

        return str;
    }

}
