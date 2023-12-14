package com.example.leetcodeclassic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PractiseMore {

    //数组中连续的子数组的最大和，返回这个和的最大值


    public int getMaxTotal(int[] array) {
        int max = 0;
        int[] dp = new int[array.length];
        dp[0] = array[0];
        for (int i=0;i<array.length;i++) {
            dp[i] = array[i] + Math.max(dp[i-1], 0);
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    List<Integer> list = new ArrayList<>();

    public List<Integer> middleForEach(TreeNode root) {
        dfs(root);
        return list;
    }

    public void dfs(TreeNode root) {
        dfs(root.left);
        list.add(root.val);
        dfs(root.right);
    }


    public List<Integer> bfs(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.add(root);
        }
        while(!queue.isEmpty()) {
            int size = queue.size();
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
        }
        return list;
    }
}
