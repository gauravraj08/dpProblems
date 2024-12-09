package strings;

public class DeleteOperationforTwoStrings {
    public static int minDistance(String word1, String word2) {
        int n1=word1.length();
        int n2=word2.length();

        int len=lcs(word1,word2);

        int delete=n1-len;
        int insert=n2-len;

        return delete+insert;
    }
    private static int lcs(String s1,String s2){
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
        System.out.println(minDistance("sea","eat"));
    }
}
