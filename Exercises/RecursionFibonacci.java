public class RecursionFibonacci {
    public static int fibonacci(int n) {
        if(n == 1) {
            return 1;
        } else if (n == 2) {
            return 1;
        }
        return fibonacci(n-2) + fibonacci(n-1);
    }

    public static void main (String[] args) {
        System.out.println("The answer is: " + fibonacci(4));
    }
}
