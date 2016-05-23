package com.znb.java.learn.leecode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhangnaibin@xiaomi.com
 * @time 2016-05-23 上午11:34
 */
public class P3 {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<Character>();
        int max = 0;
        int quick = 0, slow = 0;
        while (quick < s.length() && slow <= quick) {
            char temp = s.charAt(quick);
            if (!set.contains(temp)) {
                set.add(temp);
                quick ++;
                if (max < set.size()) {
                    max = set.size();
                }
            } else {
                char tempSlow = s.charAt(slow);
                set.remove(tempSlow);
                slow ++;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new P3().lengthOfLongestSubstring("bbbb"));
    }
}
