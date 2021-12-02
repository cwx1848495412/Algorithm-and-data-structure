package sort;

import java.util.Arrays;

/**
 * @author: 苏察哈尔丶灿
 * @create: 2021-12-01 16:07
 */
public class Heap {
    private static void swap(int[] arr, int p1, int p2) {
        if (p1 == p2) return;
        arr[p1] = arr[p1] ^ arr[p2];
        arr[p2] = arr[p1] ^ arr[p2];
        arr[p1] = arr[p1] ^ arr[p2];
    }

    /**
     * 入堆
     *
     * @param arr
     * @param index
     */
    private static void heapInsert(int[] arr, int index) {
        int parentIndex = (index - 1) / 2;
        while (arr[index] > arr[parentIndex]) {
            swap(arr, index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    private static void heapDown(int[] arr, int index, int heapSize) {
        int leftIndex = index * 2 + 1;

        while (leftIndex < heapSize) {
            int rightIndex = leftIndex + 1;

            int largeIndex = rightIndex < heapSize && arr[rightIndex] > arr[leftIndex] ? rightIndex : leftIndex;

            largeIndex = arr[index] > arr[largeIndex] ? index : largeIndex;
            if (index == largeIndex) break;
            swap(arr, index, largeIndex);
            index = largeIndex;
            leftIndex = largeIndex * 2 + 1;
        }
    }

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        // N * logN
//        for (int i = 0; i < arr.length; i++) {
//            heapInsert(arr, i);
//        }

        // N
        // 叶节点的数量 N/2
        for (int i = arr.length - 1; i >= 0; i--) {
            heapDown(arr, i, arr.length);
        }

        int heapSize = arr.length;
        swap(arr, 0, --heapSize);

        while (heapSize > 0) {
            heapDown(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 290, 39, 128, 54, 38, 94, 20, 19, 94, 72};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
