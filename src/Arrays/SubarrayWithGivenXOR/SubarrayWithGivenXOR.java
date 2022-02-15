package Arrays.SubarrayWithGivenXOR;
// remaining

public class SubarrayWithGivenXOR {

    int solve(int[] arr, int xor_output) {
        int xor;
        int subArrayCount=0;
        int n = arr.length;

        for (int i=0; i<n; i++) {
            xor = arr[i];
            if (xor == xor_output)
                subArrayCount++;

            for (int j=i+1; j<n; j++){
                xor = xor ^ arr[j];
                if (xor == xor_output)
                    subArrayCount++;
            }
        }
        return subArrayCount;
    }

    public static void main(String[] args) {
        int[] arr = {5,6,7,8,9};
        int xor = 5;
        System.out.println(new SubarrayWithGivenXOR().solve(arr, xor));
    }
}
