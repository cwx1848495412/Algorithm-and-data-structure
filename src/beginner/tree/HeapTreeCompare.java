package beginner.tree;

import java.util.ArrayList;

@FunctionalInterface
interface MyCompare<T> {
    boolean compareTo(T o1, T o2);
}

/**
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/8/26 14:03
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class HeapTreeCompare<T> {

    // 数据
//    private int[] dataArr;
    private ArrayList<T> dataArr = new ArrayList<T>();


    // 堆使用大小
    private int heapSize;

    // 比较器
    private MyCompare compare;


    /**
     * 初始化
     */
    public HeapTreeCompare(MyCompare compare) {
        this.compare = compare;
    }

    public void printArr() {
        System.out.println(dataArr);
    }

    public void printHeapSize() {
        System.out.println("堆中元素个数\t" + heapSize);
    }


    public boolean isEmpty() {
        return heapSize == 0;
    }


    public void push(T value) {

        // 给数组赋值
//        dataArr[heapSize] = value;
        dataArr.add(heapSize, value);

        heapInsert(heapSize++);
    }

    private void heapInsert(int index) {

        int parentIndex = getParentIndex(index);

        // 调整堆中位置
//        while (dataArr[index] > dataArr[parentIndex]) {
        while (compare.compareTo(dataArr.get(index), dataArr.get(parentIndex))) {
            swap(index, parentIndex);

            // 当前节点指针指向父节点
            index = parentIndex;
            // 父节点指针更新
            parentIndex = getParentIndex(index);
        }

    }

    /**
     * 返回最大值，并且重新调整堆区结构
     */
    public T pollMax() {
        if (isEmpty()) {
            throw new RuntimeException("为空，无法弹出");
        }

//        int result = dataArr[0];

        T result = dataArr.get(0);
        swap(0, --heapSize);
        heapUpdate(0);
        return result;
    }

    /**
     * 更新堆结构
     */
    private void heapUpdate(int index) {
        int leftIndex = getLeftIndex(index);
        int rightIndex = getRightIndex(index);

        while (leftIndex < heapSize) {
            // 左子树有值才进来
            // 选择左右大的去与当前比较
//            int changeIndex = (rightIndex < heapSize && dataArr[rightIndex] > dataArr[leftIndex]) ? rightIndex : leftIndex;
//            changeIndex = dataArr[index] > dataArr[changeIndex] ? index : changeIndex;

            int changeIndex = (rightIndex < heapSize && compare.compareTo(dataArr.get(rightIndex), dataArr.get(leftIndex))) ? rightIndex : leftIndex;
            changeIndex = compare.compareTo(dataArr.get(index), dataArr.get(changeIndex)) ? index : changeIndex;

            if (changeIndex == index) {
                // 无需交换
                break;
            }

            swap(index, changeIndex);

            // 更换值，重新循环
            index = changeIndex;
            leftIndex = getLeftIndex(index);
            rightIndex = getRightIndex(index);
        }
    }

    /**
     * 交换数组指定下标的两个值
     *
     * @param L 交换下标一
     * @param R 交换下标二
     */
    private void swap(int L, int R) {
//        int tmp = dataArr[L];
//        dataArr[L] = dataArr[R];
//        dataArr[R] = tmp;

        T tmp = dataArr.get(L);
        dataArr.set(L, dataArr.get(R));
        dataArr.set(R, tmp);

    }

    /**
     * 获取左子节点下标
     *
     * @param index 当前下标
     * @return 左子节点下标
     */
    private int getLeftIndex(int index) {
        return 2 * index + 1;
    }

    /**
     * 获取右子节点下标
     *
     * @param index 当前下标
     * @return 右子节点下标
     */
    private int getRightIndex(int index) {
        return 2 * index + 2;
    }

    /**
     * 获取父节点下标
     *
     * @param index 当前下标
     * @return 父节点下标
     */
    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    public static void main(String[] args) {
        HeapTreeCompare<Integer> heapTree =
                new HeapTreeCompare<Integer>(new MyCompare<Integer>() {
                    @Override
                    public boolean compareTo(Integer o1, Integer o2) {
                        // 大顶堆
//                        return o1 > o2 ? true : false;

                        // 小顶堆
                        return o1 < o2 ? true : false;
                    }
                });

        heapTree.push(3);
        heapTree.push(4);
        heapTree.push(2);
        heapTree.push(1);
        heapTree.push(5);

        heapTree.printArr();
        heapTree.printHeapSize();
        System.out.println("-------------");
        heapTree.pollMax();
        heapTree.printArr();
        heapTree.printHeapSize();
    }
}
