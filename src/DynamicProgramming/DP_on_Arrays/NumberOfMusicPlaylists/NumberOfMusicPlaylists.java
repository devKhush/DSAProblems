package DynamicProgramming.DP_on_Arrays.NumberOfMusicPlaylists;

// https://youtu.be/xRqkSe5pPSw
// https://leetcode.com/problems/number-of-music-playlists/description/

public class NumberOfMusicPlaylists {
    /*********************************** Memoization DP ****************************************
     * Time Complexity: O(n * goal)
     * Space Complexity: O(n * goal)
     */
    int n, goal, k, MOD = (int)(1e9 + 7);
    Integer[][] dp;
    public int numMusicPlaylists(int n, int goal, int k) {
        this.n = n;
        this.k = k;
        this.goal = goal;
        this.dp = new Integer[n + 1][goal];
        return f(0, 0);
    }

    private int f(int usedSong, int currLoc){
        if (currLoc == goal)
            return usedSong == n ? 1 : 0;
        if (dp[usedSong][currLoc] != null)
            return dp[usedSong][currLoc];

        long useNewSong = usedSong < n ? f(usedSong + 1, currLoc + 1) * (long)(n - usedSong) : 0;
        long useOldSong = f(usedSong, currLoc + 1) * (long)Math.max(0, usedSong - k);
        return dp[usedSong][currLoc] = (int)((useNewSong % MOD + useOldSong % MOD) % MOD);
    }
}
