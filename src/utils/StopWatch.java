package utils;

/**
 * @Auther: 苏察哈尔丶灿
 * @Date: 2020/10/18 16:17
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class StopWatch {
    private static long startTime = 0;
    private static long stopTime = 0;

    public static void start() {
        reset();
        startTime = System.currentTimeMillis();
    }

    public static void stop() {
        stopTime = System.currentTimeMillis();
        System.out.println("耗时: " + (stopTime - startTime) + " ms");
    }

    private static void reset() {
        startTime = 0;
        stopTime = 0;
    }

}
