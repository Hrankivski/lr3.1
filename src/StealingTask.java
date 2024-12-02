import java.util.concurrent.RecursiveTask;

/**
 * StealingTask performs parallel matrix row-by-row multiplication using the Fork/Join framework.
 */
public class StealingTask extends RecursiveTask<Void> {
    private Matrix a;      // The first matrix (Matrix A) for multiplication
    private Matrix b;      // The second matrix (Matrix B) for multiplication
    private int startRow;  // Starting row for this task
    private int endRow;    // Ending row for this task

    /**
     * Constructor to initialize the task with matrices and the row range to process.
     *
     * @param a         The first matrix (Matrix A).
     * @param b         The second matrix (Matrix B).
     * @param startRow  The starting row index for processing.
     * @param endRow    The ending row index for processing.
     */
    public StealingTask(Matrix a, Matrix b, int startRow, int endRow) {
        this.a = a;
        this.b = b;
        this.startRow = startRow;
        this.endRow = endRow;
    }

    /**
     * The compute method divides the task into smaller subtasks or performs the computation directly
     * when the range of rows is small enough.
     *
     * @return Always returns null since the result is printed directly.
     */
    @Override
    protected Void compute() {
        // If the task range is small enough, perform computation directly.
        if (endRow - startRow <= 10) {
            for (int i = startRow; i <= endRow; i++) {
                // Multiply the row of Matrix A with Matrix B and print the result.
                Matrix result = Matrix.multiplyRowByMatrix(a, b, i);
                result.print();
            }
        } else {
            // Divide the task into two subtasks for further parallelism.
            int mid = (startRow + endRow) / 2;

            // Create subtasks for the left and right halves.
            StealingTask left = new StealingTask(a, b, startRow, mid);
            StealingTask right = new StealingTask(a, b, mid + 1, endRow);

            // Fork the left subtask and compute the right subtask directly.
            left.fork();
            right.compute();

            // Wait for the left subtask to complete.
            left.join();
        }
        return null;
    }
}
