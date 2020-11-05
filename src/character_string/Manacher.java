package character_string;

import java.util.Arrays;

/**
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/11/2 15:38
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class Manacher {
    public static int process(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }

        // 处理字符串 填充虚轴
        char[] str = handleString(s);

        // 每个字符下标位对应的可以确定的最长回文半径
        int[] lenArr = new int[str.length];

        // 右边界 不断往右侧扩展
        // 第一个不成轴对称的位置
        // 即为最右侧成轴对称位置的下一个
        int R = -1;
        // 成轴对称的下标位置
        int C = -1;

        int maxValue = Integer.MIN_VALUE;
        // a b c d

        // 遍历数组 开始
        for (int i = 0; i < str.length; i++) {
            // i < R 在回文区域内
            //       对称节点没超过左边界 为对称节点的长度
            //           对称节点坐标为 2C - i 可以自己画图想一想为什么 很好理解
            //       对称节点超越了左边界 受右边界限制
            //           右边界长度为R - i
            // i >= R 在回文区域外
            // 当前毋须交验的长度为

            lenArr[i] = R > i ? Math.min(lenArr[2 * C - i], R - i) : 1;
            // 左右边界判断不越界
            while (i + lenArr[i] < str.length && i - lenArr[i] > -1) {
                if (str[i + lenArr[i]] == str[i - lenArr[i]]) {
                    lenArr[i]++;
                } else {
                    break;
                }
            }

            if (i + lenArr[i] > R) {
                R = i + lenArr[i];
                C = i;
            }
            maxValue = Math.max(maxValue, lenArr[i]);
        }

        System.out.println(Arrays.toString(lenArr));
        // 原字串长度为 直径/2 或者 半径-1
        return maxValue - 1;
    }

    public static char[] handleString(String s) {
        // 补充字符防止虚轴错过
        char[] chars = s.toCharArray();
        char[] result = new char[chars.length * 2 + 1];

        for (int i = 0; i < chars.length; i++) {
            result[2 * i] = '#';
            result[2 * i + 1] = chars[i];
        }

        result[result.length - 1] = '#';
        return result;
    }

    public static void testHandleString() {
        char[] chars = handleString("qwer");
        System.out.println(Arrays.toString(chars));
    }

    public static void main(String[] args) {
//        testHandleString();
        int res = process("acaa");
        System.out.println(res);
    }

}
