class Solution {
    public String intToRoman(int number) {
        HashMap<Integer, String> romans = new HashMap<>();
        romans.put(1, "I");
        romans.put(4, "IV");
        romans.put(5, "V");
        romans.put(9, "IX");
        romans.put(10, "X");
        romans.put(40, "XL");
        romans.put(50, "L");
        romans.put(90, "XC");
        romans.put(100, "C");
        romans.put(400, "CD");
        romans.put(500, "D");
        romans.put(900, "CM");
        romans.put(1000, "M");
        
        int[] numbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        
        StringBuilder roman = new StringBuilder();
        
        for (int i = 0; i < 13; i++){
            if (number >= numbers[i]){
                int count = number / numbers[i];
                
                while (count-- > 0)
                    roman.append(romans.get(numbers[i]));
                
                number %= numbers[i];
            }
        }
        return roman.toString();
    }
}