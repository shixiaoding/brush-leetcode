import java.util.HashSet;
import java.util.Set;

/**
 * 无重复字符的最长子串
 * <p>
 * 子串：原来字符串的一部分
 * 子序列：一个字符串中的 几个字符拼接成新的字符串
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        String s = "abcabcbb";
//        String s = "bbbbb";
//        String s = "pwwkew";

        System.out.println(lengthOfLongestSubstring3(s));
    }

    /**
     * 题目要点：
     * 子串中无重复字符
     * 子串 不是 子序列
     * 测试数据 只包含 ASCII 码表字符
     * 字符串可能为空， 或全部由空字符组成
     */

    /**
     * 临时字符串 或者 直接 int 值变量
     * 双指针
     * 优化暴利解法
     * 对于上面的方法，进行不临时存储 子串，直接计算其字符长度
     * <p>
     * 定义变量 maxLength 表示最大长度
     * 使用双指针截取不包含重复的字符串
     * 计算子串长度，保留较大值到maxLength
     * <p>
     * 更优解：
     * 无效代码：
     * - 不需要存储子串
     * - 不需要生成字符串常量，生成字符串的原因，就是为了检测字符串是否重复
     * <p>
     * 寻找更好的算法思维：
     * - 减少循环
     * - 定位子串 检查过程比较耗时
     */
    public static int lengthOfLongestSubstring(String s) {
        int len = s.length();
        if (s == null || s.length() == 0) {
            return 0;
        }
        int maxLength = 1;
        for (int start = 0; start < len; start++) {
            for (int end = start + 1; end < len; end++) {
                String subStr = s.substring(start, end);
                if (subStr.indexOf(s.charAt(end)) != -1) {
                    break;
                }
                int subLen = end - start + 1;
                if (subLen > maxLength) {
                    maxLength = subLen;
                }
            }
        }
        return maxLength;
    }

    static int hash(char key) {
        return key;
    }

    public static int lengthOfLongestSubstring3(String s) {
        int len;
        // 源字符串长度
        if (s == null || (len = s.length()) == 0) {
            return 0;
        }
        // 最长不重复子串的长度
        int res = 0;
        // 子串最左端字符索引
        int left = 0;
        // 子串最右端字符索引
        int right = 0;

        // 1.定义哈希表，支持ASCII码表的全部字符
        char[] chs = new char[128];
        // 2.遍历字符串的所有字符
        while (right < len) {
            char rightChar = s.charAt(right); // 右指针字符
            char c = chs[(chs.length - 1) & hash(rightChar)]; // hash算法
            if (rightChar != c) { // 未重复出现
                // 2.1.双指针定位子串索引：右指针自增
                right++;
                // 将不重复字符记录到哈希表中
                chs[(chs.length - 1) & hash(rightChar)] = rightChar;
                // 3.每次记录子串长度，并计算最大值
                int size = right - left; // 每个不重复子串的长度
                res = res > size ? res : size;
            } else { // 重复出现
                // 2.2.双指针定位子串索引：左指针自增。从哈希表中移出最左侧字符：赋默认值
                char leftChar = s.charAt(left++);
                chs[(chs.length - 1) & hash(leftChar)] = '\u0000';
            }
        }
        return res;
    }

    // todo 滑动窗口
}
