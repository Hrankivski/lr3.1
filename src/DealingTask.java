import java.util.concurrent.RecursiveTask;

public class DealingTask extends RecursiveTask<Void> {
    private Matrix a;
    private Matrix b;
    private int startRow;
    private int endRow;

    public DealingTask(Matrix a, Matrix b, int startRow, int endRow) {
        this.a = a;
        this.b = b;
        this.startRow = startRow;
        this.endRow = endRow;
    }

    @Override
    protected Void compute() {
        if (endRow - startRow <= 10) { // Базовий випадок, коли кількість рядків менша або рівна 10
            for (int i = startRow; i <= endRow; i++) {
                Matrix result = Matrix.multiplyRowByMatrix(a, b, i);
                result.print(); // Виводимо результат кожного рядка відразу
            }
        } else {
            int mid = (startRow + endRow) / 2;
            invokeAll(
                    new DealingTask(a, b, startRow, mid),
                    new DealingTask(a, b, mid + 1, endRow)
            );
        }
        return null;
    }
}
