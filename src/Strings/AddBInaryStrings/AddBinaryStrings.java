package Strings.AddBInaryStrings;

public class AddBinaryStrings {

    public long power(long a, long n){
        if (n==0)
            return 1;
        long powerHalf = power(a,n/2);
        if (n%2 == 0)
            return powerHalf * powerHalf;
        else
            return a * powerHalf * powerHalf;
    }


    public String addBinary(String A, String B) {
        long a_length = A.length();
        long b_length = B.length();
        long a=0 ,b=0;

        for (int i = 0; i < a_length; i++) {
            a += power(2,a_length-i-1) * Integer.parseInt(A.substring(i,i+1));
        }
        for (int i = 0; i < b_length; i++) {
            b += power(2,b_length-i-1) * Integer.parseInt(B.substring(i,i+1));
        }
//
//        System.out.prlongln(a);
//        System.out.prlongln(b);

        long sum = a+b;
        String binarySumReversed = "";
        long remainder;
        while (sum>0){
            remainder = sum%2;
            binarySumReversed += remainder;
            sum = sum/2;
        }

        String binarySum = "";
        for (int i = binarySumReversed.length()-1; i >=0 ; i--) {
            binarySum += binarySumReversed.charAt(i);
        }
        return binarySum;
    }

    public static void main(String[] args) {

        System.out.println(new AddBinaryStrings().addBinary("100","11"));

    }
}
