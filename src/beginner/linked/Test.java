package beginner.linked;

/**
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/8/19 21:20
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class Test {
    public static void main(String[] args) {
        // 这个数组里面  有且只有两个数字出现了 奇数次
        // 分别找出这两个数字
        int[] arr = {1,1,1,1,2,2,3,4};

        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        // a ^ b; eor = c
        // 110100
        // 000100 = tmp = 十进制值
        int tmp = eor & ((~eor) + 1);

        int eor2 = 0;// a or b
        for (int i = 0; i < arr.length; i++) {
            if((arr[i] & tmp ) == 0){
                eor2 ^= arr[i];
            }
        }

        System.out.println(eor2);
        System.out.println(eor ^ eor2);

    }

    public static void test1(){

        // 这个数组里面  有且只有一个数字出现了 奇数次  找出这个数字
        int[] arr = {1,6,1,2,6,2,3,7,3};

        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }

        System.out.println(eor);
    }
}
