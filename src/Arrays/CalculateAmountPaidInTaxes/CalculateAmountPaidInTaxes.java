package Arrays.CalculateAmountPaidInTaxes;

class CalculateAmountPaidInTaxes {

    // Simple solution
    public double calculateTax(int[][] brackets, int income) {
        double tax = 0.0;

        int incomeBound = brackets[0][0];
        int nextIncome = Math.min(incomeBound, income);
        tax += nextIncome * brackets[0][1] /100.00;
        income -= nextIncome;

        for (int i = 1; i < brackets.length; i++){
            incomeBound = brackets[i][0] - brackets[i-1][0];
            nextIncome = Math.min(incomeBound, income);

            tax += nextIncome * brackets[i][1] /100.0;
            income -= nextIncome;
        }
        return tax;
    }
}