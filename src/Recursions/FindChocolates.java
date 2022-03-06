package Recursions;

// https://www.geeksforgeeks.org/program-chocolate-wrapper-puzzle/

public class FindChocolates {

    public int findMoreChocolates(int chocolates, int wraps){
        if (chocolates<wraps)
            return 0;
        int moreChocolates = chocolates/wraps;

        return moreChocolates + findMoreChocolates(moreChocolates + (chocolates % wraps), wraps);
    }
    public int findChocolate(int money, int price, int wraps){
        int chocolates = money/price;
        return chocolates + findMoreChocolates(chocolates, wraps);
    }


    // O(1) Time && O(1) Space
    public int findChocolateEfficient(int money, int price, int wraps){
        int chocolate = money/price;
        return chocolate + (chocolate-1)/(wraps-1);
    }

    public static void main(String[] args) {
        int money = 30 ;
        int price = 4;
        int wraps = 3 ;

        System.out.println(new FindChocolates().findChocolateEfficient(money,price,wraps));
        System.out.println(new FindChocolates().findChocolate(money,price,wraps));
    }
}
