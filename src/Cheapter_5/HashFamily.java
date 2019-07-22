package Cheapter_5;

public interface HashFamily<String> {
    int hash(String x, int which);
    int getNumberOfFunctions();
    void generateNewFunctions();
}
