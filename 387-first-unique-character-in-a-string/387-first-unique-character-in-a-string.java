class Solution {
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> count = new HashMap<>();
        Deque<Character> queue = new LinkedList<>();
        
        for (int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            count.put(ch, count.getOrDefault(ch, 0)+1);
            
            if (count.get(ch) == 1)
                queue.add(ch);
            
            while (!queue.isEmpty()  &&  count.get(queue.peek()) != 1)
                queue.remove();
            
            // if (!queue.isEmpty())
            //     System.out.print(queue.peek() + " ");
            // else
            //     System.out.print(-1 + " ");
                
        }
            
        return !queue.isEmpty() ? s.indexOf(queue.peek()) : -1;
    
    }
}