package BinaryHeaps.FindKPairsWithSmallestSums;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Objects;

// https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
// https://leetcode.com/problems/find-k-pairs-with-smallest-sums/editorial/
// Pre-requisite: K Max Sum Combinations
// Same as above problem, just min instead of max.

public class FindKPairsWithSmallestSums {
    /*************************************** MinHeap Solution ****************************************
     * Time Complexity: O(min(k*log(k) , m*n*log(m*n)))
        * We iterate O(min(k,m*n)) times to get the required number of pairs.
        * The visited set and heap both can grow up to a size of O(min(k,m*n)) because at each iteration
            we are inserting at most two pairs and popping one pair.
        * Insertions into a min-heap take an additional log factor. So, to insert O(min(k,m*n)) elements
            into minHeap, we need O(min(k*log(k), m*n*log(m*n)) time.

     * Space complexity: O(min(k,m*n))
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int m = nums2.length;

        List<List<Integer>> kSmallPairs = new ArrayList<>();
        HashSet<Pair> set = new HashSet<>();
        PriorityQueue<Pair> minHeap = new PriorityQueue<>((a, b) -> a.sum - b.sum);
        Pair initialPair = new Pair(0, 0, nums1[0] + nums2[0]);
        minHeap.add(initialPair);
        set.add(initialPair);

        while (k-- > 0  && !minHeap.isEmpty()){
            Pair pair = minHeap.remove();
            kSmallPairs.add(List.of(nums1[pair.i], nums2[pair.j]));

            if (pair.i + 1 < n){
                Pair nextIPair = new Pair(pair.i + 1, pair.j, nums1[pair.i + 1] + nums2[pair.j]);
                if (!set.contains(nextIPair)){
                    minHeap.add(nextIPair);
                    set.add(nextIPair);
                }
            }
            if (pair.j + 1 < m){
                Pair nextJPair = new Pair(pair.i, pair.j + 1, nums1[pair.i] + nums2[pair.j + 1]);
                if (!set.contains(nextJPair)){
                    minHeap.add(nextJPair);
                    set.add(nextJPair);
                }
            }
        }
        return kSmallPairs;
    }

    class Pair{
        int i, j, sum;
        public Pair(int i, int j, int sum){
            this.i = i;
            this.j = j;
            this.sum = sum;
        }

        @Override
        public boolean equals(Object o){
            if (getClass() != o.getClass())
                return false;
            Pair p = (Pair) o;
            return this.i == p.i && this.j == p.j && this.sum == p.sum;
        }

        @Override
        public int hashCode(){
            return Objects.hash(i, j, sum);
        }
    }
}
