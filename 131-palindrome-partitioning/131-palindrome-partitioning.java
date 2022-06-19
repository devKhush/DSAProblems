class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> allPalindromePartitions = new ArrayList<>();

        palindromePartitioning(0, s, s.length(), allPalindromePartitions, new ArrayList<>());
        return allPalindromePartitions;
    }

    private void palindromePartitioning(int index, String s, int n, List<List<String>> answer, ArrayList<String> list){
        if (index == n){
            answer.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i < n; i++){

            if (isPalindrome(s, index, i)){
                String substringFromIndexToI = s.substring(index, i + 1);
                list.add(substringFromIndexToI);
                palindromePartitioning(i + 1, s, n, answer, list);
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String str, int startIndex, int endIndex){
        int low = startIndex, high = endIndex;

        while (low <= high){
            if (str.charAt(low) != str.charAt(high))
                return false;
            low++;
            high--;
        }
        return true;
    }
}