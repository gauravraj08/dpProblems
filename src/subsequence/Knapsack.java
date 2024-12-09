package subsequence;

import java.util.Arrays;

public class Knapsack {
    static int knapSack(int capacity, int[] val, int[] wt) {
        // code here
        int n=val.length;
        int[][] dp=new int[n][capacity+1];
        for(int[] row:dp) Arrays.fill(row,-1);
        return find(n-1,capacity,val,wt,dp);
    }
    private static int find(int i,int cap,int[] val,int[] wt,int[][] dp){
        if(i==0) {
            if(wt[0]<=cap) return val[0];
            else return 0;
        }

        if(dp[i][cap]!=-1) return dp[i][cap];

        int notTake=find(i-1,cap,val,wt,dp);
        int take=0;
        if(cap>=wt[i]) take=val[i]+find(i-1,cap-wt[i],val,wt,dp);

        return dp[i][cap] = Math.max(notTake,take);
    }

    private static int find2(int cap,int[] val,int[] wt){
        int n=val.length;
        int[][] dp=new int[n][cap+1];
        for(int[] row:dp) Arrays.fill(row,-1);

        for(int i=wt[0];i<=cap;i++) dp[0][i]=val[0];

        for(int i=1;i<n;i++){
            for(int j=0;j<=cap;j++){
                int notTake=dp[i-1][j];
                int take=0;
                if(j>=wt[i]) take=val[i]+dp[i-1][j-wt[i]];

                dp[i][j]=Math.max(notTake,take);
            }
        }
        return dp[n-1][cap];
    }

    public static void main(String[] args) {
        int[] val={10, 40, 30, 50};
        int[] wt={5, 4, 6, 3};
        System.out.println(knapSack(5,val,wt));
        System.out.println(find2(5,val,wt));
    }
}
