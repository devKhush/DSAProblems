#User function Template for python3
class Solution:
	def isPalindrome(self, s):
		# code here
		palin = 1
		
		for i in s:
		    if s == s[::-1]:
		        break
		    else:
		        palin = 0
		return palin


#{ 
 # Driver Code Starts
#Initial Template for Python 3

if __name__ == '__main__':
	T=int(input())
	for i in range(T):
		S = input()
		ob = Solution()
		answer = ob.isPalindrome(S)
		print(answer)

# } Driver Code Ends