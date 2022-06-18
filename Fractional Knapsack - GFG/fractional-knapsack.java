// { Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class Item {
    int value, weight;
    Item(int x, int y){
        this.value = x;
        this.weight = y;
    }
}

class GfG {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine().trim());
		while(t-->0){
            String inputLine[] = br.readLine().trim().split(" ");
            int n = Integer.parseInt(inputLine[0]);
            int w = Integer.parseInt(inputLine[1]);
            Item[] arr = new Item[n];
            inputLine = br.readLine().trim().split(" ");
            for(int i=0, k=0; i<n; i++){
                arr[i] = new Item(Integer.parseInt(inputLine[k++]), Integer.parseInt(inputLine[k++]));
            }
            System.out.println(String.format("%.2f", new Solution().fractionalKnapsack(w, arr, n)));
        }
    }
}// } Driver Code Ends


/*
class Item {
    int value, weight;
    Item(int x, int y){
        this.value = x;
        this.weight = y;
    }
}
*/

class Solution{
    //Function to get the maximum total value in the knapsack.
    double fractionalKnapsack(int knapsackWeight, Item[] allItems, int n) {
         // Sort all the items by VALUES PER WEIGHT in DECREASING ORDER
        Arrays.sort(allItems, new ItemComparator());

        double totalValue = 0.0;
        double totalWeight = 0.0;

        for (int i = 0; i < allItems.length; i++){
            if (allItems[i].weight + totalWeight<= knapsackWeight){
                totalValue += allItems[i].value;
                totalWeight += allItems[i].weight;
            }
            else{
                double valuePerWeightOFCurrentItem = (double)allItems[i].value / allItems[i].weight;
                double weightToBeAdded = knapsackWeight - totalWeight;
                totalWeight += weightToBeAdded;
                totalValue += valuePerWeightOFCurrentItem * weightToBeAdded;
            }
        }
        return totalValue;
    }
    
    
    static class ItemComparator implements Comparator<Item> {
        @Override
        public int compare(Item a, Item b){
            // Sort all the items by the items per weight
            double valuePerWeightInItemA = a.value/ ((double) a.weight);
            double valuePerWeightInItemB = b.value/ ((double) b.weight);

            if (valuePerWeightInItemA > valuePerWeightInItemB)
                return -1;
            else if (valuePerWeightInItemA < valuePerWeightInItemB)
                return 1;
            else return 0;

            // This can also be done
            // return Double.compare(valuePerWeightInItemB, valuePerWeightInItemA);
        }
    }
}