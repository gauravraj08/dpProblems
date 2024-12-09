package subsequence;

import java.util.Arrays;

public class UnboundedKnapsack {
    static int knapSack(int capacity, int[] val, int[] wt) {
        // code here
        int n=val.length;
        int[][] dp=new int[n][capacity+1];
        for(int[] row:dp) Arrays.fill(row,-1);
        return find(n-1,capacity,val,wt,dp);
    }
    private static int find(int i,int cap,int[] val,int[] wt,int[][] dp){
        if(i==0) {
            return (cap/wt[0])*val[0];
        }

        if(dp[i][cap]!=-1) return dp[i][cap];

        int notTake=find(i-1,cap,val,wt,dp);
        int take=0;
        if(cap>=wt[i]) take=val[i]+find(i,cap-wt[i],val,wt,dp);

        return dp[i][cap] = Math.max(notTake,take);
    }

    private static int find2(int cap,int[] val,int[] wt){
        int n=val.length;
        int[][] dp=new int[n][cap+1];

        for(int i=0;i<=cap;i++) dp[0][i]=(i/wt[0])*val[0];

        for(int i=1;i<n;i++){
            for(int j=0;j<=cap;j++){
                int notTake=dp[i-1][j];
                int take=0;
                if(j>=wt[i]) take=val[i]+dp[i][j-wt[i]];

                dp[i][j]=Math.max(notTake,take);
            }
        }
        return dp[n-1][cap];
    }

    public static void main(String[] args) {
        int[] val={6, 1, 7, 7};
        int[] wt={1, 3, 4, 5};
        System.out.println(knapSack(8,val,wt));
        System.out.println(find2(8,val,wt));
    }
}
