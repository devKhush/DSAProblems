package BinaryHeaps.TotalCostToHireKWorkers;
import java.util.PriorityQueue;

// https://leetcode.com/problems/total-cost-to-hire-k-workers/description/
// https://leetcode.com/problems/total-cost-to-hire-k-workers/editorial/

public class TotalCostToHireKWorkers {
    /********************************** Efficient MinHeap solution ***********************************
     * Time Complexity: O(m*log(m) + k*log(m))
        *  m -> candidates
        * initialization two priority queues of size m, which takes O(m*log(m)) time.
        * During the hiring rounds, we keep removing the top element from priority queues and
            adding new elements for up to k times. Thus this process takes O(k⋅log(m)) time.
     * Space Complexity: O(m)
        * store the first m and the last m workers in two priority queues
     */
    public long totalCost(int[] costs, int k, int candidates) {
        int n = costs.length;
        PriorityQueue<Integer> front = new PriorityQueue<>((a, b) -> a-b);
        PriorityQueue<Integer> back = new PriorityQueue<>((a, b) -> a-b);

        int frontInd = 0;
        int backInd = n - 1;
        for (frontInd = 0; frontInd < candidates; frontInd++){
            front.add(costs[frontInd]);
        }
        for (backInd = n-1; backInd >= n - candidates; backInd--){
            if (frontInd > backInd)
                break;
            back.add(costs[backInd]);
        }

        long cost = 0;
        while (k-- > 0){
            int frontMin = !front.isEmpty() ? front.peek() : (int)1e9;
            int backMin = !back.isEmpty() ? back.peek() : (int)1e9;

            if (frontMin <= backMin){
                cost += front.remove();
                if (frontInd <= backInd)
                    front.add(costs[frontInd++]);
            }
            else if (frontMin > backMin){
                cost += back.remove();
                if (frontInd <= backInd)
                    back.add(costs[backInd--]);
            }
        }
        return cost;
    }


    /********************************** Efficient Single MinHeap solution ***********************************
     * Time Complexity: O(m*log(m) + k*log(m))
        *  m -> candidates
        * initialization two priority queues of size m, which takes O(m*log(m)) time.
        * During the hiring rounds, we keep removing the top element from priority queues and
            adding new elements for up to k times. Thus this process takes O(k⋅log(m)) time.
     * Space Complexity: O(m)
        * store the first m and the last m workers in two priority queues
     */
    public long totalCost_(int[] costs, int k, int candidates) {
        int n = costs.length;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0]!=b[0] ? a[0]-b[0] : a[1]-b[1]);

        int frontInd = 0;
        int backInd = n - 1;
        for (frontInd = 0; frontInd < candidates; frontInd++){
            minHeap.add(new int[]{costs[frontInd], 0});
        }
        for (backInd = n-1; backInd >= n - candidates; backInd--){
            if (frontInd > backInd)
                break;
            minHeap.add(new int[]{costs[backInd], 1});
        }

        long cost = 0;
        while (k-- > 0){
            boolean frontGroup = minHeap.peek()[1] == 0 ? true : false;
            cost += minHeap.remove()[0];

            if (frontGroup  &&  frontInd <= backInd)
                minHeap.add(new int[]{costs[frontInd++], 0});

            else if (!frontGroup  &&  frontInd <= backInd)
                minHeap.add(new int[]{costs[backInd--], 1});
        }
        return cost;
    }
}
