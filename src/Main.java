import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of rows and columns for Matrix A and the number of columns for Matrix B:");
        int rowsA = scanner.nextInt();
        int colsA = scanner.nextInt();
        int colsB = scanner.nextInt();

        System.out.print("Enter the range for elements (min max):");
        int min = scanner.nextInt();
        int max = scanner.nextInt();

        Matrix a = Matrix.generateRandomMatrix(rowsA, colsA, min, max);
        Matrix b = Matrix.generateRandomMatrix(colsA, colsB, min, max);

        a.print();
        b.print();

        ForkJoinPool pool = new ForkJoinPool();

        System.out.println("\nWork Stealing results:");
        long startTime = System.nanoTime();
        pool.invoke(new StealingTask(a, b, 0, rowsA - 1));
        long endTime = System.nanoTime();
        System.out.printf("Time taken (Work Stealing): %.3f ms\n", (endTime - startTime) / 1000_000.0 );

        System.out.println("\nWork Dealing results:");
        startTime = System.nanoTime();
        pool.invoke(new DealingTask(a, b, 0, rowsA - 1));
        endTime = System.nanoTime();
        System.out.printf("Time taken (Work Dealing): %.3f ms", (endTime - startTime) / 1000_000.0);


        scanner.close();
    }
}
