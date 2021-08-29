import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author shiding
 * 数组形式的整数加法
 */
public class AddToArrayFormOfInteger {
    public static void main(String[] args) {
        int[] num = {1, 2, 0, 0};
        List<Integer> integers = addToArrayForm(num, 10000);
        integers.forEach(System.out::println);
    }

    /**
     *  循环遍历 按位相加
     * - 循环遍历数组
     * - 数组最后一位 和 整数的最后一位相加，加入新数组
     * - 当 数组循环遍历完成后，整数 还有值时
     * - 循环 取 整数的 最后一位 追加到新数组
     * - 最后反转数组
     */
    public static List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> list = new ArrayList<>();
        // 循环遍历数组
        for (int i = num.length - 1; i >= 0; i--) {
            // 数组末尾 和 k数的 最后一位相加
            int sum = num[i] + k % 10;
            k /= 10;

            // 和 大于 10
            if (sum >= 10) {
                k++;
                sum = sum % 10;
            }
            list.add(sum);
        }
        // 整数 k 还有值时
        while (k > 0) {
            list.add(k % 10);
            k /= 10;
        }
        // 数组反转
        Collections.reverse(list);
        return list;
    }

    /**
     * 循环遍历，-》 整数 与 数组最后位相加
     * - 同时循环 数组 和 整型
     *      - 定义数组 长度 i, 长度 >0 或者 k 整数 > 0,则进行循环
     *      - 每次 将 数组最后位 与 整数相加 ，后取余 加入 数组
     *      - 并 对 整数 进位 ====》 k / 10
     *      - 只至 数组 和 k 都无值
     * - 最后反转数组
     */
    public static List<Integer> addToArrayFormTwo(int[] num, int k) {
        List<Integer> list = new ArrayList<>();
        /**
         * 循环遍历数组
         * 数组 和 数
         * 只有有一个不为 零，则进行循环遍历
         */
        for (int i = num.length - 1; i >= 0 || k > 0; i--, k /= 10) {
            if (i >= 0) {
                k += num[i];
            }
            list.add(k % 10);
        }
        // 数组反转
        Collections.reverse(list);
        return list;
    }


    /**
     * 两数相加： 基本模板公式
     * int carry = 0  进位数
     * sum = x的最后位数 + y的最后位数 + carry进位数
     * 直到：x 和 y 都为空为止
     * @param num
     * @param k
     * @return
     */
    public static List<Integer> addToArrayFormThree(int[] num, int k) {
        List<Integer> list = new ArrayList<>();
        int i = num.length - 1;
        int carry = 0;
        while (i >= 0 || k > 0) {
            int x = i >= 0 ? num[i] : 0;
            int y = k > 0 ? k % 10 : 0;
            int sum = x + y + carry;

            carry = sum / 10;
            k /= 10;
            i--;
            list.add(sum % 10);
        }
        if (carry != 0) {
            list.add(carry);
        }
        Collections.reverse(list);
        return list;
    }
}

