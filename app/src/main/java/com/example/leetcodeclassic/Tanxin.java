package com.example.leetcodeclassic;

public class Tanxin {

//            ###### [买卖股票的最佳时机](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/)
    public int maxProfit(int[] prices) {
        //思路：遍历一遍，记录一个最小值，如果当前不是最小值，那么与最小值做差，假定在这天卖，记录一个最大利润
        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i=1;i<prices.length;i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                //那么假如在这天卖，记录当前的差价
                maxProfit = Math.max(prices[i]-minPrice, maxProfit);
            }
        }
        return maxProfit;
    }

//            ###### [买卖股票的最佳时机 II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/)
    /**
     * 只要有涨幅区间，都要吃掉股价涨幅
     */
    public int maxProfit2(int[] prices) {
        int profit = 0;
        for(int i=prices.length-1;i>0;i--) {
            int dif = prices[i] - prices[i-1];
            if(dif > 0) {
                //只要后一天股价比前一天高，都要累计收益
                profit += dif;
            }
        }
        return profit;
    }

//            ###### [跳跃游戏](https://leetcode.cn/problems/jump-game/)


    public static void main(java.lang.String[] args) {

    }
}
