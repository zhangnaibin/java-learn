package com.znb.java.learn.leecode;

public class P121 {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfile = 0;
        for (int i = 0; i < prices.length; i ++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfile) {
                maxProfile = prices[i] - minPrice;
            }
        }
        return maxProfile;
    }
}
