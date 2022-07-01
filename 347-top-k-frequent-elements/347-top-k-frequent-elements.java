class Solution {
//     // Approach 2 Without using Objects ************************************************************
//     public int[] topKFrequent(int[] arr, int k) {
//         HashMap<Integer, Integer> frequencies = new HashMap<>();

//         for (int num : arr)
//             frequencies.put(num, frequencies.getOrDefault(num, 0) + 1);

//         PriorityQueue<Integer> maxHeap = new PriorityQueue<>((key1, key2) -> (frequencies.get(key2) - frequencies.get(key1)));

//         for (int key : frequencies.keySet())
//             maxHeap.add(key);

//         int[] kFrequentElement = new int[k];
//         for (int i = 0; i < k; i++)
//             kFrequentElement[i] = maxHeap.remove();

//         return kFrequentElement;
//     }
    
    
//     // Aprroach 1 Using HashMap and MaxHeap *******************************************************
//     public int[] topKFrequent_1(int[] arr, int k) {
//         HashMap<Integer, Integer> frequencies = new HashMap<>();
        
//         for (int num : arr)
//             frequencies.put(num, frequencies.getOrDefault(num, 0) + 1);
        
//         PriorityQueue<Element> maxHeap = new PriorityQueue<>();
        
//         for (int value : frequencies.keySet())
//             maxHeap.add(new Element(value, frequencies.get(value)));
        
//         int[] kFrequentElement = new int[k];
        
//         for (int i = 0; i < k; i++)
//             kFrequentElement[i] = maxHeap.remove().value;
        
//         return kFrequentElement;
//     }
    
//     class Element implements Comparable<Element>{
//         int value, frequency;
//         public Element(int value, int frequency){
//             this.value = value;
//             this.frequency = frequency;
//         }
        
//         @Override
//         public int compareTo(Element other){
//             return other.frequency - this.frequency;
//         }
//     }
    
    public int[] topKFrequent(int[] arr, int k) {
        HashMap<Integer, Integer> frequencies = new HashMap<>();
        for (int value : arr)
            frequencies.put(value, frequencies.getOrDefault(value, 0) + 1);

        PriorityQueue<Element> minHeap = new PriorityQueue<>();

        int i = 0;
        for (int value : frequencies.keySet()){
            if (i < k) {
                minHeap.add(new Element(value, frequencies.get(value)));
                i++;
            }
            else if (i >= k  &&  frequencies.get(value) > minHeap.peek().frequency){
                minHeap.remove();
                minHeap.add(new Element(value, frequencies.get(value)));
            }
        }

        i = 0;
        int[] kFrequentElements = new int[k];
        while (!minHeap.isEmpty())
            kFrequentElements[i++] = minHeap.remove().value;

        return kFrequentElements;
    }

    static class Element implements Comparable<Element>{
        int value, frequency;
        public Element(int value, int frequency) {
            this.value = value;
            this.frequency = frequency;
        }
        @Override
        public int compareTo(Element other){
            return this.frequency - other.frequency;
        }
    }
}