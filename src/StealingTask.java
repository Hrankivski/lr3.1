import java.util.concurrent.RecursiveTask;

public class StealingTask extends RecursiveTask<Void> {
    private Matrix a;
    private Matrix b;
    private int startRow;
    private int endRow;

    public StealingTask(Matrix a, Matrix b, int startRow, int endRow) {
        this.a = a;
        this.b = b;
        this.startRow = startRow;
        this.endRow = endRow;
    }

    @Override
    protected Void compute() {
        if (endRow - startRow <= 10) {
            for (int i = startRow; i <= endRow; i++) {
                Matrix result = Matrix.multiplyRowByMatrix(a, b, i);
                result.print();
            }
        } else {
            int mid = (startRow + endRow) / 2;
            StealingTask left = new StealingTask(a, b, startRow, mid);
            StealingTask right = new StealingTask(a, b, mid + 1, endRow);
            left.fork();
            right.compute();
            left.join();
        }
        return null;
    }
}
