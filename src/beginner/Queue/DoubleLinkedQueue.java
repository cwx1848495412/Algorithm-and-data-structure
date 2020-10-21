package beginner.Queue;

/**
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/8/23 22:25
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class DoubleLinkedQueue<T> {
    public Node<T> head;
    public Node<T> tail;

    /**
     * 头部添加
     *
     * @param value
     */
    public void addFromHead(T value) {
        Node<T> currentNode = new Node<T>(value);
        if (head == null) {
            head = tail = currentNode;
        } else {
            currentNode.next = head;
            head.pre = currentNode;
            head = currentNode;
        }
    }

    /**
     * 尾部添加
     *
     * @param value
     */
    public void addFromBottom(T value) {
        Node<T> currentNode = new Node<T>(value);
        if (head == null) {
            head = tail = currentNode;
        } else {
            tail.next = currentNode;
            currentNode.pre = tail;
            tail = currentNode;
        }
    }

    /**
     * 头部移除
     *
     * @return
     */
    public T popFromHead() {
        if (head == null) {
            return null;
        }

        Node<T> currentNode = head;

        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.pre = null;
            // 消除引用
            currentNode.next = null;
        }

        return currentNode.value;
    }

    /**
     * 尾部移除
     *
     * @return
     */
    public T popFromBottom() {
        if (tail == null) {
            return null;
        }

        Node<T> currentNode = tail;

        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.pre;
            tail.next = null;
            // 消除引用
            currentNode.pre = null;
        }

        return currentNode.value;
    }
}


/**
 * 节点
 *
 * @param <T>
 */
class Node<T> {
    public T value;
    public Node<T> pre;
    public Node<T> next;

    public Node(T value) {
        this.value = value;
    }
}
