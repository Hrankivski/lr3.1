import java.util.Random;

public class Matrix {
    private int[][] data;

    public Matrix(int rows, int cols) {
        this.data = new int[rows][cols];
    }

    public static Matrix generateRandomMatrix(int rows, int cols, int min, int max) {
        Matrix matrix = new Matrix(rows, cols);
        Random rand = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix.data[i][j] = rand.nextInt(max - min + 1) + min;
            }
        }
        return matrix;
    }

    public int[][] getData() {
        return data;
    }

    public void print() {
        for (int[] row : data) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    public static Matrix multiplyRowByMatrix(Matrix a, Matrix b, int row) {
        int colsB = b.data[0].length;
        Matrix result = new Matrix(1, colsB);
        for (int j = 0; j < colsB; j++) {
            for (int k = 0; k < a.data[0].length; k++) {
                result.data[0][j] += a.data[row][k] * b.data[k][j];
            }
        }
        return result;
    }
}
