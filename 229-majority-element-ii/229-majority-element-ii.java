class Solution {
    
    public List<Integer> majorityElement(int[] arr) {
        int n = arr.length;
        int majorElement1 = 0, count1 = 0;
        int majorElement2 = 0, count2 = 0;
        
        for (int num : arr){
            if (num == majorElement1)
                count1++;
            
            else if (num == majorElement2)
                count2++;
            
            else if (count1 == 0){
                majorElement1 = num;
                count1 = 1;
            }
            
            else if (count2 == 0){
                majorElement2 = num;
                count2 = 1;
            }
            
            else{
                count1--;
                count2--;
            }
        }
        
        int majorElement1_Count = 0;
        int majorElement2_Count = 0;
        for (int num : arr){
            if (num == majorElement1)
                majorElement1_Count++;
            
            else if (num == majorElement2)
                majorElement2_Count++;
        }
        
        List<Integer> allMajorElements = new ArrayList<>();
        
        if (majorElement1_Count > n/3)
            allMajorElements.add(majorElement1);
        
        if (majorElement2_Count > n/3)
            allMajorElements.add(majorElement2);
        
        return allMajorElements;
    }
    
    
    
    // HashMap Solution ***************************************************************
    public List<Integer> majorityElement_HashMap(int[] arr) {
        int n = arr.length;
        HashMap<Integer, Integer> count = new HashMap<>();
        List<Integer> majorityElements = new ArrayList<>();

        for (int num : arr)
            count.put(num, count.getOrDefault(num, 0) + 1);

        for (int num : count.keySet())
            if (count.get(num) > n/3)
                majorityElements.add(num);
        
        return majorityElements;
    }
    
    
    // Brute force *********************************************************************
    public List<Integer> majorityElement_BruteForce(int[] arr) {
        int n = arr.length;
        
        List<Integer> majorityElements = new ArrayList<>();
        
        for (int i = 0; i < n; i++){
            int currCount = 0;
            for (int j = i; j < n; j++){
                if (arr[i] == arr[j])
                    currCount++;
            }
            
            if (currCount > n/3  &&  !majorityElements.contains(arr[i]))
                majorityElements.add(arr[i]);
        }
        
        return majorityElements;
    }
}