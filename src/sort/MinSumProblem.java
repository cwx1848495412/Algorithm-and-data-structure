package sort;

/**
 * 数组小和
 *
 * @author: 苏察哈尔丶灿
 * @create: 2021-11-30 13:54
 */
public class MinSumProblem {

    private static int merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        int res = 0;
        while (p1 <= M && p2 <= R) {
            res += arr[p1] < arr[p2] ? (R - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
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

        return res;
    }

    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }

        int M = L + ((R - L) >> 1);
        return process(arr, L, M)
                +
                process(arr, M + 1, R)
                +
                merge(arr, L, M, R);
    }
}
