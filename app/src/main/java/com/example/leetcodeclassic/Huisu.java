package com.example.leetcodeclassic;

import java.util.ArrayList;
import java.util.List;

public class Huisu {

//            ###### [子集](https://leetcode.cn/problems/subsets/)
    class Solution {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();

        public List<List<Integer>> subsets(int[] nums) {
            dfs(nums, 0);
            return result;
        }

        public void dfs(int[] nums, int index) {
            if (index == nums.length) {
                //到最底一级分支了
                result.add(new ArrayList<Integer>(ans));
                return;
            }

            ans.add(nums[index]);
            dfs(nums, index+1); //继续向下一级搜索
            ans.remove(ans.size()-1);  //执行结束后需要对 ans 进行回溯
            dfs(nums, index+1);
        }
    }

//            ###### [全排列](https://leetcode.cn/problems/permutations/)
    class Solution2 {
        /**
         * 思路：
         * n个数全排列，从数学上想，一共有n!种排列方式，
         * 因为第一个位置有n个选择，第二个位置有(n-1)个选择，以此类推，所有一共有 n*(n-1)*(n-2)*2*1
         * 解题过程就是要模拟这个过程
         *
         * baseCase  处理完最后一个元素，把当前Array做一份copy添加到答案里
         * backtrack(array, index)
         * 从i= [index, n]之间依次选取所有元素进行搜索
         * 每次将i和index的元素交换 -> next_state
         * dfs(array, index+1)
         * 将i和index元素交换回来 -> restore state (回溯状态)
         * @param nums
         * @return
         */
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();  //最终返回结果
            dfs(result, nums, 0);
            return result;
        }

        /**
         * 回溯算法过程
         * @param result
         * @param nums
         * @param index
         */
        private void dfs(List<List<Integer>> result, int[] nums, int index) {
            if (index >= nums.length) {
                //当前分支搜索完毕，将当前集合遍历，并存入总集合中
                List<Integer> ans = new ArrayList<>();
                for (int i=0;i<nums.length;i++) {
                    ans.add(nums[i]);
                }
                result.add(ans);
                return;
            }

            //搜索没有结束，将当前放的位置和剩下的数每一个进行交换，并且调用当前层级的dfs，然后再交换回来
            for (int i=index;i<nums.length;i++) {
                swap(nums, index, i);
                dfs(result, nums, index+1);
                swap(nums, i, index);
            }
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

//            ###### [组合总和](https://leetcode.cn/problems/combination-sum/)


//            ###### [括号生成](https://leetcode.cn/problems/generate-parentheses/)


    public static void main(java.lang.String[] args) {

    }
}
