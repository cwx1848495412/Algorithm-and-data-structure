package character_string;

import java.util.Arrays;

/**
 * 一个数组中第K【小/大】的数
 *
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/10/28 15:48
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class BTPRT {

    /**
     * 简易版，帮助理解
     *
     * @param arr   数组
     * @param L     左指针
     * @param R     右指针
     * @param index 一个数组中第K小的数,比如第5,
     *              那么它所在数组下标应该为 5 - 1 = 4
     *              index传参4
     *              index 范围应该在L..R之间
     */
    public static int process(int[] arr, int L, int R, int index) {
        if (L == R) {
            // 潜台词 L == R == index
            // 因为index 在L..R之间
            return arr[index];
        }

        // 随机选一个划分值，目的是为了避免最差打偏的情况
        int pivot = arr[L + (int) Math.random() * (R - L + 1)];

        // 返回等于的左右边界
        int[] range = netherLands(arr, L, R, pivot);

        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            // 去左侧递归
            return process(arr, L, range[0] - 1, index);
        } else {
            // 去右侧递归
            return process(arr, range[1] + 1, R, index);
        }
    }


    /**
     * 荷兰国旗
     *
     * @param arr   数组
     * @param L     左指针
     * @param R     右指针
     * @param pivot 以此值来划分
     * @return
     */
    public static int[] netherLands(int[] arr, int L, int R, int pivot) {
        if (L > R) {
            throw new RuntimeException("参数很奇怪,你别闹");
//            return new int[]{-1, -1};
        }
        // L       R
        // 5 7 5 4 8 == 5
        // 0 1 2 3 4

        //   LR
        // 4 5  8 == 5
        // 0 1  2
        // 本函数中 L R 是基本数据类型，且是局部变量
        // 可以随意改值，不会影响其他的变量
        int index = L;// 0
        while (index <= R) {
            if (arr[index] == pivot) {
                index++;
            } else if (arr[index] < pivot) {
                swap(arr, L++, index++);
            } else if (arr[index] > pivot) {
                swap(arr, index, R--);
            }
        }

        // 等于区域下标临界值
        return new int[]{L, R};
    }

    /**
     * 交换数组中两个下标的值
     *
     * @param arr 数组
     * @param L   左指针
     * @param R   右指针
     */
    public static void swap(int[] arr, int L, int R) {
        // 改的是引用类型地址，最好还是不要用异或
        int temp = arr[L];
        arr[L] = arr[R];
        arr[R] = temp;
    }

    /**
     * 简易版，帮助理解
     *
     * @param arr   数组
     * @param L     左指针
     * @param R     右指针
     * @param index 一个数组中第K小的数,比如第5,
     *              那么它所在数组下标应该为 5 - 1 = 4
     *              index传参4
     *              index 范围应该在L..R之间
     */
    public static int bfprt(int[] arr, int L, int R, int index) {
        if (L == R) {
            // 潜台词 L == R == index
            // 因为index 在L..R之间
            return arr[index];
        }

        // bfprt算法取划分值 不会打太偏
        int pivot = midpointOfMiddle(arr, L, R);

        // 返回等于的左右边界
        int[] range = netherLands(arr, L, R, pivot);

        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            // 去左侧递归
            return bfprt(arr, L, range[0] - 1, index);
        } else {
            // 去右侧递归
            return bfprt(arr, range[1] + 1, R, index);
        }
    }

    private static int midpointOfMiddle(int[] arr, int L, int R) {
        // 逻辑分N个区域 每个区域5个数值 排序取中点
        int size = R - L + 1;

        // 数组不满的情况下 还是要申请一个数组存剩下的值
        int offset = size % 5 == 0 ? 0 : 1;

        // mid arr 申请空间
        int[] midArr = new int[size / 5 + offset];

        for (int temp = 0; temp < midArr.length; temp++) {
            int left = L + (temp * 5);
            // right 右边界可能不满一个完整长度 与最大右边界坐下边界判断
            int right = Math.min(left + 4, R);
            // 五个小数组排序
            midArr[temp] = getMiddle(arr, left, right);
        }
        // 中位数组取中点作为返回值
        return bfprt(midArr, 0, midArr.length - 1, midArr.length / 2);
    }

    private static int getMiddle(int[] arr, int L, int R) {
        // 数组排序
        // 数量级比较小，选择插入排序
        insertionSort(arr, L, R);
        // 返回中点,此时数组长度一般为5
        return arr[(L + R) / 2];
    }

    private static void insertionSort(int[] arr, int L, int R) {
        // L       R
        // 6 7 2 8 3
        //       i
        //   j
        for (int i = L + 1; i < R; i++) {
            for (int j = i - 1; j >= L && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    public static void testNetherLands() {
        int[] arr = {5, 7, 5, 4, 8};
        int[] resArr = netherLands(arr, 0, arr.length - 1, 5);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(resArr));
    }

    public static void testProcess() {
        System.out.println("Random");
//        int[] arr = {1, 2, 2, 2, 2, 3};
        int[] arr = {5, 8, 4, 6, 7};
        // 假设有序数组
        // 找第 3 小的数
        // 索引所在会是 2
        int res = process(arr, 0, arr.length - 1, 2);

        // 找第 2 大的数
        // 索引会是 arr.length - 2
//        int res = process(arr, 0, arr.length - 1, arr.length - 2);
        System.out.println(Arrays.toString(arr));
        System.out.println(res);
    }

    public static void testBfprt() {
        System.out.println("bfprt");
//        int[] arr = {1, 2, 2, 2, 2, 3};
        int[] arr = {5, 8, 4, 6, 7};
        // 假设有序数组
        // 找第 3 小的数
        // 索引所在会是 2
        int res = bfprt(arr, 0, arr.length - 1, 2);

        // 找第 2 大的数
        // 索引会是 arr.length - 2
//        int res = process(arr, 0, arr.length - 1, arr.length - 2);
        System.out.println(Arrays.toString(arr));
        System.out.println(res);
    }

    public static void main(String[] args) {
//        testNetherLands();
        testProcess();
        testBfprt();
    }
}
