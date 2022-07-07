package Recursions_And_BackTracking.SplitStringIntoMaxNumberOfUniqueSubstrings;
import java.util.HashSet;

// PRE-REQUISITE: "PALINDROME PARTITIONING" & "WORD BREAK"

class SplitStringIntoMaxNumberOfUniqueSubstrings {
    private int maximumPartitions;
    
    public int maxUniqueSplit(String s) {
        maximumPartitions = Integer.MIN_VALUE;
        
        partitionStringIntoSubString(0, s, s.length(), new HashSet<>(), 0);
        return maximumPartitions;
    }
    
    
    public void partitionStringIntoSubString(int index, String s, int n, HashSet<String> subStringPartitions, int partitionsDone){
        if (index == n){
            maximumPartitions = Math.max(partitionsDone, maximumPartitions);
            return;
        }
        
        for (int i = index; i < n; i++){
            String subString = s.substring(index, i + 1);
            
            if (!subStringPartitions.contains(subString)){
                subStringPartitions.add(subString);
                partitionStringIntoSubString(i + 1, s, n, subStringPartitions, partitionsDone + 1);
                subStringPartitions.remove(subString);
            }
        }
    }
}