package com.example.leetcodeclassic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Array {

//    ###### [两数之和](https://leetcode-cn.com/problems/two-sum/)
    public int[] twoSum(int[] nums, int target) {
        //思路：创建一个map，遍历nums，如果没存过该数，就存入该数值和下标，如果存过，就查找map中有这个数的配对的数(target-nums[i])吗
        //如果存在，返回这两个数的下标，不存在，则抛出异常
        Map<Integer, Integer> record = new HashMap<>();
        for (int i=0;i<nums.length;i++) {

            if (record.containsKey(target-nums[i])) {
                //找到这对数，返回两个数的下标
                int pos = record.get(target-nums[i]);
                return new int[]{pos, i};
            }

            record.put(nums[i], i);
        }

        //遍历完如果仍找不到，就抛出异常
        throw new IllegalArgumentException("找不到这两个数");
    }

//    ###### [加一](https://leetcode-cn.com/problems/plus-one/)
    public static int[] plusOne(int[] digits) {
        //思路：遍历数组，从最低位开始将每个数字判断，如果不是9，就++
        //如果不是9，就+1，如果是9，就变为0，再判断高一位
        for (int i=digits.length-1;i>=0;i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            } else {
                digits[i] = 0;
            }
        }

        //能走到这里，说明原来数为9..99这种情况，现在都变为0..00，需要重建数组，第一位为1，后面都为0
        int[] newDigits = new int[digits.length+1];
        newDigits[0] = 1;
        return newDigits;
    }

//    ###### [二分查找](https://leetcode.cn/problems/binary-search/)
    /**
     * 思路：用while循环做条件，对数组范围内取中间元素判断
     * 如果相等则找到，如果target小，往左边找，如果target大，往右边找
     * 并且start或end范围重设，middle重取值继续循环
     */
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length-1;

        while(start <= end) {
            int middle = (start+end)/2;
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] > target){
                end = middle-1;
            } else {
                start = middle+1;
            }
        }

        return -1;
    }

//    ###### [删除有序数组中的重复项](https://leetcode.cn/problems/remove-duplicates-from-sorted-array/)
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

//    ###### [最长连续递增序列](https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence/)
    public int findLengthOfLCIS(int[] nums) {
        if(nums.length <= 1) {  //长度为0，则为0，长度为1，则为1
            return nums.length;
        }
        int max = 0;
        int start = 0; //每次递增区间的起头的数，即最小的数
        for(int i=1;i<nums.length;i++) {
            if(nums[i] <= nums[i-1]) {
                //下降趋势，更新起点
                start = i;
            }
            //后一个大，上涨趋势
            max = Math.max(max, i+1-start);
        }
        return max;
    }

//    ###### [合并排序的数组](https://leetcode.cn/problems/sorted-merge-lcci/)
    public void merge(int[] A, int m, int[] B, int n) {
        //思路：遍历从B中取数字，插入到A数组中到正确位置
        //将B数组放入A数组末尾
        for (int i=0;i<B.length;i++) {
            A[m+i] = B[i];
        }

        //对数组A进行排序
        Arrays.sort(A);
    }

    public void merge2(int[] A, int m, int[] B, int n) {
        int pa = 0, pb = 0;
        int[] sorted = new int[m + n];
        int cur;
        while (pa < m || pb < n) {
            if (pa == m) {
                cur = B[pb++];
            } else if (pb == n) {
                cur = A[pa++];
            } else if (A[pa] < B[pb]) {
                cur = A[pa++];
            } else {
                cur = B[pb++];
            }
            sorted[pa + pb - 1] = cur;
        }
        for (int i = 0; i != m + n; ++i) {
            A[i] = sorted[i];
        }
    }

//    ###### [数组中的第K个最大元素](https://leetcode.cn/problems/kth-largest-element-in-an-array/)

    /**
     * 思路：用自带Arrays.sort进行排序，返回第nums.length-k个数即可
     * 如不能用Arrays.sort，则自行写冒泡、插入、选择、快拍等排序方式
     */
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }

//            ###### [只出现一次的数字](https://leetcode-cn.com/problems/single-number/)
    /**
     * 使用异或的3个结论
     * 一个数和 0 做 XOR 运算等于本身：a⊕0 = a
     * 一个数和其本身做 XOR 运算等于 0：a⊕a = 0
     * XOR 运算满足交换律和结合律：a⊕b⊕a = (a⊕a)⊕b = 0⊕b = b
     */
    public int singleNumber(int[] nums) {
        //遍历每个元素，循环元素使用异或，会将出现2次的互相异或为0，最终只剩下a^0=a，a为出现一次的元素
        //例如：  b^c^d^b^d = (b^b)^(d^d)^c=0^c=c ，即c为单身的那位
        int result = 0;
        for (int i=0;i<nums.length;i++) {
            result ^= nums[i];
        }

        return result;
    }

//            ###### [二进制链表转整数](https://leetcode-cn.com/problems/convert-binary-number-in-a-linked-list-to-integer/)
    /**
     * 思路：
     * 遍历链表，将链表的值存入栈中，遍历栈，依次弹出栈，每个数字✖️2的pos次方，达到链表反转的功能
     * 然后按照该栈的顺序将二进制转十进制
     * @param head
     * @return
     */
     public int getDecimalValue(Lists.ListNode head) {
         Stack<Integer> stack = new Stack<>();
         stack.push(head.val);
         //链表值存入栈
         while (head.next != null) {
             stack.push(head.next.val);
             head = head.next;
         }
         int pos = 0;
         int total = 0;
         while (!stack.isEmpty()) {
             //每个数字在对应pos位置乘以2的pos次方，直接左移几位就可以了
             total += (stack.pop() << pos);
             pos++;
         }
         return total;
     }

//     ###### [前 K 个高频元素](https://leetcode.cn/problems/top-k-frequent-elements/)

    /**
     * 思路：
     * 统计每个数出现的次数
     * 然后得到次数最大值
     * 从次数最大值依次递减，用containsValue先判断，然后遍历map元素中的次数是否匹配，添加到新的数组中
     * @param nums
     * @param k
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();
        int[] res = new int[k];
        for (int i=0;i<nums.length;i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i])+1);
            } else {
                map.put(nums[i], 1);
            }
        }

        int maxTimes = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxTimes) {
                maxTimes = entry.getValue();
            }
        }

        for (int i=0;i<k;i++) { //放入数组0到k-1的位置
            if (map.containsValue(maxTimes)) {
                //有出现maxTimes次数的元素
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    if (entry.getValue() == maxTimes) {
                        res[k-1] = entry.getKey();
                        k--;
                    }
                }
            }
            maxTimes--;
        }

        return res;
    }

    public static void main(java.lang.String[] args) {

    }
}
