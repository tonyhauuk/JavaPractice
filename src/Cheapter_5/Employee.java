package Cheapter_5;

public class Employee {
    private String name;
    private double salary;
    private int seniority;

    public boolean equals(Object rhs) {
        return rhs instanceof Employee && name.equals(((Employee) rhs).name);
    }

    public int hashCode() {
        return name.hashCode();
    }

    public static int hash3(String key, int tableSize) {
        int hashVal = 0;

        for (int i = 0; i < key.length(); i++)
            hashVal = 37 * hashVal + key.charAt(i);


        return hashVal;
    }
}
