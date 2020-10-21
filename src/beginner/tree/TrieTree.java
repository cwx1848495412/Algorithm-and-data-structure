package beginner.tree;

import java.lang.reflect.Proxy;

/**
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/8/27 09:49
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class TrieTree {
    // 节点类
    private static class Node {
        public Character value;
        public int pass;
        public int end;
        public Node[] nexts;

        public Node() {
            value = null;
            pass = 0; // 通过的次数
            end = 0; // 截至的次数
//            char - a = 索引下标值
            nexts = new Node[26];
        }

        // 返回路径
        private int getPath(char ch) {
            return ch - 'a';
        }
    }

    // 根节点
    private Node root;

    public TrieTree() {
        root = new Node();
    }

    public void insert(String string) {
        if (string.isEmpty()) return;
        // 转小写
        char[] chars = string.toLowerCase().toCharArray();

        // 从根节点开始扫面
        Node node = root;
        node.pass++;

        for (int i = 0; i < chars.length; i++) {
            // 下面哪条分叉路为空
            int path = node.getPath(chars[i]);
            if (node.nexts[path] == null) {
                // 新建一条路
                node.nexts[path] = new Node();
                // 指针移动
                node = node.nexts[path];
                node.value = chars[i];
            }

            node.pass++;
        }

        node.end++;
    }

    // 某个单词加入过几次
    public int search(String string) {
        if (string.isEmpty()) return 0;
        // 转小写
        char[] chars = string.toLowerCase().toCharArray();

        // 从根节点开始扫面
        Node node = root;

        for (int i = 0; i < chars.length; i++) {
            // 下面哪条分叉路为空
            int path = node.getPath(chars[i]);
            if (node.nexts[path] == null) {
                return 0;
            }
            node = node.nexts[path];
        }

        return node.end;
    }

    // 某个单词加入过几次
    public int prefixNumber(String string) {
        if (string.isEmpty()) return 0;
        // 转小写
        char[] chars = string.toLowerCase().toCharArray();

        // 从根节点开始扫面
        Node node = root;

        for (int i = 0; i < chars.length; i++) {
            // 下面哪条分叉路为空
            int path = node.getPath(chars[i]);
            if (node.nexts[path] == null) {
                return 0;
            }
            node = node.nexts[path];
        }

        return node.pass;
    }

    // 删除
    public void delete(String string) {
        if (string.isEmpty()) return;

        if (search(string) != 0) {
            // 转小写
            char[] chars = string.toLowerCase().toCharArray();

            // 从根节点开始扫面
            Node node = root;
            node.pass--;

            for (int i = 0; i < chars.length; i++) {
                // 下面哪条分叉路为空
                int path = node.getPath(chars[i]);
                if (--node.nexts[path].pass == 0) {
                    node.nexts[path] = null;
                    return;
                }
                node = node.nexts[path];
            }

            node.end--;
        }

    }
}
