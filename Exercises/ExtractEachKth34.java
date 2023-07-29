package CodeSignal;

public class ExtractEachKth34 {

    static void solution(int [] numbers, int kthElement){
        for(int i=0; i < numbers.length; i++){

            int elementNumber = i + 1;

            // check if element number is a multiple of kth element
            if(elementNumber % kthElement == 0){
                System.out.println(numbers[i]);
            }
        }
    }


    public static void main(String[] args){
        int [] arr = {1,2,3,4,5,6,7,8,9,10,12,12,13,14,15};
        int kth = 3;
        solution(arr,3);
    }

}
