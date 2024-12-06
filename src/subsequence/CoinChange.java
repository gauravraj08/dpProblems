package subsequence;

import java.util.Arrays;

public class CoinChange {
    private static int coinChange(int[] coins, int amount) {
        int n=coins.length;
        int[][] dp=new int[n][amount+1];
        for(int[] row:dp) Arrays.fill(row,-1);
        int ans=find(n-1,amount,coins,dp);
        if(ans>=Math.pow(10,9)) return -1;
        return ans;
    }

    private static int find(int i,int target,int[] arr,int[][] dp){
        if(i==0) {
            if (target % arr[0] == 0) return target/arr[0];
            else return (int) Math.pow(10, 9);
        }

        if(dp[i][target]!=-1) return dp[i][target];

        int notTaken= find(i - 1, target, arr,dp);
        int taken=(int) Math.pow(10,9);
        if(target>=arr[i]) taken=1+find(i,target-arr[i],arr,dp);

        return dp[i][target] = Math.min(notTaken,taken);
    }

    private static int find2(int[] arr,int amount){
        int n=arr.length;
        int[][] dp=new int[n][amount+1];

        for(int target=0;target<=amount;target++){
            if(target%arr[0]==0) dp[0][target]=target/arr[0];
            else dp[0][target]=(int)Math.pow(10,9);
        }

        for(int i=1;i<n;i++){
            for(int target=0;target<=amount;target++){
                int notTake=dp[i-1][target];
                int take=(int)Math.pow(10,9);
                if(target>=arr[i]) take=1+dp[i][target-arr[i]];
                dp[i][target]=Math.min(notTake,take);
            }
        }
        if(dp[n-1][amount]>=Math.pow(10,9)) return -1;

        return dp[n-1][amount];
    }
    public static void main(String[] args) {
        int[] arr={1,2,5};
        System.out.println(coinChange(arr,11));
        System.out.println(find2(arr,11));
    }
}
