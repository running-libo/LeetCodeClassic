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

    /**
     * 思路：二分查找的下界为 000，上界可以粗略地设定为 xxx。在二分查找的每一步中，
     * 我们只需要比较中间元素 mid\textit{mid}mid 的平方与 xxx 的大小关系，并通过比较的结果调整上下界的范围。
     */
    public int mySqrt(int x) {
        int left = 0, right = x; //左右两个返回
        int ans = -1; //最大上界的数
        while(left <= right) {
            int middle = left + (right-left)/2;  //中位数
            if ((long)middle*middle <= x) {
                ans = middle;
                left = middle+1;
            } else {
                right = middle-1;
            }
        }
        return ans;
    }

//       ###### [整数反转](https://leetcode.cn/problems/reverse-integer/)

    /**
     * 需判断整数溢出
     * @param x
     * @return
     */
    public int reverse(int x) {
        int newNum = 0; //每次的新数
        int restNum = x; //每次剩下的数
        int lastNew = 0; //上次的新数
        while(restNum != 0) {
            lastNew = newNum;
            newNum = newNum * 10 + restNum % 10;
            restNum /= 10;
            //判断整数溢出，如果变换的新数的前几位数与变换前不一样，则溢出了，例如 3 变成了 3x，如果  3!= 3x/10 ，则发生了溢出
            if (lastNew != newNum /10) {
                return 0;
            }
        }
        return newNum;
    }

//    ##### [二进制求和](https://leetcode.cn/problems/add-binary/)

    /**
     * 思路，将两个字符串的各个字符按位相加，需要逢2进1，设置ans为进位
     * 需要从末尾对齐相加，以十进制的计算法来做二进制加法
     *    1010
     *  + 1011
     *   10101
     */
    public String addBinary(String a, String b) {
        int n = Math.max(a.length(), b.length()); //最大一个的长度
        int carry = 0; //计算的进位
        StringBuilder sb = new StringBuilder();  //存储结果
        for (int i=0;i<n;i++) {
            int aNum = i<a.length() ? (a.charAt(a.length()-1-i) - '0') : 0; //从尾部开始找到当前a需要相加的值
            int bNum = i<b.length() ? (b.charAt(b.length()-1-i) - '0') : 0; //从尾部开始找到当前b需要相加的值
            int sum = aNum + bNum + carry;
            carry = sum / 2;
            sb.append(sum % 2);
        }

        if (carry != 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

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
