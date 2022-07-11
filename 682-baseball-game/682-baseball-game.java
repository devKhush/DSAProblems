class Solution {
    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();

        for (String operation : ops) {
            switch (operation) {
                case "+" -> {
                    int A = stack.pop();
                    int B = stack.pop();
                    int newRecord = A + B;
                    stack.push(B);
                    stack.push(A);
                    stack.push(newRecord);
                }
                case "D" ->
                        stack.push(2 * stack.peek());
                case "C" ->
                        stack.pop();
                default ->
                        stack.push(Integer.parseInt(operation));
            }
        }
        int sumOfRecords = 0;
        while (!stack.isEmpty())
            sumOfRecords += stack.pop();
        return sumOfRecords;
    }
}