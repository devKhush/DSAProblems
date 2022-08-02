package BitManipulation.BitManipulationPreRequisite;

// https://youtu.be/5iyuU4hQFrw

public class BitManipulation {

    // Time Complexity: O(1)
    public boolean checkBitIsSet(int number, int i){

        // Check if the bit at 'ith' position  (in the 32 Bit representation of integer number)
        // is set to '1' or not
        // It can also be used to get bit at 'ith' position of number
        int bitIsSet = (number >> i) & 1;

        // return ((number >> i) & 1) == 1;
        return bitIsSet == 1;
    }


    // Time Complexity: O(1)
    public int setBitAtIthPosition(int number, int i){

        // Set the bit at 'ith' position (in the 32 Bit representation of integer number) to '1'
        return number | (1 << i);
    }
}
