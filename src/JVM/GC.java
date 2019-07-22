package JVM;

public class GC {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
//        testAllocation();
//        testPretenureSizeThreshold();
        testPretenureSizeThreshold2();
    }

    public static void testAllocation() {
        byte[] a1, a2, a3, a4;
        a1 = new byte[2 * _1MB];
        a2 = new byte[2 * _1MB];
        a3 = new byte[2 * _1MB];
        a4 = new byte[4 * _1MB];
    }

    public static void testPretenureSizeThreshold() {
        byte[] allocation;
        allocation = new byte[4 * _1MB];
    }

    public static void testPretenureSizeThreshold2() {
        byte[] a1, a2, a3, a4;

        a1 = new byte[_1MB / 4];
        a2 = new byte[_1MB / 4];
        a3 = new byte[4 * _1MB];
        a4 = new byte[4 * _1MB];
        a4 = null;
        a4 = new byte[4 * _1MB];
    }
}
