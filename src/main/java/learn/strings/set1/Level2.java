package learn.strings.set1;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Level2 {
    static void main(String[] args) {
        String str = "banana";

        Map<Character, Long> freqMap = getFreqMap(str);
        System.out.println("1. Character Frequency " + freqMap);

        freqMap = getFreqMap("swiss");
        freqMap.entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .findFirst()
                .ifPresent(op -> System.out.println("2. First non-repeated character " + op.getKey()));

        freqMap.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .findFirst()
                .ifPresent(op -> System.out.println("3. First repeated character " + op.getKey()));

        freqMap = getFreqMap("programming");
        System.out.print("4. Duplicate characters ");
        freqMap.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .forEach(e -> System.out.print(e.getKey() + " "));

        System.out.print("\n5. Unique characters ");
        freqMap.entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .forEach(e -> System.out.print(e.getKey() + " "));

        freqMap = getFreqMap("success");
        freqMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(e -> System.out.println("\n6. Most frequent character " + e.getKey()));

        freqMap = getFreqMap("banana");
        freqMap.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .ifPresent(e -> System.out.println("7. Least frequent character " + e.getKey()));

        freqMap = getFreqMap("programming");
        System.out.print("8. Character Frequency in sorted order :: ");
        freqMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> System.out.print(e + " "));

        // 19. Character Frequency Preserving Insertion Order - by using LinkedHashMap
        // 20. Maximum Occurring Character - same as Most Frequent Character


    }

    static Map<Character, Long> getFreqMap(String str) {
        return str.chars()
                .mapToObj(ch -> (char)ch)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()));
    }
}
