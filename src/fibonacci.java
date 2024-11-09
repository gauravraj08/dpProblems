import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class fibonacci {
    //Method-1 Using Normal Recursion
    private static int fibo(int n){
        if(n<=1) return n;
        return fibo(n-1)+fibo(n-2);
    }

    //Method-2 Using Memoization
    private static int fibo2(int n,int[] dp){
        if(n<=1) return n;
        if(dp[n]!=-1) return dp[n];
        return dp[n]=fibo2(n-1,dp)+fibo2(n-2,dp);
    }

    //Method-3 Using Tabulation
    private static int fibo3(int n){
        int prev2=0;
        int prev1=1;
        for(int i=2;i<=n;i++){
            int curr=prev2+prev1;
            prev2=prev1;
            prev1=curr;
        }
        return prev1;
    }
    public static void main(String[] args) {
        int n=5;
        System.out.println(fibo(n));
        int[] dp=new int[n+1];
        Arrays.fill(dp,-1);
//        ArrayList<Integer> dp=new ArrayList<>(Collections.nCopies(n + 1, -1));
        System.out.println(fibo2(n,dp));
        System.out.println(fibo3(n));
    }
}
