import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        // Initialize a scanner to read input from the user.
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for matrix dimensions and validate input.
        System.out.print("Enter the number of rows and columns for Matrix A and the number of columns for Matrix B: ");
        int rowsA = scanner.nextInt(); // Rows in Matrix A
        int colsA = scanner.nextInt(); // Columns in Matrix A (also rows in Matrix B)
        int colsB = scanner.nextInt(); // Columns in Matrix B

        // Prompt the user for the range of matrix elements.
        System.out.print("Enter the range for elements (min max): ");
        int min = scanner.nextInt(); // Minimum value for elements
        int max = scanner.nextInt(); // Maximum value for elements

        // Generate random matrices A and B with the specified dimensions and element range.
        Matrix a = Matrix.generateRandomMatrix(rowsA, colsA, min, max);
        Matrix b = Matrix.generateRandomMatrix(colsA, colsB, min, max);

        // Print the generated matrices to the console.
        System.out.println("Matrix 1");
        a.print();
        System.out.println("Matrix 2");
        b.print();

        // Create a ForkJoinPool for parallel matrix multiplication tasks.
        ForkJoinPool pool = new ForkJoinPool();

        // Perform matrix multiplication using a work-stealing approach.
        System.out.println("\nWork Stealing results:");
        long startTime = System.nanoTime(); // Record start time
        pool.invoke(new StealingTask(a, b, 0, rowsA - 1)); // Execute task
        long endTime = System.nanoTime(); // Record end time
        System.out.printf("Time taken (Work Stealing): %.3f ms\n", (endTime - startTime) / 1_000_000.0);

        // Perform matrix multiplication using a work-dealing approach.
        System.out.println("\nWork Dealing results:");
        startTime = System.nanoTime(); // Record start time
        pool.invoke(new DealingTask(a, b, 0, rowsA - 1)); // Execute task
        endTime = System.nanoTime(); // Record end time
        System.out.printf("Time taken (Work Dealing): %.3f ms", (endTime - startTime) / 1_000_000.0);

        // Close the scanner to release system resources.
        scanner.close();
    }
}
