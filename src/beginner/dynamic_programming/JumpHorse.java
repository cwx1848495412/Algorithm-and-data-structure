package beginner.dynamic_programming;

import utils.StopWatch;

/**
 * 跳马问题
 * 马从(0,0) 出发 有k步要走 并且一定要走完
 * 最终来到(x,y)的方法有多少种
 * 不考虑绊马脚
 *
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/10/26 13:41
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class JumpHorse {

    public static void callProcess1() {
        StopWatch.start();
        int i = process1(6, 8, 10);
        StopWatch.stop();

        System.out.println("暴力递归,方法数为:" + i);
    }

    public static void callProcess2() {
        StopWatch.start();
        int i = process2(6, 8, 10);
        StopWatch.stop();

        System.out.println("动态规划,方法数为:" + i);
    }

    // 暴力递归
    private static int process1(int x, int y, int k) {
        if (k == 0) {
            return x == 0 && y == 0 ? 1 : 0;
        }

        if (x < 0 || x > 8 || y < 0 || y > 9) {
            return 0;
        }

        return process1(x - 1, y + 2, k - 1)
                + process1(x + 1, y + 2, k - 1)
                + process1(x + 2, y + 1, k - 1)
                + process1(x + 2, y - 1, k - 1)
                + process1(x + 1, y - 2, k - 1)
                + process1(x - 1, y - 2, k - 1)
                + process1(x - 2, y - 1, k - 1)
                + process1(x - 2, y + 1, k - 1);
    }

    // 动态规划
    private static int process2(int x, int y, int k) {
        int[][][] dp = new int[9][10][k + 1];
        // 这个不是终止条件
        // 这个是设置已知条件
        // 从0,0 起跳,剩余0步的情况下 x,y == 0,0 这种就算一种跳法成立
        // 否则不成立这种跳法,其余都为0,数组默认值刚好也是0,不需要特地初始化值
        dp[0][0][0] = 1;

        for (int level = 1; level < k + 1; level++) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 10; j++) {
                    dp[i][j][level] = getDPValue(dp, i - 1, j + 2, level - 1)
                            + getDPValue(dp, i + 1, j + 2, level - 1)
                            + getDPValue(dp, i + 2, j + 1, level - 1)
                            + getDPValue(dp, i + 2, j - 1, level - 1)
                            + getDPValue(dp, i + 1, j - 2, level - 1)
                            + getDPValue(dp, i - 1, j - 2, level - 1)
                            + getDPValue(dp, i - 2, j - 1, level - 1)
                            + getDPValue(dp, i - 2, j + 1, level - 1);
                }
            }
        }


        return dp[x][y][k];
    }

    // 获取dp表记录值，因为有判断条件，抽离出一个方法
    private static int getDPValue(int[][][] dp, int x, int y, int k) {
        if (x < 0 || x > 8 || y < 0 || y > 9) {
            return 0;
        }

        return dp[x][y][k];
    }

    public static void main(String[] args) {
        callProcess1();
        callProcess2();
    }
}
