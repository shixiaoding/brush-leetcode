import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Java：反转字符串中的单词 III
 *
 * @author shiding
 */
public class ReverseWordsInAStringIii {
    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";
        System.out.println(reverseWords3(s));
    }

    /**
     * 1: 通过 java语言特性
     * 先将数据进行分割成单个单词的数组
     * 在对单个单词分割成字符数组，数组进行反转 后 转为字符串
     *
     */
    public static String reverseWords(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> list = Arrays.asList(s.trim().split(" "));

        for (String str : list) {
            List<String> tempStr = Arrays.asList(str.split(""));
            Collections.reverse(tempStr);
            stringBuilder.append(String.join("", tempStr)).append(" ");
        }
        return stringBuilder.toString().trim();
    }

    /**
     * 通过 双指针 确认单词范围
     * 通过 范围 倒叙截取字符 放入新的字符串中
     */
    public static String reverseWords2(String s) {
        StringBuilder strBuilder = new StringBuilder();
        int len = s.length();
        int left = 0;
        int right = 0;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) != ' ') {
                right++;
            } else {
                while (left != right) {
                    strBuilder.append(s.charAt(right - 1));
                    right--;
                }
                strBuilder.append(" ");
                right = left = i + 1;
            }
        }
        while (left != right) {
            strBuilder.append(s.charAt(right - 1));
            right--;
        }
        return strBuilder.toString();
    }


    /**
     * 双指针 + 原地替换
     * 通过双指针 确认单词范围
     * 对单词进行头尾字符替换
     */
    public static String reverseWords3(String s) {
        char[] chars = s.toCharArray();
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            if (chars[i] == ' ') {
                reverse(start, i - 1, chars);
                start = i + 1;
            }
            if (i == s.length() - 1) {
                reverse(start, i, chars);
            }
        }
        return new String(chars);
    }

    public static void reverse(int left, int right, char[] arrays) {
        while (left < right) {
            char temp = arrays[right];
            arrays[right] = arrays[left];
            arrays[left] = temp;
            left++;
            right--;
        }
    }


}