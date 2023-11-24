package com.example.leetcodeclassic;

import java.util.HashSet;
import java.util.Set;

public class Strings {

//    ###### [反转字符串](https://leetcode-cn.com/problems/reverse-string/)
    public void reverseString(char[] s) {
        //原地倒转字符数组，采用对应位置交换值的方法
        int start = 0;
        int end = s.length-1;
        //要交换的次数
        char temp;
        while (start < end) {
            temp = s[start];
            s[start] = s[end];
            s[end] = temp;

            //位置往中间靠
            start++;
            end--;
        }

    }


//    ###### [无重复字符的最长子串](https://leetcode.cn/problems/longest-substring-without-repeating-characters/)
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            // 哈希集合，记录每个字符是否出现过
            Set<Character> occ = new HashSet<Character>();
            int n = s.length();
            // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
            int rk = -1, ans = 0;
            for (int i = 0; i < n; ++i) {
                if (i != 0) {
                    // 左指针向右移动一格，移除一个字符
                    occ.remove(s.charAt(i - 1));
                }
                while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                    // 不断地移动右指针
                    occ.add(s.charAt(rk + 1));
                    ++rk;
                }
                // 第 i 到 rk 个字符是一个极长的无重复字符子串
                ans = Math.max(ans, rk - i + 1);
            }
            return ans;
        }
    }

    //    ###### [最长回文子串](https://leetcode.cn/problems/longest-palindromic-substring/)

    /**
     * 思路：暴力解法 (击败 5.00% 的java用户)
     * 遍历从0位置作为起始到末尾的i下标，到i+1~末尾范围的字符串的判断，这里是一个双重for循环
     * 用maxleng表示当前的最大回文子串长度，如果这个范围小于maxleng，那就不用判断回文了
     * 如果是回文，则更新这个返回字符串和maxLeng
     */
    public String longestPalindrome(String s) {
        int leng = s.length();
        String resultStr = "";
        int maxLeng = 0; //记录当前的最长子回文串长度
        for (int i=0;i<leng;i++) {
            for (int j=i+1;j<=leng;j++) {
                //如果i-j范围已经小于maxLeng长度了，就不用去判断是否回文了
                if (j-i < maxLeng) {
                    continue;
                }
                String test = s.substring(i,j);
                if (isPalindromic(test)) {
                    //当前i-j范围是回文串
                    resultStr = test;
                    maxLeng =test.length();
                }
            }
        }
        return resultStr;
    }

    /**
     * 简单双指针判断一下是否是回文串
     */
    public boolean isPalindromic(String s) {
        int start = 0, end = s.length()-1;
        while(start<end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            } else {
                start++;
                end--;
            }
        }
        return true;
    }

    public static void main(java.lang.String[] args) {

    }
}
