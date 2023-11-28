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
    /**
     * 思路：滑动窗口解法
     * 以 (a)bcabcbb 开始的最长字符串为 (abc)abcbb
     * 以 a(b)cabcbb 开始的最长字符串为 a(bca)bcbb
     * ...
     * 以 abcabc(b)b 开始的最长字符串为 abcabc(b)b
     * 以 abcabcb(b) 开始的最长字符串为 abcabcb(b)
     * 枚举每个位置开始下标为i，从这里开始找一个右指针rk，在一个范围内找无重复字符，用Hashset记录是否重复，直到遇到重复字符，则结束本次窗口。
     * 每次循环一个i更新窗口的最大长度，并且清空set
     */
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet();
        int n = s.length();
        int maxLeng = 0;
        for (int i=0;i<n;i++) {
            int right = i;
            while(right < n && set.add(s.charAt(right))) {
                //新字符添加成功，右移右指针
                right++;
            }
            maxLeng = Math.max(maxLeng, set.size()); //set存放为i起始的、无重复字符的子字符集
            set.clear();
        }
        return maxLeng;
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
