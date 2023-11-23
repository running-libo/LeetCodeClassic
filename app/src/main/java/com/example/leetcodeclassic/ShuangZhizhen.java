package com.example.leetcodeclassic;

import java.util.ArrayList;
import java.util.Arrays;
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


//            ###### [无重复字符的最长子串](https://leetcode.cn/problems/longest-substring-without-repeating-characters/)


    public static void main(java.lang.String[] args) {

    }
}
