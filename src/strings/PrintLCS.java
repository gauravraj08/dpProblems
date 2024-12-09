package strings;

import java.util.*;

public class PrintLCS {
    public static List<String> all_longest_common_subsequences(String s, String t) {
        // Code here
        int n1=s.length();
        int n2=t.length();

        int[][] dp=new int[n1+1][n2+1];

        for(int i=0;i<=n1;i++) dp[i][0]=0;
        for(int j=0;j<=n2;j++) dp[0][j]=0;

        for(int i=1;i<=n1;i++){
            for(int j=1;j<=n2;j++){
                if(s.charAt(i-1)== t.charAt(j-1)) dp[i][j] = 1+dp[i-1][j-1];
                else dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        Map<String, Set<String>> memo=new HashMap<>();
        Set<String> lcsSet=findLCS(s,t,n1,n2,dp,memo);

        List<String> res=new ArrayList<>(lcsSet);
        Collections.sort(res);

        return res;
    }

    private static Set<String> findLCS(String s,String t,int i,int j,int[][] dp,Map<String,Set<String>> memo){
        if(i==0 || j==0){
            return new HashSet<>(Collections.singletonList(""));
        }
        String key=i+","+j;
        if(memo.containsKey(key)){
            return memo.get(key);
        }
        Set<String> lcsSet=new HashSet<>();

        if(s.charAt(i-1)==t.charAt(j-1)){
            Set<String> prevLCS=findLCS(s,t,i-1,j-1,dp,memo);
            for(String lcs:prevLCS){
                lcsSet.add(lcs+s.charAt(i-1));
            }
        }
        else{
            if(dp[i-1][j]==dp[i][j]){
                lcsSet.addAll(findLCS(s,t,i-1,j,dp,memo));
            }
            if(dp[i][j-1]==dp[i][j]){
                lcsSet.addAll(findLCS(s,t,i,j-1,dp,memo));
            }
        }
        memo.put(key,lcsSet);
        return lcsSet;
    }
    public static void main(String[] args) {
        System.out.println(all_longest_common_subsequences("abcde","ace"));
//        System.out.println(find2("abcde","ace"));
    }
}
