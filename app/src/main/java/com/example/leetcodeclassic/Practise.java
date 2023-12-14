package com.example.leetcodeclassic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

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

    public List<List<Integer>> subsets(int[] nums) {
        result.add(new ArrayList<>());
        dfs(nums, 0);
        return result;
    }

    public void dfs(int[] nums, int start) {
        if (path.size() == nums.length) {
            return;
        }
        for (int i=start;i<nums.length;i++) {
            path.add(nums[i]);
            result.add(new ArrayList<>(path));
            dfs(nums, i+1);
            path.remove(path.size()-1);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        dfs(nums);
        return result;
    }

    public void dfs(int[] nums) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
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

    List<String> list = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        dfs(new StringBuilder(), n, 0, 0);
        return list;
    }

    public void dfs(StringBuilder stringBuilder, int n, int left, int right) {
        if (left+right == n*2) {
            list.add(stringBuilder.toString());
            return;
        }

        if (left < n) {
            stringBuilder.append('(');
            dfs(stringBuilder, n, left+1, right);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }
        if (right < left) {
            stringBuilder.append(')');
            dfs(stringBuilder, n, left, right+1);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        preOrder(root);
        return path;
    }

    private void preOrder(TreeNode root) {
        if (root != null) {
            path.add(root.val);

            if (root.left != null) {
                preOrder(root.left);
            }
            if (root.right != null) {
                preOrder(root.right);
            }
        }
    }

    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while(cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode cur = head;
        ListNode pre = null;
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

    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (slow == fast) {
                ListNode cur = head;
                while(cur != slow) {
                    cur = cur.next;
                    slow = slow.next;
                }

                return cur;
            }
        }
        return null;
    }

    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while(cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }

        int start=0, end = list.size()-1;
        while(start<end) {
            if (list.get(start) != list.get(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public int getDecimalValue(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        while(cur != null) {
            stack.add(cur.val);
            cur = cur.next;
        }

        int pos = 0;
        int sum = 0;
        while(!stack.isEmpty()) {
            sum = sum + (stack.pop() << pos);
            pos++;
        }
        return sum;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while(l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        cur.next = l1 == null ? l2 : l1;
        return head.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        for (int i=0;i<n;i++) {
            cur = cur.next;
        }
        while(cur != null) {
            pre = pre.next;
            cur = cur.next;
        }
        pre.next = pre.next.next;
        return dummy.next;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;
        while(curA != curB) {
            if (curA != null) {
                curA = curA.next;
            } else {
                curA = headB;
            }
            if (curB != null) {
                curB = curB.next;
            } else {
                curB = headA;
            }
        }
        return curA;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode head = null;
        ListNode tail = null;
        while(l1 != null || l2 != null) {
            int n1 = l1==null ? 0 : l1.val;
            int n2 = l2==null ? 0 : l2.val;

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

            carry = sum/10;
        }

        if (carry != 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        dummy.next = head;

        while(cur.next != null && cur.next.next != null) {
            ListNode temp1 = cur.next;
            ListNode temp2 = cur.next.next.next;
            cur.next = cur.next.next;
            cur.next.next = temp1;
            temp1.next = temp2;

            cur = cur.next.next;
        }

        return dummy.next;
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        int lenth = 0;
        while(cur.next != null) {
            cur = cur.next;
            lenth++;
        }
        lenth += 1;
        cur.next = head;
        int move = lenth - k%lenth;
        for (int i=0;i<move;i++) {
            cur = cur.next;
        }
        ListNode newHead = cur.next;
        cur.next = null;
        return newHead;
    }

    class CQueue {
        Stack<Integer> stack1;
        Stack<Integer> stack2;

        public CQueue() {
            stack1 = new Stack();
            stack2 = new Stack();
        }

        public void appendTail(int value) {
            stack1.add(value);
        }

        public int deleteHead() {
            if (!stack2.isEmpty()) {
                return stack2.pop();
            } else {
                while(!stack1.isEmpty()) {
                    stack2.add(stack1.pop());
                }
                return stack2.isEmpty() ? -1 : stack2.pop();
            }
        }
    }

    class MyStack {
        Queue<Integer> queue;
        public MyStack() {
            queue = new LinkedList<>();
        }

        public void push(int x) {
            int size = queue.size();
            queue.offer(x);
            for (int i=0;i<size;i++) {
                queue.add(queue.poll());
            }
        }

        public int pop() {
            return empty() ? 0 : queue.poll();
        }

        public int top() {
            return queue.peek();
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }

    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        Stack<Character> stack = new Stack<>();
        map.put('(',')');
        map.put('[',']');
        map.put('{','}');
        for (int i=0;i<s.length();i++) {
            Character ch = s.charAt(i);
            if (map.containsKey(ch)) {
                stack.add(ch);
            } else {
                if (!stack.isEmpty() && map.get(stack.pop()) == ch) {
                    continue;
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public boolean isPalindrome(String s) {
        int start = 0,end = s.length()-1;
        while(start<end) {
            while(start < end && !Character.isLetterOrDigit(s.charAt(start))) {
                start++;
            }
            while(start < end && !Character.isLetterOrDigit(s.charAt(end))) {
                end--;
            }
            if (Character.toLowerCase(s.charAt(start)) == Character.toLowerCase(s.charAt(end))) {
                start++;
                end--;
            } else {
                return false;
            }
        }
        return true;
    }

    public int[] searchRange(int[] nums, int target) {
        int start = -1, end = -1;
        int lenth = nums.length;
        for (int i=0;i<lenth;i++) {
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

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int left = 0, right = 0;
        for (int i=0;i<nums.length;i++) {
            if (i>0 && nums[i] == nums[i-1]) {
                continue;
            }

            left = i+1;
            right = nums.length-1;
            while(left<right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
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
        return res;
    }

    public void sortColors(int[] nums) {
        int start = 0, end = nums.length-1;
        for (int i=0;i<nums.length;i++) {
            if (nums[i] == 0) {
                swap(nums, i, start);
                start++;
            }
        }
        for (int i=nums.length-1;i>=start;i--) {
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



}
