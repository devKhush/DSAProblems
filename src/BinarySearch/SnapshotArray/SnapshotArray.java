package BinarySearch.SnapshotArray;
import java.util.ArrayList;

// https://leetcode.com/problems/snapshot-array/description/
// https://youtu.be/YpBVzPv8P8o

public class SnapshotArray {
    /*************************************** OP Design Solution **************************************
     * Intuition:
        * If we compare two consecutive snaps, they differ only at the position (indices) where the
            values were changed. So, instead of saving whole array, we only save the positions where
            changes in values occurs during consecutive snaps.

     * Time Complexity:
        * O(1) for set() and snap()
        * O(log(n)) for get() where n is the no. of times changes happened at index
     * Space Complexity: O(length * n)
        * length -> size of array
        * where n is the average no. of times changes happened at index
     */

    // Store the snap information at every index in form of the ArrayList of {snap_id, new_value}
    ArrayList<int[]>[] snaps;
    int snapID;
    public SnapshotArray(int length) {
        this.snaps = new ArrayList[length];
        this.snapID = 0;
        for (int i = 0; i < length; i++){
            snaps[i] = new ArrayList<>();
        }
    }

    public void set(int index, int val) {
        snaps[index].add(new int[]{snapID, val});
    }

    public int snap() {
        return snapID++;
    }

    public int get(int index, int snap_id) {
        ArrayList<int[]> list = snaps[index];
        // If the size is empty OR the first snap is greater than snap_id, then values hasn't been changed from 0 yet
        if (list.isEmpty() || list.get(0)[0] > snap_id)
            return 0;

        // Binary search to find the lower bound for the snap_id,
        int low = 0, high = list.size() - 1;
        int snap_index = -1;
        while (low <= high){
            int mid = low + ((high - low)>>1);
            if (list.get(mid)[0] <= snap_id){
                snap_index = mid;
                low = mid + 1;
            }
            else
                high = mid - 1;
        }
        return list.get(snap_index)[1];
    }
}
