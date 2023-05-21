/* This function checks if a number is a palindrome */

package local.home;

public class PalindromeNumber {

    public static boolean isPalindrome(int num) {

        String strNumber = String.valueOf(num);
        StringBuilder sb = new StringBuilder(strNumber);

        return strNumber.equals(sb.reverse().toString());
    }


    public static void main(String[]args) {
        System.out.println(isPalindrome(123454321));
    }
}
