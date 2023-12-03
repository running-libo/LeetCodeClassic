package com.example.leetcodeclassic;

import java.util.Arrays;

public class Dongtai {

//            ###### [爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/) ★
    public int climbStairs(int n) {
        //思路：用动态规划思想，要爬到n阶，可以通过最后爬一步和两步2中爬法到，到n阶的方法等于到n-1阶方法加上n-2阶方法，即f(n)=f(n-1)+f(n-2)，这个f(n)方法就用递归实现
        //此外，f(n)=f(n-1)+f(n-2)，从n为3开始，该函数计算得出的是一个斐波那契数列，当前数为前两个数之和，所以有两种解法
        if (n < 3) {
            return n;
        }

        int a = 1;
        int b = 2;
        int cur = 0;
        for (int i=3;i<=n;i++) {
            cur = a + b;
            a = b;
            b = cur;
        }
        return cur;
    }

//            ###### [最大子数组和](https://leetcode.cn/problems/maximum-subarray/)★
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i=1;i<nums.length;i++) {
            //如果i前一个数的子数组和小于0，就不要加上前面的累赘了，否则就加上
            dp[i] = nums[i] + Math.max(dp[i-1], 0);

            //遍历的同时获取dp数组中的最大值
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }

//            ###### [零钱兑换](https://leetcode.cn/problems/coin-change/)

    /**
     * 示例： 1 2 3 面值，   要凑齐5元
     * 凑钱数的最小硬币数 = Min(1个1元硬币 + 凑齐4元所需硬币数, 1个2元硬币 + 凑齐3元所需硬币数, 1个3元硬币 + 凑齐2元所需硬币数)
     * 思路：dp[i]表示对应价格i所需最小硬币数，coins表示硬币面值
     * 则 dp[i] = min(1+dp[p-coins[j]])
     */
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

//            ###### [打家劫舍](https://leetcode.cn/problems/house-robber/)
    /**
     *  要解前n间房能偷的最大金额，那就尝试先从前1、2、3间能偷的最大金额找规律
     Sn表示前n间房能偷的最大金额    Hn表示第n间房的金额
     示例： [1,2,3,1]
     前1间房能偷的最大金额  S1 = H1 = 1
     前2间房能偷的最大金额  S2 = max(S1,H1) = 2
     从第三间房开始，就有2种偷法了，就是偷第n间房和不偷第n间房
     偷第n间房，那就不能偷n-1间房，能偷n-2间房 那么最大金额不一样的取决于最近这三间房怎么偷，因为前Sn-2都一样
     前3间房能偷的最大金额  S3 = max(S2, H3+S1) = 4
     前n间房能偷的最大金额  Sn = max(Sn-1, Hn+Sn-2)
     */
    public int rob(int[] nums) {
        int length = nums.length;
        if(length <= 1) {
            return nums[0];
        }
        int[] dp = new int[length];  //dp[i]表示前i间房能偷的最大金额
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for(int i=2;i<length;i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2]+nums[i]);
        }
        return dp[length-1];  //这里需要得到前n间房的最大值，所以是返回dp[length-1]
    }

    //    ##### [最长递增子序列](https://leetcode.cn/problems/longest-increasing-subsequence/)
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length]; //这个dp[i] 表示以 nums[i] 为结尾的最长递增子序列长度
        //遍历i时，需要从头到尾遍历0~i，找出最大的子序列长度
        dp[0] = 1;
        int maxLeng = dp[0];
        for (int i=1;i<nums.length;i++) {
            //这里需要初始dp[i]=1，最小长度有一个
            dp[i] = 1;
            for (int j=0;j<i;j++) {
                if (nums[i] > nums[j]) {
                    //则dp[i]会比 dp[j] 大1个序列长度
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLeng = Math.max(maxLeng, dp[i]); //从每个dp[i]中找最大的一个
        }
        return maxLeng;
    }

    public static void main(String[] args) {

    }
}
