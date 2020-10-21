package beginner.dynamic_programming;

import utils.StopWatch;

/**
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/10/18 15:46
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class NQueen {

    /**
     * @param i      当前行
     * @param record 索引为行号 值为列号
     * @param n      共多少行和列  题目规定行列数相同 所以也代表共多少列
     * @return 共有几种摆法
     */
    public static int process1(int i, int[] record, int n) {
        if (i == n) {
            // 0 - (n-1) 为尝试完的所有空间
            // 当前行为n就代表都尝试完了 返回当次结果
            return 1;
        }

        int res = 0;
        // 尝试当前行每一列 n*n的表格 n行也可以看为n列
        for (int j = 0; j < n; j++) {
            if (isValid(i, j, record)) {
                // 如果当前行和之前所有结果都不冲突,可以尝试下一行
                record[i] = j;
                res += process1(i + 1, record, n);
            }
        }
        return res;
    }

    // 验证当前行是否能摆
    public static boolean isValid(int i, int j, int[] record) {
        // 验证到当前行的上一行
        for (int k = 0; k < i; k++) {
            // a,b
            // c,d
            // 本尝试方式天生不共行
            // 如何判断不共列
            // b == d 共列
            // |a-c| == |b-d| 共斜线
            // 原理 x,y 坐标差值相同则共斜线
            if (j == record[k] || Math.abs(i - k) == Math.abs(j - record[k])) {
                // 共列返回false
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int n = 14;
        int[] record = new int[n];

        StopWatch.start();
        int res = process1(0, record, n);
        System.out.println(res);
        StopWatch.stop();

        StopWatch.start();
        int res2 = callProcess2(n);
        System.out.println(res2);
        StopWatch.stop();
    }

    /**
     * @param n 要得出几皇后问题
     * @return
     */
    public static int callProcess2(int n) {
        // 要想办法组织出limit
        // 例如4皇后问题要 000..0 1111

        // 0000 0001 << 4
        // 0001 0000 - 1
        // 0000 1111
        int limit = (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    /**
     * @param limit       截止条件位值
     * @param columnLimit 列限制
     * @param leftLimit   左斜线限制
     * @param rightLimit  右斜线限制
     * @return 状况
     */
    public static int process2(int limit, int columnLimit, int leftLimit, int rightLimit) {
        // 位运算
        // limit n皇后 就有n位截止
        // 本行列限制等于最终截止条件 返回
        if (columnLimit == limit) {
            // 000...000 1111
            // 000...000 1101 没点满表示皇后不够 只摆了三个(...4皇后下)
            // 000...000 1111 点满表示皇后齐活了 (...4皇后下)
            return 1;
        }
        // 开始处理逻辑
        // 把所有限制条件集中起来 columnLimit | leftLimit | rightLimit
        // 取反 ~(columnLimit | leftLimit | rightLimit)
        // 与 limit
        // 取反后 需要的后n位 能放皇后的位置为1
        // 但是前几位 0/1并不确定 再& 与上limit 的前边的0 相当于规整数据
        int position = limit & (~(columnLimit | leftLimit | rightLimit));
//        int tryPosition = 0;
        int res = 0;
        // position 相当于可用位中可放置位置有几个
        // 都没有就不会进for循环
        while (position != 0) {
            // 从最小位的1开始尝试
            int tryPosition = position & ((~position) + 1);

            // 更改下次position的值
            // 111 - 011 = 100 = 7 - 3 = 4
            // 111 ^ 011 = 100 = 7 ^ 3 = 4
            // 让其下次再从次之的最低位1去尝试
            position = position - tryPosition;// position ^ tryPosition 也行
            res += process2(limit,
                    columnLimit | tryPosition,
                    (leftLimit | tryPosition) << 1,
                    // 无符号右移，目的 高位补0，不想要乱七八糟的1
                    // 不过要上好像也无妨 limit 与完之后 高位都会归0
                    (rightLimit | tryPosition) >>> 1
            );
        }

        return res;
    }

}
