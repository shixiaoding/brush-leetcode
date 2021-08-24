/**
 * @author oliver
 * @date 2021/8/24 1:15 下午
 */
public class AddTwoNumbers {

    public static void main(String[] args) {

    }

    /**
     * 1、l1,l2 执行相同的步骤：
     * - 循环遍历链表，将逆序排列的元素，转换成整数
     * 2、两个整数相加
     * 3、转换成 String数组
     * 4、循环遍历 String数组 逆序 输出新的链表
     */
    public ListNode addTwoNumbersOne(ListNode l1, ListNode l2) {
        // 把链表转成数字
        long num1 = 0;
        // 位数：0代表个位，1代表十位，以此递增
        int square1 = 0;
        while (l1 != null) {
            num1 += (long) l1.val * Math.pow(10, square1);
            l1 = l1.next;
            square1++;
        }

        long num2 = 0;
        int square2 = 0;
        while (l2 != null) {
            num2 += (long) l2.val * Math.pow(10, square2);
            l2 = l2.next;
            square2++;
        }
        long sumNum = num1 + num2;

        // 如果 和 为 0 ，则直接返回新的链表
        if (sumNum == 0) {
            return new ListNode(0);
        }
        // 转换成 字符串 数组
        String[] strArr = String.valueOf(sumNum).split("");
        ListNode newNode = null;
        // 循环遍历，组成新的逆序链表
        for (String aStr : strArr) {
            if (newNode == null) {
                newNode = new ListNode(Integer.parseInt(aStr));
            } else {
                newNode = new ListNode(Integer.parseInt(aStr), newNode);
            }
        }
        return newNode;
    }

    /**
     * 对暴利解法进行优化，去除 整数 转 字符串 数组，减少空间复杂度
     * 1、循环遍历链表，将逆序排列的元素，转换成整数
     * 2、两个整数相加
     * 3、使用 数学思维，对数取余，可获取每位的值，创建成新的链表
     */
    public ListNode addTwoNumbersTwo(ListNode l1, ListNode l2) {
        long num1 = 0;
        int square1 = 0;
        while (l1 != null) {
            num1 += (long) l1.val * Math.pow(10, square1);
            l1 = l1.next;
            square1++;
        }

        long num2 = 0;
        int square2 = 0;
        while (l2 != null) {
            num2 += (long) l2.val * Math.pow(10, square2);
            l2 = l2.next;
            square2++;
        }
        long sumNum = num1 + num2;

        ListNode head = new ListNode(); // 创建一个新链表，头部为空节点
        ListNode cur = head;
        if (sumNum == 0) {
            return new ListNode(0);
        }
        // 循环遍历，两数之和
        while (sumNum > 0) {
            // 每次 对整数 取余 获取最低位值
            int val = (int) sumNum % 10;
            // 添加到链表中
            cur.next = new ListNode(val);
            cur = cur.next;
            // 移除 整数的 最低位值
            sumNum = sumNum / 10;
        }
        return head.next;
    }

    /**
     * 1. 同时 循环遍历2个链表，对 两个链表同位置的数，进行累加，追加到新链表中
     * 2. 设置 进位数
     *    1. 每位数 相加时，都要加一下进位数，以防有进位
     *    2. 链表 全部遍历完成后，判断是否还有进位，有进在尾部，追加进位数
     */
    public ListNode addTwoNumbersThree(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            // 判断链表 该位是否有值，无值，则返回零
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;

            // 两链表位数相加 and 加 进位值
            int temp = x + y + carry;

            // 将 累加值 放入新的链表中
            if (head == null) {
                head = tail = new ListNode(temp % 10);
            } else {
                tail.next = new ListNode(temp % 10);
                tail = tail.next;
            }
            // 获取进位值
            carry = temp / 10;
            l1 = l1 == null ? l1 : l1.next;
            l2 = l2 == null ? l2 : l2.next;
        }

        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
