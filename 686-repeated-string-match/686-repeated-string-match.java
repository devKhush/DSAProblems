class Solution {
    public int repeatedStringMatch(String a, String b) {
        int n = a.length();
        int m = b.length();
        
        StringBuilder repeat = new StringBuilder();
        int minCount = 0;
        
        while (repeat.length() < m){
            repeat.append(a);
            minCount++;
        }
        if (repeat.toString().contains(b))
            return minCount;
        
        repeat.append(a);
        minCount++;
        
        if (repeat.toString().contains(b))
            return minCount;
        
        return -1;
    }
}