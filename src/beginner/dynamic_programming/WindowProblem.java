package beginner.dynamic_programming;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 滑动窗口
 *
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/10/23 20:21
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class WindowProblem {
//    public static void main(String[] args) {
//        // 数组内 连续子数组 最大最小值的差 符合条件的 有几个
//    }


    //    L R
    public static int process(int[] arr, int conditionNum) {

        if (arr == null || arr.length == 0) {
            return 0;
        }

        // 两个最大最小值的滑动窗口
        LinkedList<Integer> maxList = new LinkedList<>();
        LinkedList<Integer> minList = new LinkedList<>();

        int res = 0;
        int R = 0;
        for (int L = 0; L < arr.length; L++) {
            while (R < arr.length) {
                while (!maxList.isEmpty() && (arr[maxList.peekLast()] <= arr[R])) {
                    maxList.pollLast();
                }
                maxList.addLast(R);


                while (!minList.isEmpty() && (arr[minList.peekLast()] >= arr[R])) {
                    minList.pollLast();
                }
                minList.addLast(R);

                int num = arr[maxList.getFirst()] - arr[minList.getFirst()];

                // 不满足
                if (num > conditionNum) {
                    break;
                }

                R++;
            }

            res += R - L;

            if (maxList.getFirst() == L) {
                maxList.pollFirst();
            }

            if (minList.getFirst() == L) {
                minList.pollFirst();
            }

        }

        return res;
    }

    public static void main(String[] args) {
        // 行列问题
        int[][] arr = {
                {2, 4, 6},
                {4, 6, 8},
                {4, 6, 8}
        };

        int[][] ints = process2(arr);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(Arrays.toString(ints[i]));
        }
    }

    public static int[][] process2(int[][] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null || arr[0].length == 0) {
            return new int[0][0];
        }

        int[][] res = new int[arr.length][arr[0].length];

        int[] hang = new int[arr[0].length];
        int[] lie = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                int currVal = arr[i][j];
                hang[i] += currVal;
                lie[j] += currVal;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                int currVal = arr[i][j];
                int resVal = hang[i] + lie[j] - currVal;
                res[i][j] = resVal / (arr.length + arr[0].length - 1);
            }
        }

        return res;
    }
}
