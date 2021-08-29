import java.util.*;

/**
 * @author shiding
 * 翻转字符串里的单词
 */
public class ReverseWordsInAString {
    public static void main(String[] args) {
        String s = "a good   example";
//        String s = "  hello world  ";
//        String s = "the sky is blue";
        System.out.println(reverseWords4(s));
    }


    /**
     * 暴利破解1： 使用Java 语言特性实现
     * 1. 将字符串 通过 空格 切割成 单词数组
     * 2. 反转单词 （通过 数组转换成集合 利用 集合的反转函数实现）
     * 3. 重新通过 空格 拼接成 字符串
     *
     * @param s
     * @return
     */
    public static String reverseWords(String s) {
        if (s == null || "".equals(s.trim())) {
            return "";
        }
        // 去除字符串 两头的空格
        s = s.trim();

        // 将数组按空格切割成数组
        String[] strings = s.split("\\s+");
        // 将数组转化成 集合
        List<String> list = Arrays.asList(strings);
        // 对集合数据进行反转
        Collections.reverse(list);
        // 集合通过 空格拼接成字符串
        return String.join(" ", list);
    }

    /**
     * 数组 + 双指针
     * 1. 创建一个新的数组，来临时存储
     * 2. 创建 first、last 指针
     * 3. 倒序遍历字符串，定位单词的起止索引
     * 4. 根据起止索引，获取字符串存入临时数组
     * 5. 还原指针，进行重复循环 定位下一个单词
     * 6，将新数组中合法数据生成新字符串
     *
     * @param s
     * @return
     */
    public static String reverseWords2(String s) {
        s = s.trim();
        int len = s.length();

        if (s == null || len == 0) {
            return "";
        }
        char[] chars = new char[len];
        // 头
        int first = -1;
        // 尾
        int last = -1;
        // 新数组索引
        int index = 0;
        // 倒序遍历，定位单词起止索引
        for (int i = len - 1; i >= 0; i--) {
            char c = s.charAt(i);
            //
            if (c != ' ') {
                if (last == -1) {
                    last = i;
                }
                if (i == 0) {
                    first = i;
                }
            } else {
                if (last != -1) {
                    first = i + 1;
                }
            }
            // 将读取到的单词，写入新的数组
            if (first >= 0 && last >= 0) {
                if (index > 0) {
                    chars[index++] = ' ';
                }
                while (first <= last) {
                    chars[index++] = s.charAt(first);
                    first++;
                }
                // 还原指针
                first = last = -1;
            }
        }
        // 将最后得到的数组 转换成字符串
        return String.valueOf(chars, 0, index);
    }

    public static String reverseWords3(String s) {
        StringBuilder newS = new StringBuilder();
        s = s.trim();
        int len = s.length();

        if (s == null || len == 0) {
            return "";
        }
        // 头
        int first = -1;
        // 尾
        int last = -1;
        // 倒序遍历，定位单词起止索引
        for (int i = len - 1; i >= 0; i--) {
            char c = s.charAt(i);
            //
            if (c != ' ') {
                if (last == -1) {
                    last = i;
                }
                if (i == 0) {
                    first = i;
                }
            } else {
                if (last != -1) {
                    first = i + 1;
                }
            }
            // 将读取到的单词，写入新的数组
            if (first >= 0 && last >= 0) {
                if (newS != null && newS.length() > 0) {
                    newS.append(" ");
                }
                newS.append(s.substring(first, last + 1));
                // 还原指针
                first = last = -1;
            }
        }
        // 将最后得到的数组 转换成字符串
        return newS.toString();
    }

    /**
     * 双端队列
     * - 往双端队列头部依次存入每个单词
     * - 通过遍历，将字符存储在字符串中，
     * - 当到达单词尾部时，将缓冲区中的单词，存放到 双端队列的同步
     * - 从双端队列取出单词 即： 通过 String.join 将单词用 空格拼接
     *
     * @param s
     * @return
     */
    public static String reverseWords4(String s) {
        int left = 0, right = s.length() - 1;
        Deque<String> d = new ArrayDeque<String>();
        StringBuilder word = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);
            if (c != ' ') {
                word.append(c);
            } else {
                // 到达了单词结尾
                if (word.length() != 0) {
                    // 将单词添加到队列的头部
                    d.offerFirst(word.toString());
                    word.setLength(0);
                }
            }
            ++left;
        }
        if (word.length() > 0) {
            d.offerFirst(word.toString());
        }
        return String.join(" ", d);
    }
}
