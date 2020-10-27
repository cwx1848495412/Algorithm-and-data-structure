package character_string;

/**
 * KMP 算法
 *
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/10/27 15:01
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class KMPProblem {
    /**
     * @param str   字符串
     * @param match 匹配字串
     * @return 返回下标
     */
    private static int kmpContains(String str, String match) {

        if (str == null
                || match == null
                || match.length() == 0
                || str.length() == 0
                || match.length() > str.length()) {
            return -1;
        }

        char[] strArr = str.toCharArray();
        char[] matchArr = match.toCharArray();

        // 获取首尾相同的标志数组
        int[] nextArr = getNextArray(matchArr);

        // 负责str 里面的指针
        int x = 0;
        // 负责match 里面的指针
        int y = 0;
        while (x < strArr.length && y < matchArr.length) {

            if (strArr[x] == matchArr[y]) {
                // 相等了 继续往下比对
                x++;
                y++;
            } else if (y == 0) {
                // y都找到头了 还配不上 x往后在找着看吧
                x++;
            } else {
                // 不相等了 x 不动 y 换着找
                y = nextArr[y];
            }
        }
        // x 越界 y 没越界  没匹配完 -1
        // x 越界 y 越界    尾后缀相同 x位置减去y的长度 是开始位置
        // x 没越界 y越界   匹配完了 x位置减去y的长度 是开始位置
        // x 没越界 y没越界   循环都出不来 继续执行直至上面三种情况之一
        // 总结
        return y == matchArr.length ? x - y : -1;
    }

    private static int[] getNextArray(char[] matchArr) {
        if (matchArr.length == 1) {
            return new int[]{-1};
        }
        int[] nextArr = new int[matchArr.length];

        // 人为规定
        nextArr[0] = -1;
        nextArr[1] = 0;
        int i = 2;

        // 辅助变量
        int cn = 0;
        while (i < matchArr.length) {
            if (matchArr[i] == matchArr[cn]) {
                // 相等
//                nextArr[i] = cn + 1;
//                i++;
//                cn ++;
                nextArr[i++] = ++cn;
            } else if (cn == 0) {
                // 到头了 实在匹配不到
                nextArr[i++] = 0;
            } else {
                // 不相等 退着搞
                cn = nextArr[cn];
            }
        }

        return nextArr;
    }

    public static void main(String[] args) {
        String str = "abdabcabc";
        String match = "cab";
        System.out.println(kmpContains(str, match));
    }
}
