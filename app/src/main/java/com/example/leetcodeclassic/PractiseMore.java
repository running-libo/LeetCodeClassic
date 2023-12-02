package com.example.leetcodeclassic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PractiseMore {

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++) {
            if (map.containsKey(target-nums[i])) {
                return new int[]{i, map.get(target-nums[i])};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{0,0};
    }

    public void merge(int[] A, int m, int[] B, int n) {
        for (int i=0;i<n;i++) {
            A[m+i] = B[i];
        }
        Arrays.sort(A);
    }

    public int lengthOfLongestSubstring(String s) {
        int start=0;
        int maxLen = 0;
        Set<Character> set = new HashSet<>();
        for (int i=0;i<s.length();i++) {
            start = i;
            while(start<s.length() && set.add(s.charAt(start))) {
                maxLen = Math.max(maxLen, set.size());
                start++;
            }

            set.clear();
        }
        return maxLen;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode pre = null;
        ListNode cur = head;
        for (int i=0;i<left-1;i++) {
            pre = cur;
            cur = cur.next;
        }

        ListNode pre2 = pre;
        ListNode cur2 = cur;
        for (int i=left;i<=right;i++) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        if (pre2 != null) {
            pre2.next = pre;
        } else {
            head = pre;
        }
        cur2.next = cur;
        return head;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode cur = new ListNode(-1);
        ListNode head = cur;
        while(list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }

        cur.next = list1==null ? list2 : list1;
        return head.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        dummy.next = head;
        ListNode cur = head;
        for (int i=0;i<n;i++) {
            cur = cur.next;
        }
        while(cur != null) {
            cur = cur.next;
            pre = pre.next;
        }
        pre.next = pre.next.next;
        return dummy.next;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode head = null;
        ListNode tail = null;
        while(l1!=null || l2 != null) {
            int n1 = l1==null? 0 : l1.val;
            int n2 = l2==null? 0 : l2.val;

            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum%10);
            } else {
                tail.next = new ListNode(sum%10);
                tail = tail.next;
            }

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
            carry = sum / 10;
        }

        if (carry != 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }

    public boolean isPalindrome(String s) {
        int start = 0, end = s.length()-1;
        while(start<end) {
            while(start<end && !Character.isLetterOrDigit(s.charAt(start))) {
                start++;
            }

            while(start<end && !Character.isLetterOrDigit(s.charAt(end))) {
                end--;
            }
            if (Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
                return false;
            }

            start++;
            end--;
        }
        return true;
    }

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
        return new int[]{start,end};
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        int left = 0, right = 0;
        for (int i=0;i<nums.length;i++) {
            if (i>0 && nums[i-1] == nums[i]) {
                continue;
            }

            left = i+1;
            right = nums.length-1;
            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    list.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while(left<right && nums[left+1] == nums[left]) {
                        left++;
                    }
                    while(left<right && nums[right-1] == nums[right]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return list;
    }

    public int mySqrt(int x) {
        int start = 0, end = x;
        int ans = 0;
        while(start<=end) {
            int middle = start + (end-start)/2;
            if ((long)middle*middle <= x) {
                ans = middle;
                start = middle+1;
            } else {
                end = middle-1;
            }
        }
        return ans;
    }

    public boolean canJump(int[] nums) {
        int rightMost = nums[0];
        for (int i=0;i<nums.length;i++) {
            if (i<=rightMost) {
                rightMost = Math.max(rightMost, i+nums[i]);

                if (rightMost >= nums.length-1) {
                    return true;
                }
            }
        }
        return false;
    }

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        res.add(new ArrayList<>());
        dfs(nums, 0);
        return res;
    }

    public void dfs(int[] nums, int index) {
        if (path.size() == nums.length) {
            return;
        }
        for (int i=index;i<nums.length;i++) {
            path.add(nums[i]);
            res.add(new ArrayList<>(path));
            dfs(nums, i+1);
            path.remove(path.size()-1);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates, target, 0);
        return res;
    }

    public void dfs(int[] candidates, int target, int index) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i=index;i<candidates.length;i++) {
            if (candidates[i] > target) {
                break;
            }
            path.add(candidates[i]);
            dfs(candidates, target-candidates[i], i);
            path.remove(path.size()-1);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        dfs(nums);
        return res;
    }

    public void dfs(int[] nums) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i=0;i<nums.length;i++) {
            if (path.contains(nums[i])) {
                continue;
            }
            path.add(nums[i]);
            dfs(nums);
            path.remove(path.size()-1);
        }
    }

    public String longestPalindrome(String s) {
        String res = "";
        int maxLeng = 0;
        for (int i=0;i<s.length();i++) {
            for (int j=i+1;j<s.length();j++) {
                if (j-i < maxLeng) {
                    continue;
                }

                String curStr = s.substring(i,j);
                if (isPalindromic(curStr) && curStr.length() > maxLeng) {
                    //是回文子串，判断更新最长回文子串
                    res = curStr;
                    maxLeng = curStr.length();
                }
            }
        }
        return res;
    }

    public boolean isPalindromic(String s) {
        int start = 0, end = s.length()-1;
        while(start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

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

    /**
     * 能观察到这么一个规律：
     * root的左子树 的左孩子 == root的右子树 的右孩子
     * root的左子树 的右孩子 == root的右子树 的左孩子
     * 终止条件：
     *
     * left 和 right 不等，或者 left 和 right 都为空
     * 递归的比较 left.left 和 right.right，递归比较 left.right 和 right.left
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return dfs(root.left, root.right);
    }

    /**
     * 当前需要对比的两个节点，不一定是来自同一个父节点，而是对称的两个节点
     * @param left
     * @param right
     * @return
     */
    boolean dfs(TreeNode left, TreeNode right) {
        if (left == null && right == null) { //都为null时对称的
            return true;
        }
        if (left==null || right==null) { //不都为null则不对称
            return false;
        }
        if (left.val != right.val) {
            return false;
        }

        //再递归的比较 左节点的左孩子 和 右节点的右孩子
        //以及比较  左节点的右孩子 和 右节点的左孩子
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }

    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }

        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for(Map.Entry<Integer, Integer> entry : entries) {
            if (entry.getValue() * 2 > nums.length) {
                return entry.getKey();
            }
        }
        return -1;
    }

}
