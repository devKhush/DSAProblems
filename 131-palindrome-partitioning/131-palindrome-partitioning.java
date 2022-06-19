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
            String substringFromIndexToI = s.substring(index, i + 1);

            if (isPalindrome(substringFromIndexToI)){
                list.add(substringFromIndexToI);
                palindromePartitioning(i + 1, s, n, answer, list);
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String str){
        int low = 0, high = str.length() - 1;

        while (low <= high){
            if (str.charAt(low) != str.charAt(high))
                return false;
            low++;
            high--;
        }
        return true;
    }
}