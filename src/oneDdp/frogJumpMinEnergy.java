package oneDdp;

import java.util.Arrays;

public class frogJumpMinEnergy {

    //top down approach
    public int minimumEnergy(int arr[],int n){
        int[] dp=new int[n+1];
        Arrays.fill(dp,-1);
        return find(n-1,arr,dp);
    }
    private static int find(int idx,int[] arr,int[] dp){
        if(idx==0) return 0;

        if(dp[idx]!=-1) return dp[idx];

        int left=find(idx-1,arr,dp)+Math.abs(arr[idx]-arr[idx-1]);
        int right=Integer.MAX_VALUE;
        if(idx>1) right=find(idx-2,arr,dp)+Math.abs(arr[idx]-arr[idx-2]);

        return dp[idx]=Math.min(left,right);
    }

    //bottom up approach tabulation

    private static void findMin(int[] arr,int n){
        int[] dp=new int[n];
        dp[0]=0;
        for(int i=1;i<n;i++){
            int fs=dp[i-1]+Math.abs(arr[i]-arr[i-1]);
            int ss=Integer.MAX_VALUE;
            if(i>1) ss=dp[i-2]+Math.abs(arr[i]-arr[i-2]);

            dp[i]=Math.min(fs,ss);
        }
        System.out.println(dp[n-1]);
    }

    //space optmised tabulation approach

    private static void findMin2(int[] arr,int n){
        int prev=0;
        int prev2=0;
        for(int i=1;i<n;i++){
            int fs=prev+Math.abs(arr[i]-arr[i-1]);
            int ss=Integer.MAX_VALUE;
            if(i>1) ss=prev2+Math.abs(arr[i]-arr[i-2]);

            int curr=Math.min(fs,ss);
            prev2=prev;
            prev=curr;
        }
        System.out.println(prev);
    }

    public static void main(String[] args) {
        frogJumpMinEnergy fj=new frogJumpMinEnergy();
        int[] arr={10, 20, 30, 10};
        System.out.println(fj.minimumEnergy(arr,4));
        findMin(arr,4);
        findMin2(arr,4);
    }
}
