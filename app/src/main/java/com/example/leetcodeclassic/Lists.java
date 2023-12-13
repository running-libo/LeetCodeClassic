package com.example.leetcodeclassic;

import java.util.ArrayList;

public class Lists {

    public static class ListNode {
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

    /**
     * 思路：双指针法
     * 1.创建虚拟节点dummy，指向head;left节点指向dummy，用来占删除位置的前一个位置
     * 2.right节点指向head，将right节点移动n个位置，然后left和right继续同时往后移，直到right到达链表末尾
     * 3.将left的next指向next.next
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);  //创造一个虚拟节点
        dummy.next = head;
        ListNode left = dummy;  //left需要占删除位置的前一个位置
        ListNode right = head;
        for (int i=0;i<n;i++) { //拉开lfet 和 right节点 n个位置 ,参考链表的倒数第k个节点题
            right = right.next;
        }
        while(right != null) {  //让right节点走到链表末尾，left到达倒数第N个节点前一个节点
            right = right.next;
            left = left.next;
        }
        left.next = left.next.next;
        return dummy.next;
    }


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

    /**
     * 思路：模拟两个数字进行相加
     * 我们同时遍历两个链表，逐位计算它们的和，并与当前位置的进位值相加。
     * 具体而言，如果当前两个链表处相应位置的数字为 n1,n2，进位值为 carry，则它们的和为 n1+n2+carry；
     * 其中，,答案链表处相应位置的数字为 (n1+n2+carry) % 10，而新的进位值为 (n1+n2+carry)/10
     * 如果两个链表的长度不同，则可以认为长度短的链表的后面有若干个0 。
     * 此外，如果链表遍历结束后，有 carry>0，还需要在答案链表的后面附加一个节点，节点的值为 carry。
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null; //新链表的头、尾声明
        int carry = 0; //进位的值
        while(l1 != null || l2 != null) {
            //当两个链表不是都完了，程序就需继续按位相加
            int n1 = l1!=null ? l1.val : 0;
            int n2 = l2!=null ? l2.val : 0;
            int cur = n1+n2+carry; //当前位置相加

            if (head == null) {
                //第一个节点，创建头尾节点指向新节点
                tail = head = new ListNode(cur % 10);
            } else {
                //非第一个节点，让尾结点指向新的节点
                tail.next = new ListNode(cur % 10);
                tail = tail.next;
            }

            carry = cur / 10; //下一个的进位

            //l1 l2都往后移
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }

//            ######[两两交换链表中的节点](https://leetcode.cn/problems/swap-nodes-in-pairs/)

    /**
     * 操作示例： 1->2->3->4->5
     * 思路：创建一个虚拟节点指向head，cur指向虚拟节点。用temp保存1和3的位置供后面连接。让cur->2, 2->1, 1->3
     * 然后cur往后移动两位，即cur需要在反转的两个节点的前一个节点位置
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(0); //虚拟节点
        dummyHead.next = head; //虚拟节点指向head
        ListNode cur = dummyHead;
        ListNode temp1, temp2;
        while (cur.next != null && cur.next.next != null) {
            //循环结束条件：偶数个，cur.next为null结束;奇数个，cur.next.next为null结束
            temp1 = cur.next; //temp1占 1的位置
            temp2 = cur.next.next.next; //temp2占 3的位置
            cur.next = cur.next.next; //cur指向 2的位置
            cur.next.next = temp1; //2指向1的位置
            temp1.next = temp2; //1指向 3的位置

            cur = cur.next.next; //cur往后移动两位
        }
        return dummyHead.next;
    }

//            ######[旋转链表](https://leetcode.cn/problems/rotate-list/)
    /**
     * 思路：记给定链表的长度为 n，注意到当向右移动的次数 k≥n 时，我们仅需要向右移动 k mod n 次即可。因为每 n 次移动都会让链表变为原状。
     * 这样我们可以知道，新链表的最后一个节点为原链表的第 (n−1) - (k mod n) 个节点（从 0 开始计数）。
     *
     * 一条蛇咬着蛇尾，然后中间砍一刀变成了一条新的蛇
     * @param head
     */
    public ListNode rotateRight(ListNode head, int k) {
        //不合法的直接返回head不做操作
        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = head;
        int leng = 0;
        while(cur.next != null) {
            cur = cur.next;
            leng++;
        } //遍历到尾节点
        leng += 1;   //用的是 cur.next != null ，所以这里需要多 +1
        cur.next = head; //尾结点连上头节点
        int step = leng - k%leng;  //需要平移的距离
        for(int i=0;i<step;i++) {
            cur = cur.next;
        }
        ListNode newHead = cur.next;  //在这个位置将节点砍断
        cur.next = null;
        return newHead;
    }

    public static void main(java.lang.String[] args) {

    }
}
