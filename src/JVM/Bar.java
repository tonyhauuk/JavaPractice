package JVM;

public class Bar {
    int a = 1;
    static int b = 2;

    public static void main(String[] args) {
        int d = new Bar().sum(3);
        System.out.println(d);
    }

    public int sum(int c) {
        return a + b + c;
    }

}
