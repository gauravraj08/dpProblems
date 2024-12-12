package strings;

public class leetcode10RegularExpression {
    public static boolean isMatch(String s, String p) {

        int n=s.length();
        int m=p.length();

        Boolean[][] dp=new Boolean[n+1][m+1];
        return find(0,0,n,m,s,p,dp);
    }
    private static boolean find(int i,int j,int n,int m,String s,String p,Boolean[][] dp){
        if(j==m){
            dp[i][j] = (i==n);
            return dp[i][j];
        }

        if(dp[i][j]!=null) return dp[i][j];

        boolean currMatch = (i<n && (s.charAt(i)==p.charAt(j) || p.charAt(j)=='.'));

        if(j+1 < m && p.charAt(j+1)=='*'){
            boolean notTake=find(i,j+2,n,m,s,p,dp);
            boolean take=currMatch && find(i+1,j,n,m,s,p,dp);
            dp[i][j]=notTake||take;
        }
        else{
            dp[i][j] = currMatch && find(i+1,j+1,n,m,s,p,dp);
        }

        return dp[i][j];
    }
    public static void main(String[] args) {
        System.out.println(isMatch("aa","a*"));
    }
}
