package sort;

import java.util.Arrays;

/**
 * @author: 苏察哈尔丶灿
 * @create: 2021-11-30 09:28
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {1, 6, 2, 8, 3, 9, 7};
//        process(arr, 0, arr.length - 1);
        process2(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * N * logN
     * 迭代解法
     *
     * @param arr
     */
    public static void process2(int[] arr) {
        int step = 1;

        while (step < arr.length) {
            int L = 0;
            while (L < arr.length) {
                int M = L + step - 1;
                if (M > arr.length) {
                    break;
                }

                int R = Math.min(arr.length - 1, M + step);

                merge(arr, L, M, R);
                L = R + 1;
            }
            step <<= 1;
        }

    }


    /**
     * N * logN
     *
     * @param arr
     * @param L
     * @param R
     */
    public static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int M = L + ((R - L) >> 1);

        process(arr, L, M);
        process(arr, M + 1, R);
        merge(arr, L, M, R);
    }

    private static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            // 没有稳定性
//            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
            // 具有稳定性
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }

        // 左侧或者右侧 有一侧没跑完
        // 剩下的一侧一定全部有序
        // 可直接累加

        while (p1 <= M) {
            help[i++] = arr[p1++];
        }

        while (p2 <= R) {
            help[i++] = arr[p2++];
        }

        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }
    }
}
