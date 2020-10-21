package beginner.tree;

/**
 * 最大的子搜索二叉树的大小
 * Dynamic Programming
 *
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/9/27 16:25
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class MaxSubSBTSize {

    // 需要向子树寻求的信息
    private class Info {
        // 子数的最大搜索二叉树值
        public int subBSTSize;
        // 是不是搜索二叉树
        public boolean isBST;
        // 最小值
        public int min;
        // 最大值
        public int max;

        public Info(int subBSTSize, boolean isBST, int min, int max) {
            this.subBSTSize = subBSTSize;
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }

    public Info process(Node node) {

        // 和 【当前node】 无关
        // 我的最大二叉搜索子树大小是我左右儿子的最大值


        // 和 【当前node】 有关
        //  我的左子树是不是搜索二叉树
        //  我的右子树是不是搜索二叉树
        //  我是不是搜索二叉树   左最大值 < 我的值 < 右最小值

        if (node == null) {
            // 不好判断他的info属性 那就甩锅给上层
            // 上层要做好判空
            return null;
        }

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int min = node.value;
        int max = node.value;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
        }
        // 至此 所需要的两个参数获取完成

        // 是不是搜索二叉树
        boolean isBST = false;

        // 可能空指针
        // int subBSTSize = Math.max(leftInfo.subBSTSize, rightInfo.subBSTSize);
        int subBSTSize = 0;
        if (leftInfo != null) {
            // 和0比max 还不如直接替换
            subBSTSize = leftInfo.subBSTSize;
        }
        if (rightInfo != null) {
            // 和0比max 还不如直接替换
            // 但是可能上面逻辑已经给赋值过了 所以这一步替换值有风险  需要判断
            subBSTSize = Math.max(subBSTSize, rightInfo.subBSTSize);
        }

        // 前提条件 空节点默认成立搜索树
        if (
            // 左子树是
                (leftInfo == null ? true : leftInfo.isBST)
                        &&
                        // 右子树是
                        (rightInfo == null ? true : rightInfo.isBST)
                        &&
                        // 左大值<我<右小值
                        (leftInfo == null ? true : leftInfo.max < node.value)
                        &&
                        (rightInfo == null ? true : rightInfo.min > node.value)
        ) {
            // 标记是搜索二叉树
            isBST = true;

            // 我的左右子树都是搜索二叉树了 那么树大小就全等于 两个子树身上搜索二叉树size属性的的大小了
            // 我的最大大小就是他们最大的 + 1
            subBSTSize = (leftInfo == null ? 0 : leftInfo.max) + (rightInfo == null ? 0 : rightInfo.max) + 1;
        }


        // 返回info
        return new Info(subBSTSize, isBST, min, max);
    }

    public static void main(String[] args) {
        MaxSubSBTSize maxSubSBTSize = new MaxSubSBTSize();
        Node head = new Node();// 假设这是一个二叉树头节点引用，补齐懒得写
        Info processInfo = maxSubSBTSize.process(head);
        System.out.println(processInfo.subBSTSize);// 当前节点搜索二叉子树的最大大小
    }
}

// 二叉树节点类型
class Node {
    int value;
    Node left;
    Node right;
}
