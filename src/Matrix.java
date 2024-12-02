import java.util.Random;

/**
 * Matrix class provides functionality to create, manipulate, and perform operations on matrices.
 */
public class Matrix {
    private int[][] data; // 2D array to store matrix elements

    /**
     * Constructor to initialize a matrix with specified dimensions.
     *
     * @param rows Number of rows in the matrix.
     * @param cols Number of columns in the matrix.
     */
    public Matrix(int rows, int cols) {
        this.data = new int[rows][cols];
    }

    /**
     * Generates a random matrix with elements in the specified range.
     *
     * @param rows Number of rows in the matrix.
     * @param cols Number of columns in the matrix.
     * @param min  Minimum value for the random elements.
     * @param max  Maximum value for the random elements.
     * @return A new Matrix object filled with random values.
     */
    public static Matrix generateRandomMatrix(int rows, int cols, int min, int max) {
        Matrix matrix = new Matrix(rows, cols);
        Random rand = new Random();

        // Fill the matrix with random integers in the range [min, max].
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix.data[i][j] = rand.nextInt(max - min + 1) + min;
            }
        }
        return matrix;
    }

    /**
     * Getter for the matrix data.
     *
     * @return The 2D array representing the matrix.
     */
    public int[][] getData() {
        return data;
    }

    /**
     * Prints the matrix to the console.
     */
    public void print() {
        for (int[] row : data) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    /**
     * Multiplies a specific row of Matrix A with Matrix B.
     *
     * @param a   The first matrix (Matrix A).
     * @param b   The second matrix (Matrix B).
     * @param row The row index from Matrix A to be multiplied.
     * @return A Matrix object containing the result of the multiplication for the specified row.
     */
    public static Matrix multiplyRowByMatrix(Matrix a, Matrix b, int row) {
        int colsB = b.data[0].length; // Number of columns in Matrix B
        Matrix result = new Matrix(1, colsB);

        // Perform row-by-column multiplication.
        for (int j = 0; j < colsB; j++) {
            for (int k = 0; k < a.data[0].length; k++) {
                result.data[0][j] += a.data[row][k] * b.data[k][j];
            }
        }
        return result;
    }
}
