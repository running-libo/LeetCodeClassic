package com.example.leetcodeclassic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Huisu {

    //解法参考自：https://www.bilibili.com/video/BV1854y1m7WR/?spm_id_from=333.337.search-card.all.click&vd_source=40c24e77b23dc2e50de2b7c87c6fed59

//            ###### [组合总和](https://leetcode.cn/problems/combination-sum/)

    /**
     * dfs的 return条件是 target == 0，找到条件也是 target == 0
     */
    class Solution {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            Arrays.sort(candidates); //先排序，用于可以剪枝操作
            dfs(candidates, target, 0);
            return result;
        }

        /**
         * 深度优先回溯算法
         * @param candidates 候选集合
         * @param target 剩余需要组合的值
         * @param start 在候选集合中的位置
         */
        public void dfs(int[] candidates, int target, int start) {
            if (target == 0) {
                //找到这个组合，当组合放入结果，然后结束这个层级往下
                result.add(new ArrayList<>(path));
                return;
            }

            //没有找到，则继续从当前候选序列里面取出，候选序列的起始为candidates的idx下标
            for (int i=start;i<candidates.length;i++) {
                //剪枝操作：如果当前canditates[i]大于target了，则这个分支的选取都结束了
                if (candidates[i] > target) {
                    break;
                }

                //先试着把当前数放入组合
                path.add(candidates[i]);
                //继续从i开始选数，因为数可以取重复的;然后转移状态，tagert要为减去candidates[i]的新值；继续dfs往下一层搜索
                dfs(candidates, target-candidates[i], i);
                path.remove(path.size()-1); //回溯：删除最新添加的数，走for循环的下一个i，即走并行的下一个分支
            }
        }
    }

//            ###### [子集](https://leetcode.cn/problems/subsets/)
    /**
     * dfs的 return条件是 找的序号等于了数组长度，找到条件是 只要添加了新元素就是新的结果
     */
    class Solution1 {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        public List<List<Integer>> subsets(int[] nums) {
            result.add(new ArrayList<>()); //空集也是子集
            dfs(nums, 0);
            return result;
        }

        public void dfs(int[] nums, int start) {
            if (start >= nums.length) {
                return;
            }

            for (int i=start;i<nums.length;i++) {
                path.add(nums[i]);
                //注意： 在子集里，只要path组合新增了，就是一个新的子集，就需要添加到result里面去
                result.add(new ArrayList<>(path));
                dfs(nums, i+1); //往下层走，i需要+1，因为不能元素不能重复
                path.remove(path.size()-1); //回溯回到上层分支
            }
        }
    }

//            ###### [全排列](https://leetcode.cn/problems/permutations/)
    /**
     * dfs的 return条件是 path.size == nums.leng，即所有元素都找完一遍了；找到条件也是 path.size == nums.leng
     */
    class Solution2 {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        public List<List<Integer>> permute(int[] nums) {
            dfs(nums);
            return result;
        }

        public void dfs(int[] nums) {
            if (path.size() == nums.length) { //结束条件是 搜索长度满了，该全排列结束
                result.add(new ArrayList<>(path));
                return;
            }

            for (int i=0;i<nums.length;i++) {
                //去重
                if (path.contains(nums[i])) {
                    continue;
                }

                path.add(nums[i]);
                dfs(nums);
                path.remove(path.size()-1);
            }
        }
    }

//    ##### [括号生成](https://leetcode.cn/problems/generate-parentheses/)

    /**
     * 找能生成n对有效括号的所有组合
     * 思路：
     */
    class Solution3 {
        List<String> result = new ArrayList<>();
        public List<String> generateParenthesis(int n) {
            dfs(new StringBuilder(), 0, 0, n);
            return result;
        }

        /**
         * 思路：如果左括号数量还 < n，我们可以放一个左括号。如果右括号数量小于左括号的数量，我们可以放一个右括号。
         * 如果字符串长度等于 2*n，则组合添加完成
         * @param cur 当前字符串
         * @param left 当前左括号数
         * @param right 当前右括号数
         */
        public void dfs(StringBuilder cur, int left, int right, int n) {
            //如果字符串长度等于 2*n，则组合添加完成
            if (cur.length() == 2*n) {
                result.add(cur.toString());
                return;
            }

            //如果左括号数量还 < n
            if (left < n) {
                //我们可以放一个左括号
                cur.append('(');
                dfs(cur, left+1, right, n);
                cur.deleteCharAt(cur.length()-1);
            }

            //如果右括号数量小于左括号的数量，我们可以放一个右括号
            if (right < left) {
                cur.append(')');
                dfs(cur, left, right+1, n);
                cur.deleteCharAt(cur.length()-1);
            }
        }
    }

    public static void main(java.lang.String[] args) {

    }
}
