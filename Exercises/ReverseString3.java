/* This function returns a string in reverse order */

package local.home;

public class ReverseString3 {

    public Static String reverse(String str) {
        
        StringBuilder sb = new StringBuilder(str);
        
        return (sb.reverse()).toString();
    }

    public static void main(String[] args) {
        System.out.println(reverse("hello world"));
    }
}
