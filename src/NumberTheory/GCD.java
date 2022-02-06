package NumberTheory;


public class GCD {      // Or HCF
    public int GCD(int a, int b){
        if (b==0)
            return a;
        else
            return GCD(b, a%b);
    }

    public int GCD_WhileLoop(int a, int b){
        if (a==0)
            return b;
        if (b==0)
            return a;
        while (a!=b){
            if (a>b)
                a = a-b;
            else
                b = b-a;
        }
        return a;
    }

    public static void main(String[] args) {
        GCD solution = new GCD();
        System.out.println(solution.GCD(0,1));
        System.out.println(solution.GCD(1,0));
        System.out.println(solution.GCD(1,1));
        System.out.println(solution.GCD(1,2));
        System.out.println(solution.GCD(4,2));

        System.out.println();
        System.out.println(solution.GCD_WhileLoop(0,1));
        System.out.println(solution.GCD_WhileLoop(1,0));
        System.out.println(solution.GCD_WhileLoop(1,1));
        System.out.println(solution.GCD_WhileLoop(1,2));
        System.out.println(solution.GCD_WhileLoop(4,2));

    }


}
