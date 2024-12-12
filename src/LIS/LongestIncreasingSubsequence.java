package LIS;

import java.util.Arrays;

public class LongestIncreasingSubsequence {
    public static int lengthOfLIS(int[] nums) {
        int n=nums.length;
        int[][] dp = new int[n][n + 1];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return find(0,-1,n,nums,dp);
    }
    private static int find(int i,int prev,int n,int[] nums,int[][] dp) {
        if(i==n) return 0;

        if(dp[i][prev+1]!=-1) return dp[i][prev+1];

        int notTake=find(i+1,prev,n,nums,dp);
        int take=0;
        if(prev==-1 || nums[i]>nums[prev]) take=1+find(i+1,i,n,nums,dp);

        dp[i][prev+1] = Math.max(notTake,take);
        return dp[i][prev+1];
    }

    public static void main(String[] args) {
        int[] nums={10,9,2,5,3,7,101,18};
        System.out.println(lengthOfLIS(nums));
    }
}
