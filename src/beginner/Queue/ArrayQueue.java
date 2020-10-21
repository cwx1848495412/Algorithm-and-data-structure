package beginner.Queue;

import java.util.Arrays;

/**
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/8/24 19:10
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class ArrayQueue {
    private int[] arr;


    private int pushi;

    private int popi;

    private int size;

    private final int limit;

    public ArrayQueue(int limit) {
        arr = new int[limit];
        pushi = popi = size = 0;
        this.limit = limit;
    }

    public void push(int value) {
        if (size == limit) {
            throw new RuntimeException("栈满了 不能再加了");
        }

        size++;
        arr[pushi] = value;
        pushi = nextIndex(pushi);
    }

    public int pop() {
        if (size == 0) {
            throw new RuntimeException("栈空了 不能再拿了");
        }

        size--;
        int res = arr[popi];
        System.out.println("取值的下标位：" + popi + "取的值位：" + res);
        popi = nextIndex(popi);
        return res;
    }

    private int nextIndex(int i) {
        return i < limit - 1 ? i + 1 : 0;
    }

    public static void main(String[] args) {
        ArrayQueue as = new ArrayQueue(10);

        as.push(1);
        as.push(2);
        as.push(3);
        as.push(4);
        as.push(5);
        as.push(6);
        as.push(7);
        as.push(8);
        as.push(9);
        as.push(10);
        System.out.println(as.pop());
        System.out.println(as.pop());
        System.out.println(as.pop());
        as.push(11);
        as.push(12);
        as.push(13);
        System.out.println(as.pop());
        System.out.println(as.pop());
        System.out.println(as.pop());
        System.out.println(as.pop());
        System.out.println(as.pop());
        System.out.println(as.pop());
        System.out.println(as.pop());
        System.out.println(as.pop());
        System.out.println(as.pop());
        System.out.println(as.pop());
    }

}
