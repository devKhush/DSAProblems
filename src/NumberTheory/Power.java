package NumberTheory;

public class Power {
    public static int power(int a, int n){          // a^n
        if (n==0)
            return 1;
        int temp = power(a, n/2);
        if (n%2==0)
            return temp*temp;
        else
            return a*temp*temp;
    }

    public static double power(double a, int n){        // a^n
        if (n==0)
            return 1;
        double temp = power(a, n/2);

        if (n%2==0)
            return temp*temp;
        else {
            if (n>0)
                return temp * temp * a;
            else
                return temp * temp / a;
        }
    }


    public static void main(String[] args) {
        int a = 5, b = 4;
        System.out.println(power(a,b));

        double x = 2;
        int n = 3;
        System.out.println(power(x,n));

    }
}
