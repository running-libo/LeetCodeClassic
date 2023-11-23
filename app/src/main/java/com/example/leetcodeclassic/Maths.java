package com.example.leetcodeclassic;

import java.util.ArrayList;

public class Maths {

//            ###### [回文数](https://leetcode-cn.com/problems/palindrome-number/)
    public boolean isPalindrome(int x) {
        //思路：首先，排除一定的请求，如果x是负数
        // 循环将数字依次截取，将取出的数字倒序排列每次成为新的数，倒序排完该数后，与原数相比较看是否相等

        if (x < 0) {
            return false;
        }

        int restNum = x;
        int newNum = 0;
        while (restNum>0) {
            newNum = newNum*10 + restNum%10;  //用此法将新取出的数排到末尾
            restNum /= 10; //原数再去低位
        }

        return newNum == x;
    }

//            ###### [x 的平方根](https://leetcode.cn/problems/sqrtx/)


//            ###### [约瑟夫环](https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/) ★
    /**
     * 思路：用一个容器arrayList数据结构装数据，每m个位置后，将arrayList的该下标数删除
     */
    public int lastRemaining(int n, int m) {
        ArrayList<Integer> list = new ArrayList();
        for (int i=0;i<n;i++) {
            list.add(i);
        }
        int index = 0;
        while (list.size() > 1) {
            //还没有删除到剩余最后一个数
            int move = (m-1)%list.size();  //move为需要走的步数
            if (index+move < list.size()) {
                //当前次移动的位置没有超出一圈
                index += move;
                list.remove(index);
                if (index == list.size()) {
                    //如果当前删除的位置在集合的最后一个，则需要移动到第一个位置
                    index = 0;
                }
            } else {
                //当前次移动的位置超出一圈，则需要进入下一圈的对应位置
                index = (index+move-list.size());
                list.remove(index);
            }
        }
        return list.get(0);
    }

    public static void main(java.lang.String[] args) {

    }
}
