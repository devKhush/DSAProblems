class StockSpanner {
    private Stack<StockPrice> stack;
    private int days;
    
    public StockSpanner() {
        this.stack = new Stack<>();    
        this.days = 0;
    }
    
    public int next(int price) {
        StockPrice todayStock = new StockPrice(price, ++days);
        
        while (!stack.isEmpty()  &&  stack.peek().price <= todayStock.price)
            stack.pop();
        
        int spanOfTodayStockPrices = !stack.isEmpty() ? todayStock.day - stack.peek().day : todayStock.day;
        
        stack.push(todayStock);
        
        return spanOfTodayStockPrices;
    }
    
    class StockPrice{
        int price, day;
        public StockPrice(int price, int day){
            this.price = price;
            this.day = day;
        }
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */