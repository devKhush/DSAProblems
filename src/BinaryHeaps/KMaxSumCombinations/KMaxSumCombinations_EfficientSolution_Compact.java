package BinaryHeaps.KMaxSumCombinations;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.PriorityQueue;

public class KMaxSumCombinations_EfficientSolution_Compact {
    public ArrayList<Integer> solve(ArrayList<Integer> listA, ArrayList<Integer> listB, int k) {
        int n = listA.size();
        int m = listB.size();
        listA.sort((a,b)->(a-b));
        listB.sort((a,b)->(a-b));

        ArrayList<Integer> kMaxCombinations = new ArrayList<>();

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[2]-a[2]);
        maxHeap.add(new int[]{n - 1, m - 1, listA.get(n-1) + listB.get(m-1)});

        HashSet<Pair> set = new HashSet<>();
        set.add(new Pair(n - 1, m - 1));

        while (k > 0){
            int i = maxHeap.peek()[0];
            int j = maxHeap.peek()[1];
            int value = maxHeap.remove()[2];
            kMaxCombinations.add(value);
            k--;

            if (i > 0  &&  !set.contains(new Pair(i - 1, j))){
                maxHeap.add(new int[]{i-1, j, listA.get(i-1)+listB.get(j)});
                set.add(new Pair(i - 1, j));
            }
            if (j > 0  &&  !set.contains(new Pair(i, j - 1))){
                maxHeap.add(new int[]{i, j-1, listA.get(i)+listB.get(j-1)});
                set.add(new Pair(i, j - 1));
            }
        }
        return kMaxCombinations;
    }

    static class Pair{
        int i, j;
        public Pair(int i, int j){
            this.i = i;
            this.j = j;
        }
        @Override
        public boolean equals(Object o){
            if (this.getClass() != o.getClass())
                return false;
            Pair pair = (Pair)o;
            return this.i==pair.i && this.j==pair.j;
        }
        @Override
        public int hashCode(){
            return Objects.hash(i, j);
        }
    }
}
