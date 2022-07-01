// { Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;
class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            String[] s = br.readLine().trim().split(" ");
            int n = Integer.parseInt(s[0]);
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(s[i + 1]);
            }
            int k = Integer.parseInt(br.readLine().trim());
            Solution obj = new Solution();
            int[] ans = obj.topK(nums, k);
            for (int i = 0; i < ans.length; i++) System.out.print(ans[i] + " ");
            System.out.println();
        }
    }
}
// } Driver Code Ends


class Solution {
    
    public static int[] topK(int[] arr, int k) {
        HashMap<Integer, Integer> frequencies = new HashMap<>();
        for (int num : arr)
            frequencies.put(num, frequencies.getOrDefault(num, 0) + 1);

        PriorityQueue<Element> maxHeap = new PriorityQueue<>();

        for (int value : frequencies.keySet())
            maxHeap.add(new Element(value, frequencies.get(value)));

        int[] kFrequentElement = new int[k];

        for (int i = 0; i < k; i++)
            kFrequentElement[i] = maxHeap.remove().value;

        return kFrequentElement;
    }

    static class Element implements Comparable<Element>{
        int value, frequency;
        public Element(int value, int frequency){
            this.value = value;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(Element other){
            if (other.frequency != this.frequency)
                return other.frequency - this.frequency;
            else
                return other.value - this.value;
        }
    }

}