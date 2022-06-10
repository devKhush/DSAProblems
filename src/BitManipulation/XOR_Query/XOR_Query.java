package BitManipulation.XOR_Query;
import java.util.ArrayList;

// https://www.codingninjas.com/codestudio/problems/xor-query_893314?topList=top-array-coding-interview-questions&leftPanelTab=0

public class XOR_Query {

    // ***************************** Simple Intuitive Solution **************************************
    // Brute Force
    // TC -> O(n * q)   where  n = queries length   and     q = output XOR list length
    // SC -> O(1)       Ignoring output size
    public static ArrayList<Integer> xorQuery_BruteForce(ArrayList<ArrayList<Integer>> queries) {

        ArrayList<Integer> XOR_list = new ArrayList<>();

        for (ArrayList<Integer> query : queries){
            int queryType = query.get(0);

            // Adding element into the end of list as mentioned in question
            if (queryType == 1)
                XOR_list.add(query.get(1));

            // Updating all elements with the xor value as mentioned in question
            else if (queryType == 2)
                 for (int i = 0; i < XOR_list.size(); i++){
                     int xor = query.get(1);
                     XOR_list.set(i,  XOR_list.get(i) ^ xor);
                 }
        }
        return XOR_list;
    }



    // ***************************** Optimized Solution **************************************
    // We can reduce time complexity by using Bit manipulation, by using property of XOR
    // We will take XOR of all element as the end
    // TC -> O(n + q)   where  n = queries length   and     q = output XOR list length
    // SC -> O(1)       Ignoring output size

    public static ArrayList<Integer> xorQuery(ArrayList<ArrayList<Integer>> queries) {

        ArrayList<Integer> XOR_list = new ArrayList<>();

        // We declare a XOR variable for taking XOR of all values at the end
        int xor = 0;

        for (ArrayList<Integer> query : queries){
            int queryType = query.get(0);

            // We can be asked to add elements into list in the middle (before & after taking xor of list items)
            // Here we add the element to be added with the current XOR value, this is done to avoid taking
            // XOR of elements added later with the XOR value from start (which is done at the end of all queries)
            // This will work because:
            // Suppose initially XOR hold value "a ^ b" to xor all list items at the end of all queries
            // Now suppose we add value "x" at the end
            // After that suppose next queries were to xor all elements with 'c' and 'd' one after another
            // In this final result we want "x ^ c ^ d", but we will be getting "a ^ b ^ x ^ c ^ d" if we directly
            // take XOR with the XOR variable (because it is collecting elements to xor with from start)
            // So we insert "a ^ b ^ x" into the list
            // Such that after taking XOR of all list items as the end, it becomes "x ^ c ^ d" only
            // Because:     "a ^ b ^ x ^ a ^ b ^ c ^ d"    =     "x ^ c ^ d"

            if (queryType == 1)
                XOR_list.add(query.get(1) ^ xor);

            // In previous case we were taking XOR of list items each and every time we get a value
            // to XOR with, We can do better.
            // Instead, we can take XOR of all values in the entire queries ArrayList that asks us to
            // XOR the list items with given values
            // This will work bcoz of Associativity property of XOR ==> (((a^b) ^ c) ^ d)  =  a ^ (b ^ c ^ d)
            else if (queryType == 2)
                xor = xor ^ query.get(1);
        }

        // Taking XOR of list items at the end to reduce time complexity
        for (int i = 0; i < XOR_list.size(); i++)
            XOR_list.set(i,  XOR_list.get(i) ^ xor);

        return XOR_list;
    }
}
