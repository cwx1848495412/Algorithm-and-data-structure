package beginner.Queue;

/**
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/8/24 17:20
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class MyStack<T> {
    public DoubleLinkedQueue<T> container = new DoubleLinkedQueue<T>();

    /**
     * 入栈
     *
     * @param value
     */
    public void push(T value) {
        container.addFromHead(value);
    }

    /**
     * 出栈
     */
    public T pop() {
        return container.popFromHead();
    }

    public static void main(String[] args) {
        MyStack<Integer> myStack = new MyStack<Integer>();

        // 双向链表维护栈和队列结构完成

        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
    }
}
