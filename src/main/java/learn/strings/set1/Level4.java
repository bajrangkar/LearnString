package learn.strings.set1;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Level4 {
    static void main(String[] args) {
        System.out.println("**** 1. Word Frequency ****");
        String sentence = "the brown dog is a the brown cat";
        getStream(sentence)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .forEach(System.out::println);

        System.out.println("**** 2. Most Frequent Word ****");
        sentence = "java spring java kafka spring java";
        getStream(sentence)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(System.out::println);

        System.out.println("**** 3. Least Frequent Word ****");
        getStream(sentence)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .ifPresent(System.out::println);

        System.out.println("**** 4. Duplicate Words ****");
        getStream(sentence)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .forEach(System.out::println);

        System.out.println("**** 5. Unique Words ****");
        getStream(sentence)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .forEach(System.out::println);

        System.out.println("**** 6. First Non-repeated Words ****");
        getStream(sentence)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .findFirst()
                .ifPresent(System.out::println);

        System.out.println("**** 7. First Repeated Words ****");
        getStream(sentence)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .findFirst()
                .ifPresent(System.out::println);

        System.out.println("**** 8. Group words by Length ****");
        sentence = "cat dog apple banana java";
        getStream(sentence)
                .collect(Collectors.groupingBy(str -> str.length()))
                .entrySet().stream()
                .forEach(System.out::println);

        System.out.println("**** 9. Group words by First Character ****");
        sentence = "apple amazon banana ball cat";
        getStream(sentence)
                .collect(Collectors.groupingBy(str -> str.charAt(0)))
                .entrySet().stream()
                .forEach(System.out::println);

        System.out.println("**** 10. Longest Word for Each Starting Character ****");
        sentence = "apple amazon banana ball cat";
        getStream(sentence)
                .collect(Collectors.groupingBy(str -> str.charAt(0), Collectors.maxBy(Comparator.comparingInt(String::length))))
                .entrySet().stream()
                .forEach(e->  System.out.println(e.getKey() + "->" +  e.getValue().get()));

        getStream(sentence)
                .collect(Collectors.groupingBy(str -> str.charAt(0),
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(String::length)),
                                Optional::get
                        )
                ))
                .entrySet().stream()
                .forEach(System.out::println);
    }

    static Stream<String> getStream(String sentence) {
        return Stream.of(sentence.split("\\s+"));
    }
}
