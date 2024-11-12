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

    //tabulation

    public static int find2(int[][] points,int n){
        int[][] dp=new int[n][4];

        dp[0][0]=Math.max(points[0][1],points[0][2]);
        dp[0][1]=Math.max(points[0][0],points[0][2]);
        dp[0][2]=Math.max(points[0][0],points[0][1]);
        dp[0][3]=Math.max(points[0][0],Math.max(points[0][1],points[0][2]));

        for(int day=1;day<n;day++){
            for(int last=0;last<4;last++){
                dp[day][last]=0;
                for(int task=0;task<=2;task++){
                    int activity=points[day][task]+dp[day-1][task];
                    dp[day][last]=Math.max(dp[day][last],activity);
                }
            }
        }
        return dp[n-1][3];
    }

    public static int find3(int[][] points,int n){
        int[] prev=new int[4];

        prev[0]=Math.max(points[0][1],points[0][2]);
        prev[1]=Math.max(points[0][0],points[0][2]);
        prev[2]=Math.max(points[0][0],points[0][1]);
        prev[3]=Math.max(points[0][0],Math.max(points[0][1],points[0][2]));

        for(int day=1;day<n;day++){
            int[] temp=new int[4];
            for(int last=0;last<4;last++){
                temp[last]=0;
                for(int task=0;task<=2;task++){
                    if(task!=last)
                        temp[last]=Math.max(temp[last],points[day][task]+prev[task]);
                }
            }
            prev=temp;
        }
        return prev[3];
    }



    public static void main(String[] args) {
        int[][] points = {{10, 40, 70},
                {20, 50, 80},
                {30, 60, 90}};

        int n = points.length;
        System.out.println(maximumPoints( points, n));
        System.out.println(find2(points,n));
        System.out.println(find3(points,n));
    }
}
