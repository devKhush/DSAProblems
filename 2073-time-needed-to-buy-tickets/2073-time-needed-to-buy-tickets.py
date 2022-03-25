class Solution:
    def timeRequiredToBuy(self, tickets: List[int], k: int) -> int:
        queue = []
        for i, val in enumerate(tickets):
            queue.append([i, val])
        
        time = 0
        while queue:
            index, ticket = queue.pop(0)
            ticket -= 1
            time += 1
            
            if ticket == 0:
                if k == index:
                    return time
            else:
                queue.append([index, ticket])
                
                
        