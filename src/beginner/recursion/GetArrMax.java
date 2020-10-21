package beginner.recursion;

/**
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/8/24 20:10
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class GetArrMax {
    public static int getMax(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }

        int mid = L + ((R - L) >> 1);
        int leftMax = getMax(arr, L, mid);
        int rightMax = getMax(arr, mid + 1, R);

        return Math.max(leftMax, rightMax);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 15, 6, 7, 8};
        System.out.println(getMax(arr,0,arr.length - 1));
    }
}
