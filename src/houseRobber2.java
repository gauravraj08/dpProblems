public class houseRobber2 {
    public static int rob(int[] nums) {
        int n=nums.length;

        if(n==1) return nums[0];

        int[] dp1=new int[n+1];
        int[] dp2=new int[n+1];

        int case1=find(n-2,nums,dp1,0);
        int case2=find(n-1,nums,dp2,1);

        return Math.max(case1,case2);
    }
    private static int find(int idx,int[] nums,int[] dp,int start){
        if(idx<start) return 0;
        if(idx==start) return nums[idx];
        if(dp[idx]!=0) return dp[idx];

        int take=nums[idx]+find(idx-2,nums,dp,start);
        int notTake=find(idx-1,nums,dp,start);

        return dp[idx]=Math.max(take,notTake);
    }

    //tabulation method
    public static int rob2(int[] nums) {
        int n=nums.length;

        if(n==1) return nums[0];
        if(n==2) return Math.max(nums[0],nums[1]);

        int case1=find2(0,n-2,nums);
        int case2=find2(1,n-1,nums);

        return Math.max(case1,case2);
    }
    private static int find2(int start,int end,int[] nums){
        int n=end-start+1;
        int[] dp=new int[n+1];

        dp[0]=0;
        dp[1]=nums[start];

        for(int i=2;i<=n;i++){
            dp[i]=Math.max(dp[i-1],nums[start+i-1]+dp[i-2]);
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int[] arr={2,3,2};
        System.out.println(rob(arr));
        System.out.println(rob2(arr));

    }
}
