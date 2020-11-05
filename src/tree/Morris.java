package tree;

/**
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/11/5 10:21
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class Morris {
    class Node {
        int data;
        Node left;
        Node right;
    }

    public void process(Node head) {
        if (head == null) {
            return;
        }

        Node cur = head;
        Node mostRight = null;

        while (cur != null) {

            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != cur && mostRight.right != null) {
                    mostRight = mostRight.right;
                }

                if (mostRight.right == cur) {
                    mostRight.right = null;
                    cur = cur.right;
                    continue;
                } else if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }
            }

            cur = cur.right;
        }
    }

    /**
     * 判断是否是搜索二叉树
     * 原理 中序遍历 判断值是否一直递增
     * 比二叉树的递归更好用
     *
     * @param head
     */
    public boolean isBST(Node head) {
        if (head == null) {
            return true;
        }

        Node cur = head;
        Node mostRight = null;

        Integer pre = null;
        while (cur != null) {

            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != cur && mostRight.right != null) {
                    mostRight = mostRight.right;
                }

                if (mostRight.right == cur) {
                    mostRight.right = null;
                    cur = cur.right;
                    continue;
                } else if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }
            }

            if (pre != null && pre >= cur.data) {
                return false;
            }
            pre = cur.data;

            cur = cur.right;
        }
        return true;
    }
}
