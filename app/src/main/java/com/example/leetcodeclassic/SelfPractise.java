package com.example.leetcodeclassic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class SelfPractise {

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

        if (digits[0] == 0) {
            //全部都进了一位 如 999 成了 000
            int[] res = new int[digits.length+1];
            res[0] = 1; //变为  1000
            return res;
        }
        return digits;
    }

    public int removeDuplicates(int[] nums) {
        Arrays.sort(nums);
        int pos = 1;
        for (int i=1;i<nums.length;i++) {
            if (nums[i] == nums[i-1]) {
                continue;
            } else {
                nums[pos] = nums[i];
                pos++;
            }
        }
        return pos;
    }

    public int search(int[] nums, int target) {
        int start = 0, end = nums.length-1;
        while(start<=end) {
            int middle = (start+end)/2;
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] < target) {
                start = middle+1;
            } else if (nums[middle] > target) {
                end = middle-1;
            }
        }
        return -1;
    }

    public int findLengthOfLCIS(int[] nums) {
        if (nums.length <=1) {
            return nums.length;
        }
        int start = 0;
        int max = 0;
        for (int i=1;i<nums.length;i++) {
            if (nums[i] <= nums[i-1]) {
                start = i;
            }
            max = Math.max(max, i-start+1);
        }
        return max;
    }

    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }

    public void merge(int[] A, int m, int[] B, int n) {
        for (int i=0;i<n;i++) {
            A[m+i] = B[i];
        }
        Arrays.sort(A);
    }

    public void reverseString(char[] s) {
        int start = 0, end = s.length-1;
        while(start<end) {
            Character temp = s[start];
            s[start] = s[end];
            s[end] = temp;

            start++;
            end--;
        }
    }

    public int lengthOfLongestSubstring(String s) {
        int start;
        int maxLen = 0;
        Set<Character> set = new HashSet<>();
        for (int i=0;i<s.length();i++) {
            start = i;
            while(start<s.length() && set.add(s.charAt(start))) {
                start++;
            }

            maxLen = Math.max(maxLen, set.size());
            set.clear();
        }
        return maxLen;
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

    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                return true;
            }
        }

        return false;
    }

    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while(cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }

        int start = 0, end = list.size()-1;
        while(start < end) {
            if (list.get(start) != list.get(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(-1);
        ListNode head = node;
        while(l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                node.next = l1;
                node = node.next;
                l1 = l1.next;
            } else {
                node.next = l2;
                node = node.next;
                l2 = l2.next;
            }
        }

        node.next = l1==null ? l2 : l1;
        return head.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        ListNode cur = head;
        dummy.next = head;
        for (int i=0;i<n;i++) {
            cur = cur.next;
        }
        while(cur!=null) {
            cur = cur.next;
            pre = pre.next;
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
        int ans = 0;
        ListNode head = null;
        ListNode tail = null;
        while(l1 != null || l2 != null) {
            int n1 = l1!=null? l1.val : 0;
            int n2 = l2!=null? l2.val : 0;
            int sum = n1 + n2 + ans;

            if (head == null) {
                head = tail = new ListNode(sum%10);
            } else {
                tail.next = new ListNode(sum%10);
                tail  = tail.next;
            }
            ans = sum / 10;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (ans != 0) {
            tail.next = new ListNode(ans);
        }
        return head;
    }

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        while(cur.next != null && cur.next.next != null) {
            ListNode temp1 = cur.next;
            ListNode temp2 = cur.next.next.next;
            cur.next.next.next = temp1;
            cur.next = cur.next.next;
            temp1.next = temp2;
            cur = cur.next.next;
        }
        return dummy.next;
    }

    public int singleNumber(int[] nums) {
        int sum = 0;
        for (int i=0;i<nums.length;i++) {
            sum ^= nums[i];
        }
        return sum;
    }

    public int getDecimalValue(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        while(cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }
        int sum = 0;
        int pos = 0;
        while(!stack.isEmpty()) {
            sum = sum + (stack.pop() << pos);
            pos++;
        }
        return sum;
    }

    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put('{', '}');
        map.put('[', ']');
        map.put('(', ')');
        Stack<Character> stack = new Stack<>(); //放左括号
        for (int i=0;i<s.length();i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {
                stack.push(ch);
            } else {
                if (stack.isEmpty() || ch != map.get(stack.pop())) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
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
        int minDeep = Integer.MAX_VALUE;
        if (root.left != null) {
            minDeep = Math.min(minDeep, minDepth(root.left));
        }
        if (root.right != null) {
            minDeep = Math.min(minDeep, minDepth(root.right));
        }
        return minDeep+1;
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
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        TreeNode merged = new TreeNode(root1.val + root2.val);
        merged.left = mergeTrees(root1.left, root2.left);
        merged.right = mergeTrees(root1.right, root2.right);
        return merged;
    }

    List<Integer> orders = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        preOrder(root);
        return orders;
    }

    public void preOrder(TreeNode root) {
        if (root != null) {
            orders.add(root.val);
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    Queue<TreeNode> queue = new ArrayDeque();
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root != null) {
            queue.add(root);
        }
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i=0;i<size;i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(list);
        }
        return res;
    }

    public boolean isPalindrome(String s) {
        int start = 0, end = s.length()-1;
        while(start < end) {
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
        Arrays.sort(nums);

        int start = -1, end = -1;
        for(int i=0;i<nums.length;i++) {
            if (nums[i] == target) {
                start = i;
                break;
            }
        }
        for(int i=nums.length-1;i>=0;i--) {
            if (nums[i] == target) {
                end = i;
                break;
            }
        }
        return new int[] {start, end};
    }

    List<List<Integer>> res2 = new ArrayList<>();
    public List<List<Integer>> threeSum(int[] nums) {
        int left=0,right=0;
        Arrays.sort(nums);
        for (int i=0;i<nums.length;i++) {
            if (i>0 && nums[i] == nums[i-1]) {
                continue;
            }

            left = i+1;
            right = nums.length-1;

            while(left<right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res2.add(Arrays.asList(nums[i], nums[left], nums[right]));
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
        return res2;
    }

    public void sortColors(int[] nums) {
        int start=0,end=nums.length-1;
        for (int i=0;i<nums.length;i++) {
            if (nums[i] == 0) {
                temp(nums, i, start);
                start++;
            }
        }
        for (int i=nums.length-1;i>=start;i--) {
            if (nums[i] == 2) {
                temp(nums, i, end);
                end--;
            }
        }
    }

    public void temp(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public boolean isPalindrome(int x) {
        int newNum = 0, restNum = x;
        while(restNum > 0) {
            newNum = newNum*10 + restNum%10;
            restNum /= 10;
        }
        return newNum == x;
    }

    public int mySqrt(int x) {
        int left=0,right=x;
        int ans = -1;
        while(left<=right) {
            int middle = left+(right-left)/2;
            if ((long)middle*middle <= x) {
                ans = middle;
                left = middle+1;
            } else {
                right = middle-1;
            }
        }
        return ans;
    }

    public int iceBreakingGame(int num, int target) {
        List<Integer> list = new ArrayList<>();
        for (int i=0;i<num;i++) {
            list.add(i);
        }

        int index = 0;
        while(list.size() > 1) {
            int move = (target-1)%list.size();
            if (index + move < list.size()) {
                index += move;
                list.remove(index);
                if (index == list.size()) {
                    index = 0;
                }
            } else {
                index = index+move-list.size();
                list.remove(index);
            }
        }
        return list.get(0);
    }

    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int a = 0,b = 1;
        int cur = 0;
        for (int i=2;i<=n;i++) {
            cur = a + b;
            a = b;
            b = cur;
        }
        return cur;
    }

    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i=1;i<nums.length;i++) {
            dp[i] = nums[i] + Math.max(dp[i-1], 0);
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }

    public int rob(int[] nums) {
        if (nums.length <= 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], dp[0]);
        for (int i=2;i<nums.length;i++) {
            dp[i] = Math.max(nums[i] + dp[i-2], dp[i-1]);
        }
        return dp[nums.length-1];
    }

    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int min = prices[0];
        for (int i=1;i<prices.length;i++) {
            if (prices[i] < min) {
                min = prices[i];
            }
            maxProfit = Math.max(maxProfit, prices[i]-min);
        }
        return maxProfit;
    }

    public int maxProfit2(int[] prices) {
        int maxProfit = 0;
        for (int i=prices.length-1;i>0;i--) {
            if (prices[i] > prices[i-1]) {
                maxProfit = maxProfit + (prices[i]-prices[i-1]);
            }
        }
        return maxProfit;
    }

    public boolean canJump(int[] nums) {
        int rightMost = nums[0];
        for (int i=0;i<nums.length;i++) {
            if (i<=rightMost) {
                rightMost = Math.max(rightMost, i+nums[i]);
            }

            if (rightMost >= nums.length-1) {
                return true;
            }
        }
        return false;
    }

    List<List<Integer>> res3 = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates, target, 0);
        return res3;
    }

    public void dfs(int[] candidates, int target, int index) {
        if (target == 0) {
            res3.add(new ArrayList<>(path));
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

    public List<List<Integer>> subsets(int[] nums) {
        res3.add(new ArrayList<>());
        dfs(nums, 0);
        return res3;
    }

    public void dfs(int[] nums, int index) {
        if(path.size() == nums.length) {
            res3.add(new ArrayList<>(path));
            return;
        }
        for (int i=index;i<nums.length;i++) {
            path.add(nums[i]);
            res3.add(new ArrayList<>(path));
            dfs(nums, i+1);
            path.remove(path.size()-1);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        dfs(nums);
        return res3;
    }

    public void dfs(int[] nums) {
        if (path.size() == nums.length) {
            res3.add(new ArrayList<>(path));
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

}
