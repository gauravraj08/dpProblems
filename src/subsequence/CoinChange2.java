package subsequence;

import java.util.Arrays;

public class CoinChange2 {
    private static int coinChange(int[] coins, int amount) {
        int n=coins.length;
        int[][] dp=new int[n][amount+1];
        for(int[] row:dp) Arrays.fill(row,-1);
        return find(n-1,amount,coins,dp);
    }

    private static int find(int i,int target,int[] arr,int[][] dp){
        if(i==0) {
            if (target % arr[0] == 0) return 1;
            else return 0;
        }

        if(dp[i][target]!=-1) return dp[i][target];

        int notTaken= find(i - 1, target, arr,dp);
        int taken=0;
        if(target>=arr[i]) taken=find(i,target-arr[i],arr,dp);

        return dp[i][target] = notTaken+taken;
    }

    private static int find2(int[] arr,int amount){
        int n=arr.length;
        int[][] dp=new int[n][amount+1];

        for(int target=0;target<=amount;target++){
            if(target%arr[0]==0) dp[0][target]=1;
            else dp[0][target]=0;
        }

        for(int i=1;i<n;i++){
            for(int target=0;target<=amount;target++){
                int notTake=dp[i-1][target];
                int take=0;
                if(target>=arr[i]) take=dp[i][target-arr[i]];
                dp[i][target]=notTake+take;
            }
        }
        return dp[n-1][amount];
    }
    public static void main(String[] args) {
        int[] arr={1,2,5};
        System.out.println(coinChange(arr,5));
        System.out.println(find2(arr,5));
    }
}
