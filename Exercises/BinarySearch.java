
package SearchAlgorithms;

public class BinarySearch {

    public static  int search(int[] arr, int target){
        int start = 0;
        int end = arr.length - 1;

        while(start <= end){
            int midpoint = start + (end - start)/2;

            if(target == arr[midpoint]){
                return midpoint;
            } else if (target < arr[midpoint]){
                end = midpoint - 1;
            } else {
                start = midpoint + 1;
            }
        }
        
        return - 1;


    }

    public static void main (String[] args){
                       //0  1  2  3  4   5   6   7   8    9
        int[] numbers = {1, 3, 5, 8, 10, 15, 78, 80, 96, 100};
        int target = 100;

      System.out.println(search(numbers, target));


    }
}
