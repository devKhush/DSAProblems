class Solution {
    public int minSetSize(int[] arr) {
        int n = arr.length;
        
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        
        for (int val : arr){
            freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);
        }
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b)->(b - a));
        
        for (int values : freqMap.keySet()){
            maxHeap.add(freqMap.get(values));
        }
        
        int elementRemoved = 0;
        int setSize = 0;
        
        while (elementRemoved < n/2){
            int frequency = maxHeap.remove();
            elementRemoved += frequency;
            setSize++;
        }
        return setSize;
    }
}