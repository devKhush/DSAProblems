class Solution {
    public int[] topKFrequent(int[] arr, int k) {
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
    
    class Element implements Comparable<Element>{
        int value, frequency;
        public Element(int value, int frequency){
            this.value = value;
            this.frequency = frequency;
        }
        
        @Override
        public int compareTo(Element other){
            return other.frequency - this.frequency;
        }
    }
}