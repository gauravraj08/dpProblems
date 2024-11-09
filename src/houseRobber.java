import java.util.Arrays;

public class houseRobber {

    //recursive memo approach
    public static int rob(int[] nums) {
        int n=nums.length;
        int[] dp=new int[n];
        Arrays.fill(dp,-1);
        return find(n-1,nums,dp);
    }
    private static int find(int idx,int[] nums,int[] dp){
        if(idx<0) return 0;
        if(idx==0) return nums[idx];
        if(dp[idx]!=-1) return dp[idx];

        int pick=nums[idx]+find(idx-2,nums,dp);
        int notPick=find(idx-1,nums,dp);

        return dp[idx]=Math.max(pick,notPick);
    }

    //tabulation approach

    private static int find2(int[] nums){
        int n= nums.length;
        int[] dp=new int[n];
        dp[0]=nums[0];
        for(int i=1;i<n;i++){
            int take=nums[i];
            if(i>1) take+=dp[i-2];
            int notTake=dp[i-1];

            dp[i]=Math.max(take,notTake);
        }
        return dp[n-1];
    }

    //space optimization for tabulation

    private static int find3(int[] nums){
        int n= nums.length;
        int prev=nums[0];
        int prev2=0;
        for(int i=1;i<n;i++){
            int take=nums[i];
            if(i>1) take+=prev2;
            int notTake=prev;

            int curr=Math.max(take,notTake);

            prev2=prev;
            prev=curr;
        }
        return prev;
    }

    public static void main(String[] args) {
        int[] arr={1,2,3,1};
        System.out.println(rob(arr));
        System.out.println(find2(arr));
        System.out.println(find3(arr));
    }
}
