package beginner.tree;

import java.util.Arrays;

/**
 * 给定一个头节点head
 * 和另外两个节点 a b
 * 返回 a 和 b 的最低公共祖先
 *
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/9/28 14:10
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class LowParentNode {
    // 节点类
    private static class Node {
        public Object val;
        public Node left;
        public Node right;
    }

    // 收集的信息
    private static class Info {
        // 最低公共祖先节点引用
        public Node answer;
        // 是否找到node a
        public boolean findA;
        // 是否找到node b
        public boolean findB;

        public Info(Node answer, boolean findA, boolean findB) {
            this.answer = answer;
            this.findA = findA;
            this.findB = findB;
        }
    }

    public static Info process(Node node, Node nodeA, Node nodeB) {
        // 两种情况
        // 第一种 已经拿到交汇点
        //       当前交汇点等于子树拿到的交汇点,往上返,因为取的是最低
        // 第二种 暂未拿到交汇点
        //       1) 在自己和子树中找到了一个节点,自己不会是交汇点,往上返
        //       2) 在自己和子树中找到了两个节点
        //          1> 自己是两个中的某一个,那么另一个就在自己的子树下,自己就是交汇点,往上返
        //          2> 自己不是两个中的任何一个,自己又包含了两个节点,自己的子节点又没有他们的交汇点
        //             那么自己就是这个交汇点了,获取信息,往上返

        if (node == null) {
            return new Info(null, false, false);
        }

        // 本节点要整理上返的信息
        Node answer = null;
        boolean findA = false;
        boolean findB = false;

        Info leftInfo = process(node.left, nodeA, nodeB);
        Info rightInfo = process(node.right, nodeA, nodeB);

        // 当前节点有咩有找到 A / B
        findA = node == nodeA || leftInfo.findA || rightInfo.findA;
        findB = node == nodeB || leftInfo.findB || rightInfo.findB;

        if (leftInfo.answer != null) {
            answer = leftInfo.answer;
        }

        if (rightInfo.answer != null) {
            answer = rightInfo.answer;
        }

        // 经历了上述过程 子树还是没有找到的话
        // 判断自己有没有 是不是符合
        if (answer == null) {
            if (findA && findB) {
                answer = node;
            }
        }

        return new Info(answer, findA, findB);
    }

    public static void main(String[] args) {
//        如此调用 没写树结构代码...
//        Info info = LowParentNode.process(node, node, node);
//        System.out.println(info.answer);
    }

}
