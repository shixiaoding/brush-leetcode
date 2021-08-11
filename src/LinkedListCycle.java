import java.util.HashSet;
import java.util.Set;

/**
 * @author: oliver
 * @date: 2021/8/11 4:19 下午
 */
public class LinkedListCycle {
    public static void main(String[] args) {

    }


    public boolean hasCycle3(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        // 慢指针
        ListNode slow = head;
        // 快指针
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            // 当快指针 等于 慢指针时， 则说明链表 发生了循环
            if (slow == fast) {
                return true;
            }
            // 慢指针 走1步
            slow = slow.next;
            // 快指针 走2步
            fast = fast.next.next;
        }
        return false;
    }

    public boolean hasCycle2(ListNode head) {
        // 使用set集合
        Set<ListNode> array = new HashSet<>();
        while (head != null) {
            // 循环遍历 将节点 添加到集合中，添加失败，则说明节点已存在，是环形链表
            if (!array.add(head)) {
                return true;
            }
            // 向下指定 head
            head = head.next;
        }
        return false;
    }

    public boolean hasCycle1(ListNode head) {
        ListNode[] array = new ListNode[10000];
        while (head != null) {
            // 循环遍历容器
            for (int i = 0; i < array.length; i++) {
                // 并与容器中的节点进行比较，已经存在过，则是环形链表
                if (array[i] == head) {
                    return true;
                }
                // 不存在容器中的话，将节点信息，存入容器
                if (array[i] == null) {
                    array[i] = head;
                    break;
                }
            }
            // 向下指定 head
            head = head.next;
        }
        return false;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
