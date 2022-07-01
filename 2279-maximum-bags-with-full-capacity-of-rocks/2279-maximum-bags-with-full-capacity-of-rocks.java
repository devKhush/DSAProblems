class Solution {
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int[] rocksNeededToFillBags = new int[capacity.length];
        
        for (int i = 0; i < rocksNeededToFillBags.length; i++)
            rocksNeededToFillBags[i] = capacity[i] - rocks[i];
        
        Arrays.sort(rocksNeededToFillBags);
        
        int bagsFilled = 0;
        for (int i = 0; i < rocksNeededToFillBags.length; i++){
            if (rocksNeededToFillBags[i] <= additionalRocks){
                bagsFilled++;
                additionalRocks -= rocksNeededToFillBags[i];
            }
            else
                break;
        }
        return bagsFilled;
    }
    
    
    
    public int maximumBags_Greedy(int[] capacity, int[] rocks, int additionalRocks) {
        Bag[] bags = new Bag[capacity.length];
        for (int i = 0; i < bags.length; i++)
            bags[i] = new Bag(capacity[i], rocks[i]);
        
        Arrays.sort(bags, new BagComparator());
        int filledBags = 0;
        
        for (int i = 0; i < bags.length; i++){
            Bag bag = bags[i];
            if (bag.capacity - bag.rock <= additionalRocks){
                filledBags++;
                additionalRocks -= bag.capacity - bag.rock;
            }
            else break;
        }
        return filledBags;
    }
    
    class Bag{
        int capacity, rock;
        public Bag(int capacity, int rock){
            this.capacity = capacity;
            this.rock = rock;
        }
    }
    
    class BagComparator implements Comparator<Bag>{
        @Override
        public int compare(Bag a, Bag b){
            return (a.capacity - a.rock) - (b.capacity - b.rock);
        }
    }
}