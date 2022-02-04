package Strings.ReverseWordsInGivenString;

public class ReverseWords {
    String reverseWords(String s) {
        String[] words = s.split("\\.");
        String answer = "";
        for (int i = words.length-1; i>=0; i--) {
            answer += words[i];
            if (i != 0)
                answer += ".";
        }
        return answer;
    }

    public static void main(String[] args) {
        String s = "i.like.this.program.very.much";
        String ans = new ReverseWords().reverseWords(s);
        System.out.println(ans);
    }
}
