package beginner.set;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 并查集
 *
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/9/29 14:45
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class UnionSet<T> {

    // 节点元素
    private class Node<V> {
        V value;

        public Node(V val) {
            value = val;
        }
    }

    // 节点元素map
    // v -> node
    private Map<T, Node<T>> nodeMap;

    // parent map
    // son -> parent
    private Map<Node<T>, Node<T>> parentMap;

    // 代表点大小(几个代表点击就代表最终有几个集合)
    // node -> number
    private Map<Node<T>, Integer> leaderSizeMap;

    public UnionSet(List<T> dataList) {
        // 初始化每个节点元素
        for (T data : dataList) {
            Node<T> node = new Node<T>(data);
            // 通过此map可以获取到并查集中的节点元素
            nodeMap.put(data, node);
            // 通过此map可以获取到并查集中的节点元素的父元素
            parentMap.put(node, node);
            // 设置此节点元素所在的代表节点的大小，即同集合中的元素个数
            leaderSizeMap.put(node, 1);
        }
    }

    // 找到当前节点的代表节点
    private Node<T> findLeaderNode(Node<T> node) {
        // O(1) 优化点
        Stack<Node<T>> path = new Stack<Node<T>>();
        while (parentMap.get(node) != node) {
            // 所有路过的父节点记载下来，稍后更改父节点指针指向头节点
            // 将链表扁平化
            path.push(node);
            node = parentMap.get(node);
        }
        // O(1) 优化点
        while (!path.empty()) {
            // 改父节点指针指向头节点,将链表扁平化
            parentMap.put(path.pop(), node);
        }

        return node;
    }

    // 判断两个数据是否是同一集合
    public boolean isSameSet(T dataA, T dataB) {
        if (!nodeMap.containsKey(dataA) || !nodeMap.containsKey(dataB)) {
            return false;
        }

        // 代表点相同为 true
        return findLeaderNode(nodeMap.get(dataA)) == findLeaderNode(nodeMap.get(dataB));
    }

    // 合并集合
    public void unionSet(T dataA, T dataB) {
        if (!nodeMap.containsKey(dataA) || !nodeMap.containsKey(dataB)) {
            return;
        }

        // 找到各自代表点
        Node<T> aHead = findLeaderNode(nodeMap.get(dataA));
        Node<T> bHead = findLeaderNode(nodeMap.get(dataB));

        // 已经相同，同属一个集合了
        if (aHead == bHead) {
            return;
        }
        // 否则 合并

        // 优化 小的往大的上面合
        Node<T> bigHead = leaderSizeMap.get(aHead) > leaderSizeMap.get(bHead) ? aHead : bHead;
        Node<T> smallHead = bigHead == aHead ? bHead : aHead;

        // 设置小头的父元素为大头
        parentMap.put(smallHead,bigHead);
        // 更新大头的size 大节点大小+ 小节点大小
        leaderSizeMap.put(bigHead,leaderSizeMap.get(bigHead) + leaderSizeMap.get(smallHead));
        // 小头不再是头元素,从map中移除
        leaderSizeMap.remove(smallHead);
    }
}
