package class004;

/**
 * @author: 苏察哈尔丶灿
 * @create: 2021-11-29 14:51
 */
public class Linked {

    /**
     * 单链表反转
     * 时间O(n)
     * 空间O(1)
     *
     * @param head
     * @return
     */
    public static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node nextEnv = null;
        while (head != null) {
            nextEnv = head.next;
            head.next = pre;
            pre = head;
            head = nextEnv;
        }

        return pre;
    }

    /**
     * 双链表反转
     * 时间O(n)
     * 空间O(1)
     *
     * @param head
     * @return
     */
    public static DNode reverseDoubleLinkedList(DNode head) {
        DNode pre = null;
        DNode nextEnv = null;
        while (head != null) {
            nextEnv = head.next;

            head.pre = nextEnv;
            head.next = pre;

            pre = head;
            head = nextEnv;
        }

        return pre;
    }

    /**
     * 删除节点中的某值
     * 时间O(n)
     * 空间O(1)
     *
     * @param head
     * @param num
     * @return
     */
    public static Node removeValue(Node head, int num) {
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }

        Node pre = head;
        Node cur = head;

        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }

            cur = cur.next;
        }

        return head;
    }


}
