public class Matrix {

    private int rows;
    private int cols;
    private double[][] matrix;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new double[rows][cols];
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

}
