package subsequence;

import java.util.Arrays;

public class subsetSumTarget {
    static Boolean isSubsetSum(int[] arr, int target) {
        // code here
        int n= arr.length;
        int[][] dp=new int[n][target+1];
        for(int[] row:dp) {
            Arrays.fill(row, -1);
        }
        return find(n-1,arr,target,dp);
    }

    private static boolean find(int i,int[] arr,int target,int[][] dp){
        if(target==0) return true;
        if(i==0) return arr[0]==target;
        if(dp[i][target]!=-1) return dp[i][target]==0?false:true;

        boolean notTake=find(i-1,arr,target,dp);
        boolean take =false;
        if(target>=arr[i]) take=find(i-1,arr,target-arr[i],dp);

        dp[i][target] = notTake || take?1:0;
        return notTake || take;

    }

    private static boolean find2(int[] arr,int k){
        int n= arr.length;
        boolean[][] dp=new boolean[n][k+1];
        for(int i=0;i<n;i++){
            dp[i][0]=true;
        }

         if(arr[0]<=k){
            dp[0][arr[0]]=true;
        }

        for(int i=1;i<n;i++){
            for(int target=1;target<=k;target++){
                boolean notTake=dp[i-1][target];
                boolean take=false;
                if(arr[i]<=target) take = dp[i-1][target-arr[i]];

                dp[i][target]=take||notTake;
            }
        }
        return dp[n-1][k];
    }

    public static void main(String[] args) {
        int[] arr={3,34,4,12,5,2};
        System.out.println(isSubsetSum(arr,9));
        System.out.println(find2(arr,9));
    }
}
