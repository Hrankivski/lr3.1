import java.util.concurrent.RecursiveTask;

/**
 * DealingTask performs parallel matrix row-by-row multiplication using the Fork/Join framework
 * with the `invokeAll` approach for task execution.
 */
public class DealingTask extends RecursiveTask<Void> {
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
    public DealingTask(Matrix a, Matrix b, int startRow, int endRow) {
        this.a = a;
        this.b = b;
        this.startRow = startRow;
        this.endRow = endRow;
    }

    /**
     * The compute method divides the task into smaller subtasks or processes rows directly
     * when the range is small enough, using `invokeAll` for parallelism.
     *
     * @return Always returns null since the result is printed directly.
     */
    @Override
    protected Void compute() {
        // If the range of rows is small enough, perform the computation directly.
        if (endRow - startRow <= 10) {
            for (int i = startRow; i <= endRow; i++) {
                // Multiply the row of Matrix A with Matrix B and print the result.
                Matrix result = Matrix.multiplyRowByMatrix(a, b, i);
                result.print();
            }
        } else {
            // Divide the task into two subtasks and execute them in parallel.
            int mid = (startRow + endRow) / 2;

            // Use invokeAll to execute both subtasks concurrently.
            invokeAll(
                    new DealingTask(a, b, startRow, mid),
                    new DealingTask(a, b, mid + 1, endRow)
            );
        }
        return null;
    }
}
