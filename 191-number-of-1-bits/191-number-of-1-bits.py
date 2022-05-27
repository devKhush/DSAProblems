class Solution(object):
    
    def hammingWeight(self, n):
        """
        :type n: int
        :rtype: int
        """
        binStr = ''
        num = n
        
        while num > 0:
            rem = num % 2
            binStr += str(rem)
            num /= 2
        
        return binStr.count('1')
        