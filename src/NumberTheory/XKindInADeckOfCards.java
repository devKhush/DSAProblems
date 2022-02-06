package NumberTheory;

// https://leetcode.com/problems/x-of-a-kind-in-a-deck-of-cards/

import java.util.HashMap;

public class XKindInADeckOfCards {

    public int GCD(int a, int b){
        if (b==0)
            return a;
        else
            return GCD(b, a%b);
    }

    public boolean hasGroupsSizeX(int[] deck) {
        HashMap<Integer, Integer> cardsCount = new HashMap<>();
        for (int i : deck) {
            int count = cardsCount.getOrDefault(i, 0);
            cardsCount.put(i, count+1);
        }

        int X = 0;
        for (Integer card: cardsCount.keySet()) {
            int count = cardsCount.get(card);
            X = GCD(X, count);
        }
        return X>=2;
    }

    public static void main(String[] args) {
        int[] deck = {1,1,1,1,1,2,2,2};

        XKindInADeckOfCards solution = new XKindInADeckOfCards();
        System.out.println(solution.hasGroupsSizeX(deck));
    }
}
