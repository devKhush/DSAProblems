class Solution {
    public int repeatedStringMatch(String a, String b) {
        int n = a.length();
        int m = b.length();
        
        String repeat = "";
        int minCount = 0;
        
        while (repeat.length() < m){
            repeat += a;
            minCount++;
        }
        if (repeat.contains(b))
            return minCount;
        
        repeat += a;
        minCount++;
        
        if (repeat.contains(b))
            return minCount;
        
        return -1;
    }
}