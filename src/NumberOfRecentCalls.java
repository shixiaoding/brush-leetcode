import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Java：最近的请求次数
public class NumberOfRecentCalls {
    public static void main(String[] args) {

    }
}

//leetcode submit region begin(Prohibit modification and deletion)
class RecentCounter {

    public RecentCounter() {

    }

    Queue<Integer> q = new LinkedList<Integer>();

    /**
     * 使用 java 自带队列 完成
     *
     * @param t
     * @return
     */
    public int ping4(int t) {

        q.add(t);
        while (q.peek() < t - 3000) {
            q.poll();
        }
        return q.size();
    }


    QueueCustom queueCustom = new QueueCustom();

    /**
     * 解法3：自定义队列
     * - 使用队列的数据结构，先进先出的原则
     * - 通过链表实现队列
     * - 定义属性： 对头：`head` 、对尾： `tail` 、长度： `size`
     * - 定义方法： 添加节点：`add()`  、移除节点:  `poll()` 、 队列长度： `size()`
     * - 内部定义： `Node` 类 ，封装每次入队的请求，和指向下一个节点的指针
     * - 每次请求向队列尾部追加节点
     * - 循环检查列头数据是否合法，不合法则移除节点
     * - 返回队列长度
     *
     * @param t
     * @return
     */
    public int ping3(int t) {

        queueCustom.add(t);
        while (queueCustom.head.getVal() < t - 3000) {
            queueCustom.poll();
        }
        return queueCustom.size();
    }

    class QueueCustom {

        Node head;

        Node tail;

        int size = 0;

        QueueCustom() {

        }

        public void add(int val) {
            if (head == null) {
                head = new Node(val);
                tail = head;
            } else {
                Node last = new Node(val);
                tail.next = last;
                tail = last;
            }
            size++;
        }

        public int poll() {
            int headVal = head.val;
            Node next = head.next;
            head.next = null;
            head = next;
            if (next == null) {
                tail = null;
            }
            size--;
            return headVal;
        }


        public int size() {
            return this.size;
        }


        class Node {
            int val;
            Node next;

            Node(int index) {
                this.val = index;
            }

            int getVal() {
                return val;
            }
        }
    }


    List<Integer> list2 = new ArrayList<Integer>(3002);

    /**
     * 数组操作优化
     * 1， 确认：3000秒内最多请求数：
     * t 请求次 + 前3000次 = 3001次
     * 即：最大的存储范围为 3001 + 1 次 = 3002 次
     * 2. 每次添加时，循环遍历数组，将数组内不符合范围内的数据给删除
     *
     * @param t
     * @return
     */
    public int ping2(int t) {
        // 数组初始化 【至多调用 ping 方法 10^4 次】
        list2.add(t);
        while (list2.get(0) < t - 3000) {
            list2.remove(0);
        }
        return list2.size();
    }


    List<Integer> list = new ArrayList<Integer>(10000);


    /**
     * 数组遍历 （暴利破解）
     * 1. 将每次请求的数，存放到数组中 （并记录最后一位的下标）
     * 2. 每次从数组尾部进行遍历，对大于等于 【t - 3000】 ，进行统计次数，进行累加
     *
     * @param t
     * @return
     */
    public int ping1(int t) {
        // 数组初始化 【至多调用 ping 方法 10^4 次】

        list.add(t);
        int end = list.size() - 1;
        int count = 0;
        while (list.get(end) >= t - 3000) {
            count++;
            if (--end < 0) {
                break;
            }

        }
        return count;
    }


}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */
//leetcode submit region end(Prohibit modification and deletion)
