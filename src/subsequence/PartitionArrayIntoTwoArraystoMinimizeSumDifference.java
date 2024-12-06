package subsequence;

public class PartitionArrayIntoTwoArraystoMinimizeSumDifference {
    public static int minimumDifference(int[] nums) {
        int n=nums.length;
        int sum=0;
        for(int i:nums) sum+=i;

        int target=sum;

        boolean[][] dp=new boolean[n][target+1];

        for(int i=0;i<n;i++){
            dp[i][0]=true;
        }

        if(nums[0]<=target) dp[0][nums[0]]=true;

        for(int i=1;i<n;i++){
            for(int k=1;k<=target;k++){
                boolean notTaken=dp[i-1][k];
                boolean taken=false;
                if(k>=nums[i]) taken=dp[i-1][k-nums[i]];

                dp[i][k]=taken||notTaken;
            }
        }

        int min=Integer.MAX_VALUE;

        for(int s1=0;s1<=sum/2;s1++){
            if(dp[n-1][s1]==true) {
                min = Math.min(min, Math.abs(sum-s1)-s1);
            }
        }
        return min;
    }
    public static void main(String[] args) {
        int[] arr={-36,36};
        System.out.println(minimumDifference(arr));
    }
}
