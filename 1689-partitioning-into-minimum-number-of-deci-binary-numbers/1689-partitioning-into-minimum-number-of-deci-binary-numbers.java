class Solution {
    public int minPartitions(String n) {
        int maxNumber = 0;
        char[] number = n.toCharArray();
        
        for (char digit : number)
            maxNumber = Math.max(maxNumber, digit - '0');
        return maxNumber;
    }
}