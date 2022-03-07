import java.util.ArrayList;

class Solution {
    public int calPoints(String[] ops) {
        
        ArrayList<Integer> records = new ArrayList<>();
        int j = -1;
        
        for (int i=0; i<ops.length; i++){
            switch(ops[i]){
                case "+":
                    records.add(records.get(j) + records.get(j-1));
                    j++;
                    break;
                case "C":
                    records.remove(j);
                    j--;
                    break;
                case "D":
                    records.add(2*records.get(j++));
                    break;
                default:
                    records.add(Integer.parseInt(ops[i]));
                    j++;
            }
        }
        
        int sum = 0;
        for (int i=0; i<records.size(); i++)
            sum += records.get(i);
        return sum;
    }
}