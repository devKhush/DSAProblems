import java.util.Random;

public class PrintUsingRandom {
    /*
    Function to print n times a string using Random function without for loop
     */
    public static int function(int n){
        if(n<=0)
            return 0;
        else{
            int i  = new Random().nextInt(n);
            System.out.println("*");
            return function(i)+function(n-i-1);
        }
    }
    public static void main(String[] args) {
        function(6);
    }
}