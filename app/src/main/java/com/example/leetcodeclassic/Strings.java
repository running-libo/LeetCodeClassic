package com.example.leetcodeclassic;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

//    ##### [找出字符串中第一个匹配项的下标](https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/)
    public int strStr(String haystack, String needle) {
        if (!haystack.contains(needle)) {
            return -1;
        } else {
            for (int i=0;i<=haystack.length()-needle.length();i++) {
                String subStr = haystack.substring(i, i+needle.length());
                if (subStr.equals(needle)) {
                    return i;
                }
            }
        }
        return -1;
    }

//    ##### [罗马数字转整数](https://leetcode.cn/problems/roman-to-integer/)

    /**
     * 通常情况下，罗马数字中小的数字在大的数字的右边。若输入的字符串满足该情况，那么可以将每个字符视作一个单独的值，累加每个字符对应的数值即可。
     * 若存在小的数字在大的数字的左边的情况，根据规则需要减去小的数字。对于这种情况，我们也可以将每个字符视作一个单独的值，若一个数字右侧的数字比它大，则将该数字的符号取反。
     * @param s
     */
    public int romanToInt(String s) {
        int res = 0;
        for (int i=0;i<s.length();i++) {
            int value = symbolValues.get(s.charAt(i)); //当前罗马字符代表的数字大小

            if (i<s.length()-1 && value < symbolValues.get(s.charAt(i+1))) {
                //当前的数比下一个符号表示的数小，则需要减掉
                res -= value;
            } else {
                //否则就是相加 ,最后一个字符不用判断正负，肯定是相加的
                res += value;
            }
        }
        return res;
    }
    Map<Character,Integer> symbolValues = new HashMap<Character, Integer>() {
        {
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }
    };

//    ##### [最长公共前缀](https://leetcode.cn/problems/longest-common-prefix/)
    public static String longestCommonPrefix(String[] strs) {
        String str = strs[0];
        StringBuilder sb = new StringBuilder();
        //取第一个字符串为基准
        for (int i=0;i<str.length();i++) {
            Character c = str.charAt(i);
            //遍历取出第一个字符串的每个字符
            for (int j=1;j<strs.length;j++) {
                //遍历后面每个字符串
                if (i < strs[j].length() && strs[j].charAt(i) == c) {
                    //如果当前 i 没有超出某个字符串长度并且对应位置字符相同，则取判断下一个字符串
                    continue;
                } else {
                    //如果有长度不够或者字符不同的，则直接返回最长前缀字符串
                    return sb.toString();
                }
            }
            sb.append(c); //该字符 c 满足条件
        }
        return sb.toString();
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
            for (int j=i+1;j<=leng;j++) {   //注意：这里需要  j<=leng
                //如果i-j范围已经小于maxLeng长度了，就不用去判断是否回文了
                if (j-i < maxLeng) {
                    continue;
                }
                String curStr = s.substring(i,j);
                if (isPalindromic(curStr)) {
                    //是回文子串，判断更新最长回文子串
                    resultStr = curStr;
                    maxLeng = curStr.length();
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

//    ##### [反转字符串中的单词](https://leetcode.cn/problems/reverse-words-in-a-string/)

    public String reverseWords(String s) {
        s = s.trim();
        List<String> list = Arrays.asList(s.split("\\s+"));  // 正则匹配连续的空白字符作为分隔符分割
        Collections.reverse(list); //反转集合各个元素
        return String.join(" ", list); //String.join在集合每个元素之间添加分割符号
    }

//    ###### [字符串转换整数 (atoi)](https://leetcode.cn/problems/string-to-integer-atoi/)
    public int myAtoi(String str) {
        str = str.trim();  //先去掉前后空格
        if (str.length() == 0) return 0;

        if (!Character.isDigit(str.charAt(0)) && str.charAt(0) != '-' && str.charAt(0) != '+') {
            //如果第一个字符 不是数字、不是 +-符号，则数值为0
            return 0;
        }

        long ans = 0;
        boolean neg = str.charAt(0) == '-';  //是否是-号开头的
        int i = !Character.isDigit(str.charAt(0)) ? 1 : 0;  //判断第一个字符是否是数字
        //定义取字符的下标，不是数字从下标1开始取，是数字从下标0开始取
        while(i < str.length() && Character.isDigit(str.charAt(i))) {
            ans = ans * 10 + (str.charAt(i++) - '0');  //取出 第i个字符的数值，拼接数值
            if (!neg && ans > Integer.MAX_VALUE) {
                ans = Integer.MAX_VALUE;
                break;  //是整数且越界了
            }
            if (neg && ans > 1L + Integer.MAX_VALUE) {
                ans = 1L + Integer.MAX_VALUE;
                break;  //是负数且越界了
            }
        }
        return neg ? (int) -ans : (int)ans;
    }

    public static void main(java.lang.String[] args) {

    }
}
