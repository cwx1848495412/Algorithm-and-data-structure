package beginner.bit;

import java.util.HashMap;

/**
 * @author: 苏察哈尔丶灿
 * @create: 2021-11-26 15:36
 */
public class KMProblem {

    /**
     * 请保证数组中只有一种数出现了K次
     * 其他数出现了M次
     * <p>
     * 且 k<m
     */
    public static int onlyKTimes(int[] arr, int k, int m) {
        int[] t = new int[32];


        // t [0 ...... 31]
        // 0下标为低位
        // 31下标为高位
        for (int num : arr) {
            for (int i = 1; i < 32; i++) {
                t[i] += (num >> i) & 1;
            }
        }

        int ans = 0;

        for (int i = 1; i < 32; i++) {
            if (t[i] % m != 0) {
                // 第i位有1
                ans |= (1 << i);

            }
        }

        return ans;
    }


    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4};
        int i = onlyKTimes(arr, 2, 3);
        System.out.println(i);
    }
}
