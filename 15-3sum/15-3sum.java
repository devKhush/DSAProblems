class Solution {
    public List<List<Integer>> threeSum(int[] arr) {
        Arrays.sort(arr);
        int sum = 0;

        int n = arr.length;
        List<List<Integer>> allTriplets = new ArrayList<>();

        for (int i = 0; i <= n-3; i++){
            if (i > 0 && arr[i] == arr[i-1])        // skip duplicates
                continue;
            if (arr[i] + arr[i+1] + arr[i+2] > sum)
                break;
            if (arr[i] + arr[n-2] + arr[n-1] < sum)
                continue;

            // Here the problem 'Two Sum : Input Array is Sorted starts'
            int low = i + 1, high = n-1;

            while (low < high){
                if (arr[i] + arr[low] + arr[high] == sum){
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(arr[i]);
                    list.add(arr[low]);
                    list.add(arr[high]);
                    allTriplets.add(list);
                    low++;
                    high--;

                    while (low < high  && arr[low] == arr[low - 1])
                        low++;
                    while (high > low  &&  arr[high] == arr[high + 1])
                        high--;
                }
                else if (arr[i] + arr[low] + arr[high] > sum)
                    high--;
                else if (arr[i] + arr[low] + arr[high] < sum)
                    low++;
            }
        }
        return allTriplets;
    }
}