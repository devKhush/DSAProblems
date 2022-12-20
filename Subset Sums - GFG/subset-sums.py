#User function Template for python3
class Solution:
	def subsetSums(self, arr, N):
		subset = []
		self.solve(0, arr, subset, 0)
		sorted(subset)
		return subset
	
	def solve(self, i, arr, subset, sum):
	    if i == len(arr):
	        subset.append(sum)
	        return
	    
	    self.solve(i+1, arr, subset, sum+arr[i])
	    self.solve(i+1, arr, subset, sum)
        	    
	    


#{ 
 # Driver Code Starts
#Initial Template for Python 3

if __name__ == '__main__':
    T=int(input())
    for i in range(T):
        N = int(input())
        arr = [int(x) for x in input().split()]
        ob = Solution()
        ans = ob.subsetSums(arr, N)
        ans.sort()
        for x in ans:
            print(x,end=" ")
        print("")

# } Driver Code Ends