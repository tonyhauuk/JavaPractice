package Cheapter_1;

public class MathPow {
    public static void main(String[] args) {
//        int x = 5, y = 4;
//        int ret = pow(x, y);
//        System.out.println(ret);
        System.out.println(even(6));
    }

    public static int pow(int x, int y) {
        if (y == 0)
            return 1;
        if (y == 1)
            return x;
        if (isEven(y))
            return pow(x * x, y / 2);
        else
            return pow(x * x, y / 2) * x;
    }

    public static boolean isEven(int y) {
        if (y % 2 == 0)
            return true;
        else
            return false;
    }

    public static boolean even(int y) {
        return (y%2==0);
    }
}
