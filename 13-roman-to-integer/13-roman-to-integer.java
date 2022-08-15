class Solution {
    public int romanToInt(String s) {
        int[] romanMap = new int[26];
        romanMap['I' - 'A'] = 1;
        romanMap['V' - 'A'] = 5;
        romanMap['X' - 'A'] = 10;
        romanMap['L' - 'A'] = 50;
        romanMap['C' - 'A'] = 100;
        romanMap['D' - 'A'] = 500;
        romanMap['M' - 'A'] = 1000;

        int n = s.length();
        int number = 0;

        for (int i = n - 1; i >= 0; i--){
            int currRomanNumber = romanMap[s.charAt(i) - 'A'];

            if (i != n - 1  &&  currRomanNumber < romanMap[s.charAt(i + 1) -'A'])
                number -= currRomanNumber;
            else
                number += currRomanNumber;
        }
        return number; 
    }
}

























