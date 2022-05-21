package Recursions_And_BackTracking.SubSequencesOfArray;
import java.util.ArrayList;

// https://youtu.be/AxNNVECce8c

public class SubSequencesOfArray {

    private ArrayList<ArrayList<Integer>> answer;

    public void subSequencesOfArray(int index, ArrayList<Integer> list, int[] arr){
        if (index == arr.length){
            System.out.println(list);
            answer.add(new ArrayList<>(list));
            return;
        }

        subSequencesOfArray(index + 1, list, arr);

        list.add(arr[index]);
        subSequencesOfArray(index + 1, list, arr);
        list.remove((Integer) arr[index]);

        /*
        // To get answer in reverse order OR acc. to explanation:

        list.add(arr[index]);
        subSequencesOfArray(index + 1, list, arr);
        list.remove((Integer) arr[index]);

        subSequencesOfArray(index + 1, list, arr);
         */
    }

    public void getSubSequencesOfArray(int[] arr){
        answer = new ArrayList<>();
        subSequencesOfArray(0, new ArrayList<Integer>(), arr);

        System.out.println();
        System.out.println(answer);
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 2};
        new SubSequencesOfArray().getSubSequencesOfArray(arr);
    }

}
