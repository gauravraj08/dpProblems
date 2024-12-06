package oneDdp;

public class frogJumpKstep {

    //recursive memo method
    public static int minimizeCost(int k, int arr[]) {
        // code here
        int n= arr.length;
        int[] dp=new int[n+1];
        return find(n-1,k,arr,dp);
    }
    private static int find(int idx,int k,int[] arr,int[] dp){
        if(idx==0) return 0;
        if(dp[idx]!=0) return dp[idx];
        int min=Integer.MAX_VALUE;
        for(int i=1;i<=k;i++){
            if(idx-i>=0) {
                int jump = find(idx - i, k, arr,dp) + Math.abs(arr[idx] - arr[idx - i]);
                min=Math.min(min,jump);
            }
        }
        return dp[idx]=min;
    }

    //tabulation method

    public static int find2(int k,int[] arr){
        int n= arr.length;
        int[] dp=new int[n];
        dp[0]=0;
        for(int i=1;i<n;i++){
            int min=Integer.MAX_VALUE;
            for(int j=1;j<=k;j++){
                if(i-j>=0){
                    int jump=dp[i-j]+Math.abs(arr[i]-arr[i-j]);
                    min=Math.min(min,jump);
                }
            }
            dp[i]=min;
        }
        return dp[n-1];
    }
    
    public static void main(String[] args) {
        int[] arr={10, 30, 40, 50, 20};
        System.out.println(minimizeCost(3,arr));
        System.out.println(find2(3,arr));
    }
}
