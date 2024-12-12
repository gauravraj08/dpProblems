package stock;

import java.util.Arrays;

public class BuySell2 {
    public static int maxProfit(int[] prices) {
        int n=prices.length;
        int[][] dp = new int[n][2];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return find(0,0,prices,dp);
    }
    private static int find(int i,int buy,int[] prices,int[][] dp){
        if(i==prices.length) return 0;
        if(dp[i][buy]!=-1) return dp[i][buy];

        int profit=0;
        if(buy==0){

            int notTake = find(i+1,0,prices,dp);
            int take = -prices[i]+find(i+1,1,prices,dp);

            profit = Math.max(notTake,take);
        }
        if(buy==1){

            int notSell = find(i+1,1,prices,dp);
            int sell = prices[i] + find(i+1,0,prices,dp);

            profit = Math.max(notSell,sell);
        }

        dp[i][buy]=profit;

        return profit;
    }
    private static int find2(int[] prices){
        int n=prices.length;
        int[][] dp = new int[n+1][2];

        dp[n][0]=dp[n][1]=0;
        int profit=0;
        for(int i=n-1;i>=0;i--){
            for(int buy=0;buy<2;buy++){

                if(buy==0){

                    int notTake = dp[i+1][0];
                    int take = -prices[i]+dp[i+1][1];
                    profit = Math.max(notTake,take);
                }
                if(buy==1){

                    int notSell = dp[i+1][1];
                    int sell = prices[i] + dp[i+1][0];

                    profit = Math.max(notSell,sell);
                }
                dp[i][buy]=profit;
            }
        }
        return dp[0][0];
    }
    public static void main(String[] args) {
        int[] arr={7,1,5,3,6,4};
        System.out.println(maxProfit(arr));
        System.out.println(find2(arr));
    }
}
