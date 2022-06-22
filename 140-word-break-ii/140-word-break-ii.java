class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        ArrayList<String> allBrokenWords = new ArrayList<>();

        breakWord(0, s, s.length(), wordDict, new ArrayList<>(), allBrokenWords);
        return allBrokenWords;
    }

    private  void breakWord(int index, String s, int n, List<String> dictionary, ArrayList<String> currentBrokenWord, ArrayList<String> allBrokenWords){
        if (index == n){
           allBrokenWords.add(convertListIntoString(currentBrokenWord));
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
    
    private String convertListIntoString(ArrayList<String> list){
        int n = list.size();
        String string = "";
        String previousWord = "";
        
        int i = 0;
        
        for (String word : list){
            if (i < n-1)
                string += word + " ";
            previousWord = word;
            i++;
        }
        string += previousWord;
        return string;
    }
}