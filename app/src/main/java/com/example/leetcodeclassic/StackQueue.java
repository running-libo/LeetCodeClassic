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
     * 用两个队列实现栈
     * 这里需要实现一个队列元素的倒序，每次从queue2入列，从queue1中取出
     */
    class MyStack {
        Queue<Integer> queue1;
        Queue<Integer> queue2;

        /** Initialize your data structure here. */
        public MyStack() {
            queue1 = new LinkedList();  //入队列
            queue2 = new LinkedList();  //出队列
        }

        /** Push element x onto stack. */
        public void push(int x) {
            queue2.offer(x);
            while(!queue1.isEmpty()) { //将queue1中的元素移入queue2中，实现现有元素的倒序
                queue2.offer(queue1.poll());
            }
            //交换数据，所以每次添加完数据都在queue1队列中
            Queue<Integer> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return queue1.poll();
        }

        /** Get the top element. */
        public int top() {
            return queue1.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue1.isEmpty();
        }
    }

//            ###### [有效的括号](https://leetcode-cn.com/problems/valid-parentheses/)
    public static boolean isValid(String s) {
        //思路：1.在hashmap中存入左右括号对应分别作为键值对
        //遍历字符，如果为左括号，就在栈中存入右括号，看后面的字符是否有该右括号来消掉
        //3.用stack来存，先存就后取，后存就要先取
        Stack<Character> stack = new Stack();
        HashMap<Character,Character> map = new HashMap();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        for(Character ch : s.toCharArray()) {
            if(map.containsKey(ch)) { //当前字符为左括号
                stack.push(map.get(ch));
            } else {  //当前字符为右括号，那栈中最顶部必然要与之相同才行
                if(stack.isEmpty() || stack.pop() != ch) {
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
