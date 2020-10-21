package beginner.linked;


/**
 * 快慢指针
 *
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/9/21 21:19
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class FastSlowPoint {

    private static class Node<T> {
        public T value;
        public Node<T> next;
    }

    // 求链表中点 中点或上中点
    public Node minOrUp(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    /**
     * 获取入环的第一个节点或者null
     * 追击问题
     *
     * @param head
     * @return
     */
    public Node getLoopNode(Node head){

        if(head == null || head.next == null || head.next.next == null){
            return null;
        }

        Node slow = head.next;
        Node fast = head.next.next;

        while (slow != fast){
            if(fast.next == null || fast.next.next == null){
                return null;
            }

            slow = slow.next;
            fast = fast.next.next;
            // 相交 出环
        }

        // 继续一次走一步 直至相遇  得到第一点相交
        // 追击问题可证明
        fast = head;
        while (slow != fast){
            fast = fast.next;
            slow = slow.next;
        }

        return fast;
    }
}
