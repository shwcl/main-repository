/* This function returns a string in reverse order */

package local.home;

public class ReverseString {
    public static String reverse(String str) {
        StringBuilder sb = new StringBuilder();
      
        for(int i= str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    public static void main (String[] args) {
        System.out.println(reverse("The black cat jumps over the moon"));
        System.out.println(reverse("the cow jumps over the moon"));
    }
}
