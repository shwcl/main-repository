/* This function returns a string in reverse order */

package local.home;

public class ReverseString2 {

    public static String reverse(String str) {

        String reverse = "";

        for(int i = str.length() - 1; i >= 0; i--) {
            reverse = reverse + str.charAt(i);
        }
        return reverse;
    }

    public static void main (String[] args) throws Exception{
        System.out.println(reverse("hello world"));
    }
}
