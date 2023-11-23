package com.example.leetcodeclassic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Tree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

//            ###### [二叉树的最大深度](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/)
    /**
     * 节点为空时说明高度为 0，所以返回 0；节点不为空时则分别求左右子树的高度的最大值，同时加1表示当前节点的高度，返回该数值
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else { //获取左子树的最大深度和右子树最大深度对比，选取大的，再+1，为当前节点的最大深度
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }
    }

//            ###### [二叉树的最小深度](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/)
    public int minDepth(TreeNode root) {
        //思路：得到树的最底层的叶子节点，即左右节点都为null，设置深度为1，从下往上找
        //当前节点深度为取左右节点的深度较小值，+1，即当前节点最小深度作为函数返回值，该函数为递归调用函数
        if(root == null) {
            return 0;
        }

        if(root.left == null && root.right == null) {
            return 1;
        }

        int mindepth = Integer.MAX_VALUE;
        if(root.left != null) {
            //左子节点不为空，得到左子节点的深度
            mindepth = Math.min(minDepth(root.left), mindepth);
        }
        if(root.right != null) {
            //右子节点不为空，得到右子节点的深度
            mindepth = Math.min(minDepth(root.right), mindepth);
        }
        return mindepth+1;
    }

//            ###### [翻转二叉树](https://leetcode-cn.com/problems/invert-binary-tree/)
    /**
     * 思路：
     * 观察可得翻转二叉树就是将所有节点的左右子节点调换顺序，需要使用递归
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        //将左右节点调换顺序，然后分别将左、右节点递归调用翻转的方法
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;
        invertTree(root.left);
        invertTree(root.right);

        return root;  //最终需要返回根节点
    }

//            ###### [合并二叉树](https://leetcode-cn.com/problems/merge-two-binary-trees/)
    /**
     * 思路：
     * 将t2中的值往t1中合并，如果t1中节点为空，则返回t2中的值，否则返回2个节点中值的和
     * 然后递归调用方法，将t1左节点和t2左节点合并，将t1右节点和t2右节点合并
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null || t2 == null) {
            return t1 == null ? t2 : t1;
        }

        t1.val = t1.val + t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

//            ###### [二叉树的前序遍历](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/)
    class Solution {
        List<Integer> list = new ArrayList();

        public List<Integer> preorderTraversal(TreeNode root) {
            //思路：首先想到二叉树的操作，采用递归法：
            //如果当前根节点不为null，加入到集合中，然后对左右子节点先后调用递归，将左节点添加完，就添加右节点
            //直到碰到节点为null，则该往下节点递归结束

            preorder(root);
            return list;
        }

        public void preorder(TreeNode root) {
            if(root == null) {
                return;
            }

            list.add(root.val);
            //节点不为null，则继续对左右节点递归调用添加、判空等操作
            preorder(root.left);
            preorder(root.right);
        }

    }

//            ###### [二叉树的层序遍历](https://leetcode.cn/problems/binary-tree-level-order-traversal/)
    /**
     * 思路：用队列存储每一层的数字，然后用变量记录当前层级元素的个数
     * 创建当前层级集合，遍历当前层级的size，弹出当列当前数添加到集合，判断当前节点左右节点是否为空，不为空加入到当前队列中
     * 注：每一层取当前层是从队列取前面数，存下一层是放入队列尾部，所以不会有冲突
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.add(root);
        }
        //队列添加过元素，就将当前队列里的数添加到当前层级集合，并且将每个元素的左右子节点加入到下一层级的队列中
        while(!queue.isEmpty()) {
            //如果当前队列不为空，将当前队列的当前层弹出队列，存储数组中
            int size = queue.size();
            List<Integer> curLevel = new ArrayList<>();
            //遍历size，确定当前层需要处理几个数
            for (int i=0;i<size;i++) {
                //当前层级可以继续弹出
                TreeNode node = queue.poll();
                curLevel.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            //当前层处理完成
            res.add(curLevel);
        }
        return res;
    }

    public static void main(java.lang.String[] args) {

    }
}
