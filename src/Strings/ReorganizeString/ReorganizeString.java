package Strings.ReorganizeString;
import java.util.PriorityQueue;

// https://leetcode.com/problems/reorganize-string/description/
// https://leetcode.com/problems/reorganize-string/editorial/

public class ReorganizeString {
    /***************************************** Brute Force *************************************
     * Time Complexity: O(n*log(n))
        * Building Maxheap
        * Total queries on maxheap is of order n
     * Space Complexity: O(1)
        * MaxHeap & Count array are of constant size
     */
    public String reorganizeString__maxHeap(String s) {
        int n = s.length();

        int[] count = new int[26];
        for (int i = 0; i < n; i++){
            count[s.charAt(i) - 'a']++;
        }

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a,b) -> b[1] - a[1]);
        for (int i = 0; i < 26; i++){
            if (count[i] != 0)
                maxHeap.add(new int[]{'a' + i, count[i]});
        }

        StringBuilder sb = new StringBuilder();
        while (!maxHeap.isEmpty()){
            int[] first = maxHeap.remove();

            if (sb.length() == 0  ||  sb.charAt(sb.length() - 1) != first[0]){
                sb.append((char)first[0]);
                if (--first[1] > 0)
                    maxHeap.add(first);
            }
            else{
                if (maxHeap.isEmpty())
                    return "";

                int[] second = maxHeap.remove();
                sb.append((char)second[0]);
                if (--second[1] > 0)
                    maxHeap.add(second);
                maxHeap.add(first);
            }
        }
        return sb.toString();
    }


    /*************************************** Efficient Approach ********************************
     * Time Complexity: O(n)
        * Linear time
     * Space Complexity: O(1)
        * Constant space for space array
     */
    public String reorganizeString(String s){
        int n = s.length();
        int[] count = new int[26];
        for (int i = 0; i < n; i++){
            count[s.charAt(i) - 'a']++;
        }

        int maxCount = 0;
        int frequentChar = 0;
        for (int i = 0; i < 26; i++){
            if (maxCount < count[i]){
                maxCount = count[i];
                frequentChar = i;
            }
        }
        if (maxCount > (n + 1)/2)
            return "";

        char[] ans = new char[n];
        int index = 0;
        while (count[frequentChar] > 0){
            ans[index] = (char)('a' + frequentChar);
            count[frequentChar]--;
            index += 2;
        }
        for (int i = 0; i < 26; i++){
            while (count[i] > 0){
                if (index >= n)
                    index = 1;
                ans[index] = (char)(i + 'a');
                count[i]--;
                index += 2;
            }
        }
        return new String(ans);
    }
}
