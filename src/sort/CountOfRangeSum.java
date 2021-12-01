package sort;

/**
 * 有多少个子数组 累加和 在 upper-lower区间中
 *
 * @author: 苏察哈尔丶灿
 * @create: 2021-12-01 10:37
 */
public class CountOfRangeSum {

    public static int function(int[] arr, int upper, int lower) {
        int[] sum = new int[arr.length];
        sum[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }

        return process(sum, 0, sum.length - 1, upper, lower);
    }


    private static int process(int[] sum, int L, int R, int upper, int lower) {
        if (L == R) {
            if (sum[L] >= lower && sum[R] <= upper) {
                return 1;
            } else {
                return 0;
            }
        }

        int M = L + ((R - L) >> 1);

        int leftAns = process(sum, L, M, upper, lower);
        int rightAns = process(sum, M + 1, R, upper, lower);
        int mergeAns = merge(sum, L, M, R, upper, lower);
        return leftAns + rightAns + mergeAns;
    }

    private static int merge(int[] arr, int L, int M, int R, int upper, int lower) {

        int ans = 0;
        int windowL = L;
        int windowR = L;
        for (int i = M + 1; i <= R; i++) {
            int min = arr[i] - upper;
            int max = arr[i] - lower;

            while (windowR <= M && windowR <= max) {
                windowR++;
            }

            while (windowL <= M && windowL <= min) {
                windowL++;
            }

            ans += windowR - windowL;
        }


        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
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

        return ans;
    }

}
