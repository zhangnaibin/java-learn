package com.znb.java.learn.leecode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class P123 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 1) {
            return 0;
        }
        int i = 0;
        int valley;
        int peak;
        List<Integer> profileList = new ArrayList<>();
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1])
                i++;
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1])
                i++;
            peak = prices[i];
            profileList.add(peak - valley);
        }
        profileList.sort(Comparator.reverseOrder());
        if (profileList.size() >= 2) {
            return profileList.get(0) + profileList.get(1);
        }
        if (profileList.size() == 0) {
            return 0;
        }

        return profileList.get(0);
    }

    public static void main(String[] args) {
        P123 x = new P123();
//        System.out.println(x.maxProfit(new int[]{7,1,5,3,6,4}));
//        System.out.println(x.maxProfit(new int[]{1,2,3,4,5}));
//        System.out.println(x.maxProfit(new int[]{7,6,4,3,1}));
//        System.out.println(x.maxProfit(new int[]{2,1,2,0,1}));
        System.out.println(x.maxProfit(new int[]{1,2,4,2,5,7,2,4,9,0}));
    }
}
