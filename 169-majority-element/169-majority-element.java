class Solution {
    
    public int majorityElement(int[] arr) {
        int majorityElement = 0;
        int count = 0;
        
        for (int num : arr){
            if (count == 0)
                majorityElement = num;
            
            if (num == majorityElement)
                count++;
            
            else
                count--;
        }
        return majorityElement;
    }

    
    public int majorityElement_Sorting1(int[] arr) {
        Arrays.sort(arr);
        
        int majorElement = arr[0];
        int count = 1;
        
        int currCount = 1;
        for (int i=1; i<arr.length; i++){
            
            if (arr[i]==arr[i-1]){
                currCount++;
                if (currCount>count){
                    majorElement = arr[i];
                    count = currCount;
                }
                
            }
            else{
                currCount = 1;   
            }
        }
        return majorElement;
    }
    
    public int majorityElement_ByHashMap(int[] arr) {
        HashMap<Integer, Integer> count = new HashMap<>();
        
        for (int value : arr)
            count.put(value, count.getOrDefault(value, 0) + 1);
        
        for (int value : count.keySet()){
            if (count.get(value) > arr.length/2)
                return value;
        }
        return -1;
    }
    
    
    public int majorityElement_Sorting2(int[] arr) {
        Arrays.sort(arr);
        return arr[arr.length/2];
    }
}