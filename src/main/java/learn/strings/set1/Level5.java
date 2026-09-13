package learn.strings.set1;

import java.sql.SQLOutput;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Level5 {
    static void main(String[] args) {

        String str1 = "listen";
        String str2 = "silent";

        System.out.println("1. Anagram Check ");
        boolean result = str1.chars().sorted().boxed().toList()
                .equals( str2.chars().sorted().boxed().toList());
        System.out.println(result);

        System.out.println("2. All Anagram Words ");
        String[] strArr = {"listen", "silent", "enlist", "rat", "tar", "art", "java"};
        Map<String, List<String>> stringMap = Arrays.stream(strArr)
                .collect(Collectors.groupingBy(
                        str -> str.chars().sorted().mapToObj(ch -> String.valueOf((char)ch)).collect(Collectors.joining()),
                        Collectors.toList()));
        System.out.println(stringMap);

        // 3. Group of Words, that are anagrams - Same as 2

        System.out.println("4. Palindromic Strings");
        strArr = new String[]{"level", "java", "radar", "spring", "madam"};
        Arrays.stream(strArr)
                .filter(word -> isPalindrome(word.toLowerCase()))
                .forEach(System.out::println);

        System.out.println("5. Longest Palindromic String");
        strArr = new String[]{"java", "level", "racecar", "spring", "madam"};
        Arrays.stream(strArr)
                .filter(word -> isPalindrome(word.toLowerCase()))
                .max(Comparator.comparingInt(String::length))
                .ifPresent(System.out::println);

        System.out.println("6. Top K Frequent Words");
        String sentence = "java spring java kafka spring java docker kafka";
        strArr = sentence.split("\\s+");
        Arrays.stream(strArr)
                .map(word -> word.toLowerCase())
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(2)
                .forEach(System.out::println);

        System.out.println("7. Second Most Frequent Character");
        String word = "aabbcccddd";
        word.chars().mapToObj(ch -> (char)ch).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .skip(1)
                .findFirst()
                .ifPresent(System.out::println);

        System.out.println("8. All Characters with Maximum Frequency");
        word.chars().mapToObj(ch -> (char)ch).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .ifPresent(System.out::println);

        System.out.println("9. First Character with Frequency > 1");
        word.chars().mapToObj(ch -> (char)ch).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .findFirst()
                .map(Map.Entry::getKey)
                .ifPresent(System.out::println);

        System.out.println("10. Word Frequency with Multiple COnditions");
        sentence = "Java java SPRING spring boot Boot Kafka kafka kafka";
        Arrays.stream(sentence.split("\\s+"))
                .map(word2 -> word2.toLowerCase())
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()))
                .limit(3)
                .forEach(System.out::println);
    }

    static boolean isPalindrome(String word) {
        return word.equals(
                IntStream.range(0, word.length())
                        .mapToObj(i -> String.valueOf(word.charAt(word.length() - 1 - i)))
                        .collect(Collectors.joining())
        );
    }
}
