import java.util.Arrays;

public class UniquePath {
    public static int uniquePaths(int m, int n) {
        int[][] dp=new int[m+1][n+1];
        for(int[] row:dp) {
            Arrays.fill(row, -1);
        }
        return find(m-1,n-1,dp);
    }

    private static int find(int i,int j,int[][] dp){
        if(i==0 || j==0) return 1;
        if(i<0 || j<0) return 0;

        if(dp[i][j]!=-1) return dp[i][j];

        int up=find(i,j-1,dp);
        int left=find(i-1,j,dp);

        return dp[i][j]=up+left;
    }
    private static int find2(int m,int n){
        int[][] dp=new int[m+1][n+1];
        for(int[] row:dp) {
            Arrays.fill(row, -1);
        }

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0 && j==0) dp[i][j]=1;
                else {
                    int up=0,left=0;
                    if(i>0) up = dp[i - 1][j];
                    if(j>0) left = dp[i][j - 1];

                    dp[i][j] = up + left;
                }
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(3,7));
        System.out.println(find2(3,7));
    }
}
