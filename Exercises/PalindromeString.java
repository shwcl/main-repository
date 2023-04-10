/* This function checks if a string is a palindrome */

package local.home;

public class PalindromeString {

    public static boolean isPalidrome(String str) {
        int midIndex = str.length() / 2;
        int lastIndex = str.length() - 1;

        for(int i=0; i <= midIndex - 1; i++) {
            if((str.charAt(i) != str.charAt(lastIndex - i))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        System.out.println("Result: " + isPalidrome("lomol"));
    }
}
