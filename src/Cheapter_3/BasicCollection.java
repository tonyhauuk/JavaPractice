package Cheapter_3;

import java.util.*;

public class BasicCollection {
    public static void main(String[] args) {
//        testSet();
//        System.out.println(oneCharOff("Wine", "none"));
        List<String> lst = new ArrayList<>();
        lst.add("vine");
        lst.add("nine");
        lst.add("none");
        lst.add("doctor");
        Map<String, List<String>> map = computeAdjacentWords1(lst);
        System.out.println(map);
    }

    private static void testSet() {
        testSet("Hello");
    }

    private static void testSet(String s) {
        Set<String> set = new TreeSet<>(new CaseInsensitiveCompare());
        set.add(s);
        set.add(s);

        System.out.println("The size is: " + set.size());
    }

    public static void printHighChangeables(Map<String, List<String>> adjWords, int minWords) {
        for (Map.Entry<String, List<String>> entry : adjWords.entrySet()) {
            List<String> words = entry.getValue();

            if (words.size() >= minWords) {
                System.out.println(entry.getKey() + " (");
                System.out.println(words.size() + "): ");
                for (String w : words)
                    System.out.println(" " + w);
                System.out.println();
            }
        }
    }

    private static boolean oneCharOff(String word1, String word2) {
        if (word1.length() != word2.length())
            return false;

        int diffs = 0;

        for (int i = 0; i < word1.length(); i++)
            if (word1.charAt(i) != word2.charAt(i))
                if (++diffs > 1)
                    return false;
        return diffs == 1;
    }

    public static Map<String, List<String>> computeAdjacentWords(List<String> theWords) {
        Map<String, List<String>> adjWords = new TreeMap<>();
        Map<Integer, List<String>> wordsLength = new TreeMap<>();

        for (String w : theWords)
            update2(wordsLength, w.length(), w);

        String[] words = new String[theWords.size()];

        theWords.toArray(words);
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (oneCharOff(words[i], words[j])) {
                    update(adjWords, words[i], words[j]);
                    update(adjWords, words[j], words[i]);
                }
            }
        }
        return adjWords;
    }

    public static Map<String, List<String>> computeAdjacentWords1(List<String> words) {
        Map<String, List<String>> adjWords = new TreeMap<>();
        Map<Integer, List<String>> wordsByLength = new TreeMap<>();

        for (String w : words)
            update2(wordsByLength, w.length(), w);

        for (Map.Entry<Integer, List<String>> entry : wordsByLength.entrySet()) {
            List<String> groupWords = entry.getValue();
            int groupNum = entry.getKey();

            for (int i = 0; i < groupNum; i++) {
                Map<String, List<String>> repToWord = new TreeMap<>();
                for (String str : groupWords) {
                    String rep = str.substring(0, i) + str.substring(i + 1);
                    update(repToWord, rep, str);
                }
                for (List<String> wordClique : repToWord.values()) {
                    if (wordClique.size() >= 2)
                        for (String s1 : wordClique)
                            for (String s2 : wordClique)
                                if (s1 != s2)
                                    update(adjWords, s1, s2);
                }
            }
        }
        return adjWords;
    }

    private static void update(Map<String, List<String>> m, String key, String value) {
        List<String> lst = m.get(key);
        if (lst == null) {
            lst = new ArrayList<>();
            m.put(key, lst);
        }
        lst.add(value);
    }

    private static void update2(Map<Integer, List<String>> m, Integer key, String value) {
        List<String> lst = m.get(key);
        if (lst == null) {
            lst = new ArrayList<>();
            m.put(key, lst);
        }
        lst.add(value);
    }
}

class CaseInsensitiveCompare implements Comparator<String> {

    public int compare(String lhs, String rhs) {
        return lhs.compareToIgnoreCase(rhs);
    }
}
