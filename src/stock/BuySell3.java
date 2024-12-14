package stock;

import java.util.Arrays;

public class BuySell3 {
    public static int maxProfit(int[] prices) {
        int n=prices.length;
        int[][][] dp = new int[n][2][3];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return find(0,0,2,prices,dp);
    }
    private static int find(int i,int buy,int cap,int[] prices,int[][][] dp){
        if(i==prices.length) return 0;
        if(cap==0) return 0;
        if(dp[i][buy][cap]!=-1) return dp[i][buy][cap];

        int profit=0;
        if(buy==0){

            int notTake = find(i+1,0,cap,prices,dp);
            int take = -prices[i]+find(i+1,1,cap,prices,dp);

            profit = Math.max(notTake,take);
        }
        if(buy==1){

            int notSell = find(i+1,1,cap,prices,dp);
            int sell = prices[i] + find(i+1,0,cap-1,prices,dp);

            profit = Math.max(notSell,sell);
        }

        dp[i][buy][cap] = profit;

        return profit;
    }
    private static int find2(int[] prices){
        int n=prices.length;
        int[][][] dp = new int[n+1][2][3];

//        dp[n][0]=dp[n][1]=0;
        int profit=0;
        for(int i=n-1;i>=0;i--){
            for(int buy=0;buy<2;buy++) {
                for (int cap = 1; cap <= 2; cap++) {
                    if (buy == 0) {

                        int notTake = dp[i + 1][0][cap];
                        int take = -prices[i] + dp[i + 1][1][cap];
                        profit = Math.max(notTake, take);
                    }
                    if (buy == 1) {

                        int notSell = dp[i + 1][1][cap];
                        int sell = prices[i] + dp[i + 1][0][cap-1];

                        profit = Math.max(notSell, sell);
                    }
                    dp[i][buy][cap] = profit;
                }
            }
        }
        return dp[0][0][2];
    }
    public static void main(String[] args) {
        int[] arr={3,3,5,0,0,3,1,4};
        System.out.println(maxProfit(arr));
        System.out.println(find2(arr));
    }
}
