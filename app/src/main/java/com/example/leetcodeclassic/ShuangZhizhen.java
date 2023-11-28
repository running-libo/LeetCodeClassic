package com.example.leetcodeclassic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ShuangZhizhen {

//            ###### [验证回文串](https://leetcode-cn.com/problems/valid-palindrome/) ★
    public boolean isPalindrome(String s) {
        //思路：双指针，从头和尾分别向中间靠拢，如果该位置不为字符或字母，向中间移动
        //如果该位置都为字符或字母，判断两个字符是否相等，相等在往中间循环靠拢，否则返回false

        int start = 0;
        int end = s.length()-1;
        while(start < end) {
            while(start < end && !Character.isLetterOrDigit(s.charAt(start))) {
                //判断左边是否为字符或字母
                start++;
            }

            //判断右边是否为字符或字母
            while(start < end && !Character.isLetterOrDigit(s.charAt(end))) {
                end--;
            }

            if (start < end && Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
                //左右都是字符或字母，则判断是否相等，并且都转为大写或者小写判断
                return false;

            }
            start++;
            end--;
        }

        return true;
    }

//            ###### [删除有序数组中的重复项](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/)  ★
    public int removeDuplicates(int[] nums) {
        //思路：双指针：快指针向前扫，如果nums[fast]!=nums[fast-1]，说明值不同，那么就将值存入nums[slow++]
        //最后返回slow
        if (nums.length == 0) {
            return 0;
        }

        int fast = 1, slow = 1; //第一个数已确定，从第二个数开始扫描
        while(fast < nums.length) {
            if (nums[fast] != nums[fast-1]) {
                //有新的数值
                nums[slow++] = nums[fast];
            }
            fast++;
        }
        return slow;
    }

    //     ###### [在排序数组中查找元素的第一个和最后一个位置](https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/)
    /**
     * 思路：设计start指针，从左边往右遍历，找到target就更新start
     * 设计end指针，从右边往左边遍历，找到target就更新end
     */
    public int[] searchRange(int[] nums, int target) {
        int start = -1, end = -1;
        for (int i=0;i<nums.length;i++) {
            if (nums[i] == target) {
                start = i;
                break;
            }
        }
        for (int i=nums.length-1;i>=0;i--) {
            if (nums[i] == target) {
                end = i;
                break;
            }
        }
        return new int[]{start, end};
    }

//            ###### [三数之和](https://leetcode.cn/problems/3sum/)
    /**
     * 思路：
     * 设定：需要找到3个数，a+b+c=0, 这里 a b c三个数的下标从左到右
     * 定义a的下标为i，b的下标为left，c的下标为right
     * 首先，对数组进行排序
     * 则需要找nums[i]+nums[left]+nums[right] = 0，找到则放入集合中
     * 如果nums[i]+nums[left]+nums[right] > 0，则需right--
     * 如果nums[i]+nums[left]+nums[right] < 0，则需left++
     * 其次，a b c三个数都要去重
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);  //排序
        int left = 0, right = 0;
        for (int i=0;i<nums.length;i++) {
            if (nums[i] > 0) {
                //当前最小数大于0，则找不到符合条件的组
                return list;
            }
            if (i>0 && nums[i] == nums[i-1]) {
                //对i去重:  如果当前组的a和上一组的a相等，则视为重复组
                continue;
            }
            left = i+1;
            right = nums.length-1;
            while(left<right) {
                int temp = nums[i] + nums[left] + nums[right];
                if (temp > 0) {
                    right--;
                } else if (temp < 0) {
                    left++;
                } else {
                    //找到符合条件组
                    list.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //将b c相同元素跳过，即去重
                    while(left < right && nums[left+1] == nums[left]) {
                        //将b去重
                        left++;
                    }
                    while(left < right && nums[right-1] == nums[right]) {
                        //将c去重
                        right--;
                    }
                    left++;
                    right--;
                }
            }
        }
        return list;
    }

//            ###### [颜色分类](https://leetcode.cn/problems/sort-colors/)
    /**
     * 单指针法:
     * 思路：遍历第一次，先把0的往前移动交换，保证0的都在前面排着
     * 遍历第二次，再把2的往后移动交换，保证2的都在后面排着
     *  排序法：任何排序法都可以
     * @param nums
     */
    public void sortColors(int[] nums) {
        int start = 0;
        int end = nums.length-1;
        for (int i=0;i<nums.length;i++) {
            if (nums[i] == 0) {
                swap(nums, i, start);
                start++;
            }
        }
        for (int i=end;i>=start;i--) {
            if (nums[i] == 2) {
                swap(nums, i, end);
                end--;
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

//            ###### [无重复字符的最长子串](https://leetcode.cn/problems/longest-substring-without-repeating-characters/)
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

    public static void main(java.lang.String[] args) {

    }
}
