public class ninNin {
    public static int maximumPoints(int arr[][], int n) {
        // code here
        int[][] dp=new int[n][4];
        return find(n-1,3,arr,dp);
    }
    private static int find(int day,int last,int[][] arr,int[][] dp){

        if(dp[day][last]!=0) return dp[day][last];

        if(day==0){
            int max=0;
            for(int i=0;i<=2;i++){
                if(last!=i) max=Math.max(max,arr[0][i]);
            }
            return dp[day][last] = max;
        }

        int max=0;

        for(int i=0;i<=2;i++){
            if(i!=last){
                int activity=arr[day][i]+find(day-1,i,arr,dp);
                max=Math.max(max,activity);
            }
        }
        return dp[day][last]=max;
    }
    public static void main(String[] args) {
        int[][] points = {{10, 40, 70},
                {20, 50, 80},
                {30, 60, 90}};

        int n = points.length;
        System.out.println(maximumPoints( points, n));
    }
}
