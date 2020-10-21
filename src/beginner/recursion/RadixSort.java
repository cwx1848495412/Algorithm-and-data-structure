package beginner.recursion;

import java.util.Arrays;

/**
 * 基数排序
 *
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/9/21 19:29
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {546, 56, 889, 45, 21, 55, 69};

        doSort(arr, 3);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 执行排序
     * countP 可以用count 赋值替换
     * 为了帮助理解没有替换
     *
     * @param arr
     * @param d
     */
    public static void doSort(int[] arr, int d) {
        // 有几位循环几次
        for (int i = 1; i <= d; i++) {
            int[] count = new int[10]; // 词频
            int[] countP = new int[10]; // <=有几个
            int[] help = new int[arr.length]; // 倒出序列

            for (int j = 0; j < arr.length; j++) {
                // 当前位的值
                int num = getDigit(arr[j], i);
                // 词频统计
                count[num]++;
                countP[num]++;
            }

            // countP 规划
            for (int j = 1; j < countP.length; j++) {
                countP[j] = countP[j] + countP[j - 1];
            }

            // 倒出重来
            for (int j = arr.length - 1; j >= 0; j--) {
                int num = getDigit(arr[j], i);
                int howMany = countP[num];
                help[howMany - 1] = arr[j];

                // 有几位的统计数值--
                countP[num]--;
            }

            // 更新arr
            for (int j = 0; j < arr.length; j++) {
                arr[j] = help[j];
            }

//            System.out.println("count" + Arrays.toString(count));
//
//            System.out.println("countP" + Arrays.toString(countP));
//
//            System.out.println("help" + Arrays.toString(help));
        }

    }

    /**
     * 获取某个数字的第几位的值
     *
     * @param num
     * @param d
     * @return
     */
    public static int getDigit(int num, int d) {

//        num % 10
//        num / 10 % 10
//        num / 100 % 10
        return (int) (num / Math.pow(10, d - 1)) % 10;
    }
}
