package com.example.leetcodeclassic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Practise {
    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++) {
            if (map.containsKey(target-nums[i])) {
                return new int[] {i, map.get(target-nums[i])};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[] {0, 0};
    }

    public int[] plusOne(int[] digits) {
        for (int i=digits.length-1;i>=0;i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i] += 1;
                return digits;
            }
        }

        int[] array = new int[digits.length+1];
        array[0] = 1;
        return array;
    }

    public int removeDuplicates(int[] nums) {
        int pos = 1;
        for (int i=1;i<nums.length;i++) {
            if (nums[i] != nums[i-1]) {
                nums[pos++] = nums[i];
            }
        }
        return pos;
    }

    public int search(int[] nums, int target) {
        int start = 0, end = nums.length-1;
        while(start <= end) {
            int middle = (start+end)/2;
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] < target) {
                start = middle+1;
            } else {
                end = middle-1;
            }
        }
        return -1;
    }

    public int findLengthOfLCIS(int[] nums) {
        int start = 0;
        int maxLen = 1;
        for (int i=1;i<nums.length;i++) {
            if (nums[i] <= nums[i-1]) {
                start = i;
            }
            maxLen = Math.max(maxLen, i-start+1);
        }
        return maxLen;
    }

    public void merge(int[] A, int m, int[] B, int n) {
        for (int i=0;i<n;i++) {
            A[m+i] = B[i];
        }
        Arrays.sort(A);
    }

    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
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

        int[] res = new int[k];
        while(k>0) {
            if (map.containsValue(maxTimes)) {
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

    public void reverseString(char[] s) {
        int start = 0, end = s.length-1;
        while(start < end) {
            Character temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }

    public String reverseWords(String s) {
        String[] strArray = s.trim().split("\\s+");
        List<String> list = Arrays.asList(strArray);
        Collections.reverse(list);
        return String.join(" ", list);
    }

    public String longestCommonPrefix(String[] strs) {
        String str = strs[0];
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<str.length();i++) {
            Character ch = str.charAt(i);
            for (int j=1;j<strs.length;j++) {
                if (i<strs[j].length() && strs[j].charAt(i) == ch) {
                    continue;
                } else {
                    return stringBuilder.toString();
                }
            }
            stringBuilder.append(ch);
        }
        return stringBuilder.toString();
    }

    public int strStr(String haystack, String needle) {
        if (!haystack.contains(needle)) {
            return -1;
        } else {
            for (int i=0;i<=haystack.length()-needle.length();i++) {
                String str = haystack.substring(i, i+needle.length());
                if (str.equals(needle)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int res = 0;
        for (int i=0;i<s.length();i++) {
            int num = map.get(s.charAt(i));
            if (i<s.length()-1 && num < map.get(s.charAt(i+1))) {
                res -= num;
            } else {
                res += num;
            }
        }
        return res;
    }

    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int index = 0;
        int maxLen = 0;
        for (int i=0;i<s.length();i++) {

            index = i;
            while(index < s.length() && set.add(s.charAt(index))) {
                index++;
            }
            maxLen = Math.max(maxLen, set.size());
            set.clear();
        }
        return maxLen;
    }

    public String longestPalindrome(String s) {
        int index = 0;
        int maxLen = 0;
        String res = "";
        for (int i=0;i<s.length();i++) {
            index = i+1;

            while(index <= s.length()) {
                if (index-i < maxLen) {
                    index++;
                    continue;
                }

                String subString = s.substring(i, index);
                if (isPalidrome(subString)) {
                    res = subString;
                    maxLen = subString.length();
                }
                index++;
            }

        }
        return res;
    }

    private boolean isPalidrome(String s) {
        int start = 0, end = s.length()-1;
        while(start<end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        int minDepth = Integer.MAX_VALUE;

        if (root.left != null) {
            minDepth = Math.min(minDepth, minDepth(root.left));
        }
        if (root.right != null) {
            minDepth = Math.min(minDepth, minDepth(root.right));
        }
        return minDepth+1;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return root1==null ? root2 : root1;
        }

        root1.val = root1.val + root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }

    public boolean isSymmetric(TreeNode root) {
        return dfs(root.left, root.right);
    }

    public boolean dfs(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        } else if (left.val != right.val) {
            return false;
        }
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        return dfs2(p, q);
    }

    public boolean dfs2(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        } else if (left.val != right.val) {
            return false;
        }
        return dfs(left.left, right.left) && dfs(left.right, right.right);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.add(root);
        }

        while(!queue.isEmpty()) {
            int size = queue.size();

            List<Integer> list = new ArrayList<>();
            for (int i=0;i<size;i++) {
                TreeNode node = queue.pop();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(list);
        }

        return result;
    }

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates, target, 0);
        return result;
    }

    public void dfs(int[] candidates, int target, int start) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i=start;i<candidates.length;i++) {
            if (candidates[i] > target) {
                break;
            }
            path.add(candidates[i]);
            dfs(candidates,target-candidates[i], i);
            path.remove(path.size()-1);
        }
    }

}
