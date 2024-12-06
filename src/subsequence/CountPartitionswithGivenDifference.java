package subsequence;

import java.util.Arrays;

public class CountPartitionswithGivenDifference {
    private static int countPartitions(int[] arr, int d) {
        // code here
        int n=arr.length;
        int sum=0;

        for(int i:arr) sum+=i;
        int target = (sum-d)/2;

        if(sum-d<0 || (sum-d)%2==1) return 0;

        int[][] dp=new int[n][target+1];
        for(int[] row:dp) Arrays.fill(row,-1);

        return find(n-1,target,arr,dp);
    }

    private static int find(int i,int target,int[] arr,int[][] dp){

        if(i==0){
            if(target==0 && arr[0]==0) return 2;
            if(target==0 || target==arr[0]) return 1;
            return 0;
        }

        if(dp[i][target]!=-1) return dp[i][target];
        int notTaken=find(i-1,target,arr,dp);
        int taken=0;
        if(target>=arr[i]) taken=find(i-1,target-arr[i],arr,dp);

        return dp[i][target] = taken+notTaken;
    }

    private static int find2(int[] arr,int d){
        int n=arr.length;
        int sum=0;

        for(int i:arr) sum+=i;
        int target = (sum-d)/2;

        if(sum-d<0 || (sum-d)%2==1) return 0;

        int[][] dp=new int[n][target+1];

        if(arr[0]==0) dp[0][0]=2;
        else dp[0][0]=1;

        if(arr[0]!=0 && arr[0]<=target) dp[0][arr[0]]=1;

        for(int i=1;i<n;i++){
            for(int k=0;k<=target;k++){
                int notTaken=dp[i-1][k];
                int taken=0;
                if(k>=arr[i]) taken=dp[i-1][k-arr[i]];

                dp[i][k]=notTaken+taken;
            }
        }
        return dp[n-1][target];
    }
    public static void main(String[] args) {
        int[] arr={1,1,1,1};
        System.out.println(countPartitions(arr,0));
        System.out.println(find2(arr,0));
    }
}
