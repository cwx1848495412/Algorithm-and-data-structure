package sort;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author: 苏察哈尔丶灿
 * @create: 2021-12-01 14:18
 */
public class FastSort {
    static class OP {
        int left;
        int right;

        public OP(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    /**
     * < | = | >
     * 左  中   右
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    private static int[] partition(int[] arr, int L, int R) {
        if (L == R) {
            return new int[]{L, R};
        }

        int less = L - 1;
        int more = R;
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

        swap(arr, more, R);

        return new int[]{less + 1, more};
    }

    private static void swap(int[] arr, int p1, int p2) {
        if (p1 == p2) return;
        arr[p1] = arr[p1] ^ arr[p2];
        arr[p2] = arr[p1] ^ arr[p2];
        arr[p1] = arr[p1] ^ arr[p2];
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        process(arr, 0, arr.length - 1);

    }

    private static void process(int[] arr, int L, int R) {
        if (L > R) return;

        // 随机快排
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);

        int[] partition = partition(arr, L, R);
        process(arr, L, partition[0] - 1);
        process(arr, partition[1] + 1, R);
    }

    public static void quickSortForEach(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int L = 0;
        int R = arr.length - 1;

        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);

        int[] partition = partition(arr, L, R);
        Stack<OP> stack = new Stack<>();
        stack.push(new OP(L, partition[0] - 1));
        stack.push(new OP(partition[1] + 1, R));
        while (!stack.isEmpty()) {
            OP pop = stack.pop();
            if (pop.left < pop.right) {
                swap(arr, pop.left + (int) (Math.random() * (pop.right - pop.left + 1)), pop.right);
                int[] area = partition(arr, pop.left, pop.right);

                stack.push(new OP(pop.left, area[0] - 1));
                stack.push(new OP(area[1] + 1, pop.right));
            }

        }
    }


    public static void main(String[] args) {
        int[] arr = {1, 5, 78, 1, 2, 7, 8, 2, 32, 2};
//        int[] partition = partition(arr, 0, arr.length - 1);

        quickSortForEach(arr);
        System.out.println(Arrays.toString(arr));
    }
}
