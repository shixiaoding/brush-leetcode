//Java：删除有序数组中的重复项
public class RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
        int arr[] = {1, 1, 2, 2, 3, 3, 3, 4};
        int i = removeDuplicates(arr);
        System.out.println(i);
    }

    /**
     * 双指针解法：
     * - 通过双指针 记录 目标位置：0  和 待移动元素的位置：1
     * - 两指针数据进行比较：
     *      - 数据相等：则：待移动元素的位置 向后移一位
     *      - 数据不同：则：目标位置向后移动一位，并 将待移动元素，移动到目标位置
     * 相关注意事项：
     * - 如果 目标位置 和 待移动位置的下标一直，则跳过赋值
     * 新数组的长度：
     * - 即循环结束后，目标位置即是最后一位元素的下标，所以长度为 目标元素下标 + 1
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        // 目标位置 指针
        int target = 0;
        for (int i = 1; i < nums.length; i++) {  // i：待移动元素指针
            // 当 目标数据 和 待移动元素中的数据不同，并且 目标位置向后移动的位置 和 待移动位置不同的时候
            if (nums[target] != nums[i] && ++target != i) {
                // 进行对数据赋值
                nums[target] = nums[i];
            }
        }
        // 返回目标下标 + 1
        return target + 1;
    }



    /**
     * 设置 长度变量：初始化，数组长度
     * 循环遍历，进行相邻元素比较
     * 若不相等：则 i++ 往后进行遍历
     * 若相等：
     *      则 相邻数据，之后的元素都向前位移一位
     *      位移完成后，将数组长度减1
     * @param nums
     * @return
     */
    public static int removeDuplicates1(int[] nums) {
        int arrLength = nums.length;
        // 循环遍历
        for (int i = 0; i < arrLength - 1; ) {
            // 比较相邻两个元素的数据是否相同
            if (nums[i] != nums[i + 1]) {
                // 不相同，向后遍历
                i++;
            } else {
                // 相同，将后续元素都向前移动1位
                for (int j = i + 1; j < arrLength - 1; j++) {
                    nums[j] = nums[j + 1];
                }
                // 每移动一位即，删除一个元素
                arrLength--;
            }
        }
        return arrLength;
    }
}
