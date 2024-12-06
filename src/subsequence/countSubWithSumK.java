package subsequence;

import java.util.Arrays;

public class countSubWithSumK {

    static int mod=(int)Math.pow(10,9)+7;
    private static int perfectSum(int[] nums, int target) {
        // code here
        int n= nums.length;
        int count=0;

        for(int i:nums) if(i==0) count++;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length / 2; i++) {
            int temp = nums[i];
            nums[i] = nums[nums.length - 1 - i];
            nums[nums.length - 1 - i] = temp;
        }

        int[][] dp=new int[n-count][target+1];
        for(int[] row:dp) Arrays.fill(row,-1);

        int ind=n-count-1;
        if(ind<0) return (int) Math.pow(2,count);

        int ans=find(ind,target,nums,dp);
        return count!=0?(int)Math.pow(2,count)*ans:ans;
    }

    public static int perfectSum2(int[] nums, int target) {
        int n = nums.length;                     // Size of the array
        int[][] dp = new int[n + 1][target + 1]; // DP table to store results

        // Base case: There is 1 way to make sum 0, by choosing no elements
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int sum = 0; sum <= target; sum++) {
                // Option 1: Exclude the current element
                dp[i][sum] = dp[i - 1][sum];

                // Option 2: Include the current element if it's not greater than the
                // sum
                if (nums[i - 1] <= sum) {
                    dp[i][sum] += dp[i - 1][sum - nums[i - 1]];
                }
            }
        }

        // Return the result from the DP table
        return dp[n][target];
    }

    private static int find(int i,int target,int[] arr,int[][] dp){

        if(i<0) return 0;

        if(dp[i][target]!=-1) return dp[i][target];

        if (target == 0)
            return 1;

        if (i == 0)
            return arr[0] == target ? 1 : 0;

        int notTake=find(i-1,target,arr,dp);
        int take=0;
        if(target>=arr[i]){
            take = find(i-1,target-arr[i],arr,dp);

        }

        return dp[i][target] = take+notTake;
    }
    public static void main(String[] args) {
        int[] arr={0,10,20,5,0,5};
        System.out.println(perfectSum2(arr,10));
    }
}
