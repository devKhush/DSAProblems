class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        ArrayList<String> allBrokenWords = new ArrayList<>();

        breakWord(0, s, s.length(), wordDict, new ArrayList<>(), allBrokenWords);
        return allBrokenWords;
    }

    private void breakWord(int index, String s, int n, List<String> dictionary, ArrayList<String> currentBrokenWord, ArrayList<String> allBrokenWords){
        if (index == n){
            allBrokenWords.add(String.join(" ", currentBrokenWord));
            return;
        }

        for (int i = index; i < n; i++){
            String subString = s.substring(index, i + 1);

            if (dictionary.contains(subString)){
                currentBrokenWord.add(subString);
                breakWord(i + 1, s, n, dictionary, currentBrokenWord, allBrokenWords);
                currentBrokenWord.remove(currentBrokenWord.size() - 1);
            }
        }
    }
}