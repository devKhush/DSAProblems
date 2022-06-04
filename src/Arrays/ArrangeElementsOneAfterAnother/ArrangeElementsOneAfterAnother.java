package Arrays.ArrangeElementsOneAfterAnother;

class ArrangeElementsOneAfterAnother {

    private void convertToWave(int n, int[] a) {

        for (int i=0; i<n; i+=2){
            if (i+1 < n){
                int temp = a[i];
                a[i] = a[i+1];
                a[i+1] = temp;
            }
        }

    }
}
        
