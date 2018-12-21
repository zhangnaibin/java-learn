package com.znb.java.learn.leecode;

import java.util.HashMap;
import java.util.Map;

public class P70 {
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public int climbStairs2(int n) {
        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        Map<Integer, Integer> cache = new HashMap<>();
        cache.put(1, 1);
        cache.put(2, 2);
        int i = 3;
        while (i <= n) {
            Integer count = cache.get(n - 1) + cache.get(n - 2);
            cache.put(i, count);
            i ++;
        }

        return cache.get(n);
    }

    public int climbStairs3(int n) {
        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        int pre2 = 1;
        int pre1 = 2;
        int i = 3;
        int cur = 0;
        while (i <= n) {
            cur = pre1 + pre2;
            pre2 = pre1;
            pre1 = cur;
            i ++;
        }

        return cur;
    }
}
