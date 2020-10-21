package beginner.recursion;

import java.util.Arrays;

/**
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/8/24 22:00
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class MergeSort {
    /**
     * 递归写法
     *
     * @param arr
     * @param L
     * @param R
     */
    public static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    private static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];

        int i = 0;
        int p1 = L;
        int p2 = M + 1;

        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }

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

    /**
     * 循环写法
     *
     * @param arr
     */
    public static void process2(int[] arr) {
        int mergeSize = 1;
        int len = arr.length;
        while (mergeSize < len) {
            int L = 0;
            while (L < len) {
                // 中间下标值
                int M = L + mergeSize - 1;
                // 中间下标值维护
                if (M >= len) {
                    break;
                }
                // 右边下标值及维护
                int R = Math.min(M + mergeSize, len - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }

            // 溢出优化逻辑
            if (mergeSize >= (len >> 1)) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    /**
     * 荷兰国旗 默认取arr[R]下标做处理
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int[] netherLands(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }

        if (L == R) {
            return new int[]{L, R};
        }

        // < 区域边界
        int less = L - 1;

        // > 区域边界
        int more = R;

        // 开始下标
        int index = L;

        while (index < more) {
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) {
                swap(arr, index++, ++less);
            } else if (arr[index] > arr[R]) {
                swap(arr, index, --more);
            }
        }

        // 上述过程没有动过arr[R]下标，还需将此处与>区域交换
        swap(arr, index, R);

        return new int[]{less + 1, index};
    }

    /**
     * 交换数组指定下标的两个值
     *
     * @param arr
     * @param L
     * @param R
     */
    private static void swap(int[] arr, int L, int R) {
        int tmp = arr[L];
        arr[L] = arr[R];
        arr[R] = tmp;
    }

    /**
     * 以arr[R]分级
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int partition(int[] arr, int L, int R) {
        int less = L - 1;
        int more = R;
        int index = L;
        while (index < more) {
            if (arr[index] <= arr[R]) {
                index++;
                less++;
            } else {
                swap(arr, index, --more);
            }
        }
        swap(arr, index, R);
        return index;
    }


    /**
     * 快排01
     *
     * @param arr
     * @param L
     * @param R
     */
    private static void quickSort1(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }

        int M = partition(arr, L, R);
        quickSort1(arr, L, M - 1);
        quickSort1(arr, M + 1, R);
    }

    /**
     * 快排02
     * 荷兰国旗问题 每次少移动相同的数
     * 两个区域分为三个区域
     *
     * @param arr
     * @param L
     * @param R
     */
    private static void quickSort2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }

        int[] midArr = netherLands(arr, L, R);
        quickSort2(arr, L, midArr[0] - 1);
        quickSort2(arr, midArr[0] + 1, R);
    }

    public static void main(String[] args) {
//        int[] arr = {4, 2, 6, 8, 4, 2, 5, 6, 8, 3, 9, 0, 7, 4, 6, 9, 9};

//        process(arr, 0, arr.length - 1);
//        process2(arr);
//        System.out.println(Arrays.toString(arr));

//        int[] arr = {7, 3, 2, 5, 6, 1, 3};
//        int[] newArr = netherLands(arr, 0, arr.length - 1);
//        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(newArr));


//        int[] arr = {7, 3, 2, 5, 6, 1, 3};
//        int value = partition(arr, 0, arr.length - 1);
//        System.out.println(Arrays.toString(arr));
//        System.out.println(value);


//        int[] arr = {4, 2, 6, 8, 4, 2, 5, 6, 8, 3, 9, 0, 7, 4, 6, 9, 9};
//        int[] arr = {};
//        quickSort1(arr, 0, arr.length - 1);
//        System.out.println(Arrays.toString(arr));


        int[] arr = {4, 2, 6, 8, 4, 2, 5, 6, 8, 3, 9, 0, 7, 4, 6, 9, 9};
        quickSort2(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
        System.out.println(0.1 + 0.2);
    }
}
