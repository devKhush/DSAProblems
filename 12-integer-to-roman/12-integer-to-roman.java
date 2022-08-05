class Solution {
    public String intToRoman(int number) {
        int[] romanNumber = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanString = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"}; 
        
        StringBuilder roman = new StringBuilder();
        
        for (int i = 0; i < 13; i++){
            if (number >= romanNumber[i]){
                int count = number / romanNumber[i];
                
                while (count-- > 0)
                    roman.append(romanString[i]);
                
                number %= romanNumber[i];
            }
        }
        return roman.toString();
    }
}