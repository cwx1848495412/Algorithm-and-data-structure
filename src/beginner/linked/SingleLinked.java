package beginner.linked;

/**
 * 单向链表
 *
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/8/19 20:19
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class SingleLinked {

    /**
     * 单向链表反转
     * @return
     */
    public SingleNode recvSingleList(SingleNode head){
        SingleNode pre = null;
        SingleNode next = null;

        while (head.next != null){
            next = head.next;

            head.next = pre;
            pre = head;

            // 指针向前挪
            head = next;
        }

        return head;
    }

    /**
     * 双向链表反转
     * @return
     */
    public DoubleNode recvDoubleList(DoubleNode head){
        DoubleNode pre = null;
        DoubleNode next = null;

        while (head.next != null){
            next = head.next;

            head.next = pre;
            head.pre = next;

            pre = head;

            // 指针向前挪
            head = next;
        }

        return head;
    }


    /**
     * 删除某个数据
     * @param head
     * @return
     */
    public SingleNode removeValue(SingleNode head, int num){
        // 从链表头开始删
        while (head != null){
            if(head.value != num){
                break;
            }
            head = head.next;
        }

        // head来到第一个不需要删的位置
        SingleNode pre = head;
        SingleNode current = head;
        while (current != null){
            if(current.value != num){
                // 不需要删
                pre = current;
            }else{
                // 需要删
                pre.next = current.next;
            }

            // 当前指针往后挪
            current = current.next;
        }

        return head;
    }
}

class SingleNode {
    public int value = 0;

    public SingleNode next;

    public SingleNode() {
    }

    public SingleNode(int value) {
        this.value = value;
    }
}


class DoubleNode {
    public int value = 0;

    public DoubleNode pre;
    public DoubleNode next;

    public DoubleNode() {
    }

    public DoubleNode(int value) {
        this.value = value;
    }
}


