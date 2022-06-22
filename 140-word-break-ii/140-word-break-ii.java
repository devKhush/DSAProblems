class Solution {
     public List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<String> dictionary = new HashSet<>(wordDict);

        List<String> allBrokenSentences = new ArrayList<>();

        breakWord(0, s, s.length(), dictionary, new ArrayList<>(), allBrokenSentences);
        return allBrokenSentences;
    }

    private void breakWord(int index, String s, int n, HashSet<String> dictionary, ArrayList<String> currenBrokenWords, List<String> allBrokenWords){
        if (index == n){
            allBrokenWords.add(String.join(" ", currenBrokenWords));
            return;
        }

        for (int i = index; i < n; i++){
            String subString = s.substring(index, i+1);

            if (dictionary.contains(subString)){
                currenBrokenWords.add(subString);
                breakWord(i + 1, s, n, dictionary, currenBrokenWords, allBrokenWords);
                currenBrokenWords.remove(currenBrokenWords.size() - 1);
            }
        }
    }
 
}