public class RecursionPowerOfNumber {
    
    public static int getResultFromPower(int n, int p) {
        
        if (p == 0) {
            return 1;
        } else if (p == 1) {
            return n;
        }
        return getPower(n, p - 1) * n;
    }
    
  
	public static void main(String[] args) {
		System.out.println(getResultFromPower(5,4));
	}
}
