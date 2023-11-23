package com.example.leetcodeclassic;

import java.util.ArrayList;

public class Lists {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

//            ###### [反转链表](https://leetcode-cn.com/problems/reverse-linked-list/) ★
    /**
     * 思路： 双指针迭代
     * 我们可以申请两个指针，第一个指针叫 pre，最初是指向 null 的。
     * 第二个指针 cur 指向 head，然后不断遍历 cur。
     * 每次迭代到 cur，都将 cur 的 next 指向 pre，然后 pre 和 cur 前进一位。
     * 都迭代完了(cur最后一次会被置为null)，pre 就是最后一个节点了。
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode preNode = null;
        ListNode curNode = head;
        while (curNode != null) {
            //核心就是4步
            //1.next往后移，2.cur.next指向pre
            //3.pre往后移，4.cur往后移
            //pre保持指向前一个节点，cur、next指向当前同一个节点
            ListNode temp = curNode.next;
            curNode.next = preNode;
            //preNode/curNode节点往后移动
            preNode = curNode;
            curNode = temp;
        }
        return preNode;
    }

//            ###### [反转链表 II](https://leetcode.cn/problems/reverse-linked-list-ii/) ★
    /**
     * 根据上图思路：
     * 1.需要快进到反转区间左位置
     * 2.使用变量记住区间前一个位置和第一个位置节点
     * 3.使用经典四步反转left到right区间的节点
     * 4.将反转后的链表与原链表进行拼接
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {

        ListNode pre = null, cur = head;
        //cur在pre的前一个位置
        for (int i=0;i<left-1;i++) {
            pre = cur;
            cur = cur.next;
        }
        //使用变量记住区间前一个位置和第一个位置节点
        ListNode pre2 = pre;
        ListNode cur2 = cur;
        for (int i=left;i<=right;i++) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        //将反转后的链表与原链表进行拼接

        //有一种情况，从第一个位置就开始反转，那么pre开始为null,pre2也是为null的，需要判空一下，头就指向pre，即反转区间的头节点
        if (pre2 != null) {
            pre2.next = pre;
        } else {
            head = pre;
        }
        cur2.next = cur;
        return head;
    }

//            ###### [环形链表](https://leetcode-cn.com/problems/linked-list-cycle/) ★
    //快慢指针：快指针单位时间移动2步，慢指针单位时间移动1步
    //如果某个时候，快指针的next为null了，那么表示链表有尾节点
    //否则，去判断，一定在某一时刻，快指针会超过慢指针N圈，站在同一个位置
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {
            //要么有环，要么没有环，没有环，则最终快速指针会走完，如果没有环，
            //则快速指针一定会在某个时刻追上慢速指针，即指向同一个节点
            //这里要找到一个循环结束点，并返回true，否则会死循环

            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) {
                return true;
            }
        }
        return false;
    }

//            ###### [环形链表 II](https://leetcode.cn/problems/linked-list-cycle-ii/)
    public ListNode detectCycle(ListNode head) {
        //题解：这个题的目标，是找到环的入环点，
        // 根据上图，首先根据公式推导出  a = (n-1)(b+c) + c，通过这个公式，
        // 再用两个指针相同速度走，相遇点就是要返回的入环节点
        //那么第一步：定义一个快指针和慢指针，快指针每步走2个，慢指针每步走一个，在有环的情况下快指针一定会追上慢指针相遇
        //得到相交点，构成这幅图的形式

        ListNode slow = head, fast = head;
        while(fast != null) {
            //让慢指针每次走1步，快指针每次走2步
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            if (slow == fast) {
                //再构造两个慢指针走到入环节点
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

//            ###### [回文链表](https://leetcode-cn.com/problems/palindrome-linked-list/) ★
    public boolean isPalindrome(ListNode head) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        ListNode cur = head;
        while(cur != null) {
            arrayList.add(cur.val);
            cur = cur.next;
        }
        int start = 0, end = arrayList.size()-1;
        while(start < end) {
            //将对应位置的数进行对比，只要不相等，就不是回文链表，直到start==end
            if (arrayList.get(start) != arrayList.get(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

//            ###### [合并两个有序链表](https://leetcode-cn.com/problems/merge-two-sorted-lists/) ★
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode curNode = new ListNode(-1);
        ListNode head = curNode;
        while(l1!=null && l2!=null) {
            if(l1.val <= l2.val) {
                curNode.next = l1;
                l1 = l1.next;
            } else {
                curNode.next = l2;
                l2 = l2.next;
            }
            curNode = curNode.next;
        }
        curNode.next = l1==null?l2:l1;
        return head.next;
    }

//            ######[删除链表的倒数第 N 个结点](https://leetcode.cn/problems/remove-nth-node-from-end-of-list/)


//            ###### [相交链表](https://leetcode-cn.com/problems/intersection-of-two-linked-lists/)
//创建两个指针 pApA 和 pBpB，分别初始化为链表 A 和 B 的头结点。然后让它们向后逐结点遍历。
//当 pApA 到达链表的尾部时，将它重定位到链表 B 的头结点 (你没看错，就是链表 B); 类似的，当 pBpB 到达链表的尾部时，将它重定位到链表 A 的头结点。
//若在某一时刻 pApA 和 pBpB 相遇，则 pApA/pBpB 为相交结点。
//为什么这种方法是可行的，因为当A、B都走到公共节点的时候，正好走了自己的路，又走了对方的路，两个指针走的距离相等
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode n1 = headA;
        ListNode n2 = headB;
        while(n1 != n2) {
            if(n1 != null) {
                //有后面节点，继续往后移动
                n1 = n1.next;
            } else {
                n1 = headB;
            }

            if(n2 != null) {
                n2 = n2.next;
            } else {
                n2 = headA;
            }
        }

        //当n1==n2了，即各自走了一圈半到了第一个相交节点，返回n1或n2都可以
        return n1;
    }

//            ######[两数相加](https://leetcode.cn/problems/add-two-numbers/)


//            ######[两两交换链表中的节点](https://leetcode.cn/problems/swap-nodes-in-pairs/)


//            ######[旋转链表](https://leetcode.cn/problems/rotate-list/)

    public static void main(java.lang.String[] args) {

    }
}
