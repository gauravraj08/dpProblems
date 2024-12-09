package strings;

import java.util.Arrays;

public class LongestCommonSub {
    private static int longestCommonSubsequence(String text1, String text2) {
        int n1=text1.length();
        int n2=text2.length();

        int[][] dp=new int[n1][n2];
        for (int[] row:dp) Arrays.fill(row,-1);

        return find(n1-1,n2-1,text1,text2,dp);
    }
    private static int find(int ind1,int ind2,String s1,String s2,int[][] dp){
        if(ind1<0 || ind2<0) return 0;

        if(dp[ind1][ind2]!=-1) return dp[ind1][ind2];

        if(s1.charAt(ind1)== s2.charAt(ind2)) return 1+find(ind1-1,ind2-1,s1,s2,dp);

        return dp[ind1][ind2] = Math.max(find(ind1-1,ind2,s1,s2,dp),find(ind1,ind2-1,s1,s2,dp));
    }

    private static int find2(String s1,String s2){
        int n1=s1.length();
        int n2=s2.length();

        int[][] dp=new int[n1+1][n2+1];

        for(int i=0;i<=n1;i++) dp[i][0]=0;
        for(int j=0;j<=n2;j++) dp[0][j]=0;

        for(int i=1;i<=n1;i++){
            for(int j=1;j<=n2;j++){
                if(s1.charAt(i-1)== s2.charAt(j-1)) dp[i][j] = 1+dp[i-1][j-1];
                else dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        return dp[n1][n2];
    }
    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abcde","ace"));
        System.out.println(find2("abcde","ace"));
    }
}
