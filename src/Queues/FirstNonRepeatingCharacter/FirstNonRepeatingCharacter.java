package Queues.FirstNonRepeatingCharacter;

// https://www.youtube.com/watch?v=5co5Gvp_-S0

class FirstNonRepeatingCharacter {
    // O(n^2)
    public int firstUniqChar_O_NSquare(String s) {
        
        for (int i=0; i<s.length(); i++){
            boolean seenDuplicate = false;
            
            for (int j = 0; j<s.length(); j++){
                if (i!=j && s.charAt(i)==s.charAt(j)){
                    seenDuplicate = true;
                    break;
                }
            }
            if (!seenDuplicate)
                return i;
        }
        
        return -1;
    }
}