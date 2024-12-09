package subsequence;

import java.lang.reflect.Array;
import java.util.Arrays;

public class RodCutting {
    private static int cutRod(int[] price) {
        // code here
        int n=price.length;
        int[][] dp=new int[n][n+1];
        for(int[] row:dp) Arrays.fill(row,-1);
        return find(n-1,n,price,dp);
    }

    private static int find(int i,int n,int[] price,int[][] dp){
        if(i==0){
            //since this is last guy it will require n length
            return n*price[0];
        }
        if(dp[i][n]!=-1) return dp[i][n];
        int notTake=find(i-1,n,price,dp);
        int take=0;
        int rodNo=i+1;
        if(n>=rodNo) take=price[i]+find(i,n-rodNo,price,dp);

        return dp[i][n] = Math.max(take,notTake);
    }
    private static int find2(int[] prices){
        int n=prices.length;
        int[][] dp=new int[n][n+1];

        for(int i=0;i<=n;i++) dp[0][i]=i*prices[0];

        for(int i=1;i<n;i++){
            for(int j=0;j<=n;j++){
                int notTake=dp[i-1][j];
                int take=0;
                int rodLength=i+1;
                if(j>=rodLength) take=prices[i]+dp[i][j-rodLength];

                dp[i][j]=Math.max(notTake,take);
            }
        }
        return dp[n-1][n];
    }
    public static void main(String[] args) {
        int[] prices={1, 5, 8, 9, 10, 17, 17, 20};
        System.out.println(cutRod(prices));
        System.out.println(find2(prices));
    }
}
