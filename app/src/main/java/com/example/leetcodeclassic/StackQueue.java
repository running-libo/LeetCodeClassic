package com.example.leetcodeclassic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackQueue {

//            ###### [用两个栈实现队列](https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/)
    /**
     * 实现思路：
     * 创建2个栈，栈1只负责入栈，栈2只负责出栈
     * 存入几个元素到栈1后，如果此时栈2为空，则将栈1中的元素转移到栈2，从栈2出栈
     * 如果此时栈2中不为空，则直接从栈2出栈
     */
    class CQueue {
        Stack<Integer> stack1;  //stack1只负责入栈
        Stack<Integer> stack2;  //stack2只负责出栈

        public CQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        /**
         * 入列
         * @param value
         */
        public void appendTail(int value) {
            stack1.push(value);
        }

        /**
         * 出列并返回当前出列元素
         * @return
         */
        public int deleteHead() {
            if (!stack2.isEmpty()) {
                return stack2.pop();
            } else {
                //将stack1中所有元素转移到stack2
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }

                //若队列中没有元素，deleteHead 操作返回 -1
                return stack2.isEmpty() ? -1 : stack2.pop();
            }
        }

    }

//            ###### [用队列实现栈](https://leetcode.cn/problems/implement-stack-using-queues/)
    /**
     * 用一个队列实现栈
     * 入栈操作时，首先获得入栈前的元素个数 nnn，然后将元素入队到队列，
     * 再将队列中的前 nnn 个元素（即除了新入栈的元素之外的全部元素）依次出队并入队到队列，此时队列的前端的元素即为新入栈的元素
     */
    class MyStack {
        Queue<Integer> queue;

        public MyStack() {
            queue = new LinkedList<>();
        }

        public void push(int x) {
            int n = queue.size();
            queue.offer(x);
            for (int i=0;i<n;i++) { //将x入队之后，将x之前的元素重新放入x之后，确保x在最前面
                queue.offer(queue.poll());
            }
        }

        public int pop() {
            return queue.poll();
        }

        public int top() {
            return queue.peek();
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }


//            ###### [有效的括号](https://leetcode-cn.com/problems/valid-parentheses/)

    //思路：1.在hashmap中存入左右括号对应分别作为键值对
    //遍历字符，如果为左括号，就在栈中存入右括号，看后面的字符是否有该右括号来消掉
    //3.用stack来存，如果存入的符号是左括号，没有问题可以直接存，如果存入了右括号，则栈顶一个符号必须为对应的左括号
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack();
        HashMap<Character,Character> map = new HashMap();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        for(Character ch : s.toCharArray()) {
            if(map.containsKey(ch)) { //当前字符为左括号
                stack.push(ch);
            } else {  //当前字符为右括号，那栈中最顶部必然要与之相同才行
                if(stack.isEmpty() || map.get(stack.pop()) != ch) {
                    return false;
                }
            }
        }

        //最后看stack还有没有没有正确关闭括号
        return stack.isEmpty();
    }

//            ###### [接雨水](https://leetcode.cn/problems/trapping-rain-water/)
    /**
     * 思路:
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height == null || height.length == 1) {
            return 0;
        }
        int water = 0;
        int leftMax = height[0];
        int rightMax = height[height.length-1];
        int p1 = 1, p2 = height.length-2;  //第一个和最后一个柱子接雨水无效
        while(p1<=p2) {
            if (leftMax <= rightMax) {
                water += Math.max(0, leftMax-height[p1]); //判断左边最大值和当前值的差，如果当前值大于左边最大值
                //那么当前柱子无法存水
                leftMax = Math.max(leftMax, height[p1]);
                p1++;
            } else {
                water += Math.max(0, rightMax-height[p2]); //判断左边最大值和当前值的差，如果当前值大于左边最大值
                //那么当前柱子无法存水
                rightMax = Math.max(rightMax, height[p2]);
                p2--;
            }
        }
        return water;
    }

    public static void main(java.lang.String[] args) {

    }
}
