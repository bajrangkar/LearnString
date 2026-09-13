package learn.strings.set1;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Level3 {
    static void main(String[] args) {

        String sentence = "The brown dog is a brown cat";
        Supplier<Stream<String>> streamSupplier = getStream(sentence);
        long countOfWords = streamSupplier.get().count();
        System.out.println("1. Word Count :: " + countOfWords);

        sentence = "java spring boot";
        streamSupplier = getStream(sentence);
        System.out.print("2. Upper case words :: ");
        streamSupplier.get()
                .map(str -> str.toUpperCase())
                .forEach(str -> System.out.print(str + " "));

        sentence = "apple banana application amazon orange";
        streamSupplier = getStream(sentence);
        System.out.print("\n3. Words starting with 'a' :: ");
        streamSupplier.get()
                .filter(str -> str.startsWith("a"))
                .forEach(str -> System.out.print(str + " "));

        sentence = "java python spring kotlin";
        streamSupplier = getStream(sentence);
        System.out.print("\n4. Words ending with 'n' :: ");
        streamSupplier.get()
                .filter(str -> str.endsWith("n"))
                .forEach(str -> System.out.print(str + " "));

        sentence = "Java Spring Boot Microservices";
        streamSupplier = getStream(sentence);
        streamSupplier.get()
                .max(Comparator.comparingInt(String::length))
                .ifPresent(str -> System.out.print("\n5. Longest word :: " + str));

        sentence = "Java is a powerful language";
        streamSupplier = getStream(sentence);
        streamSupplier.get()
                .min(Comparator.comparingInt(String::length))
                .ifPresent(str -> System.out.print("\n6. Shortest word :: " + str));

        sentence = "spring java kafka docker";
        System.out.print("\n7. Sorted words Alphabetically:: ");
        getStream(sentence).get()
                .sorted()
                .forEach(str -> System.out.print(str + " "));

        sentence = "Java Spring Boot Microservices";
        System.out.print("\n8. Sorted words by Length:: ");
        getStream(sentence).get()
                .sorted(Comparator.comparingInt(String::length))
                .forEach(str -> System.out.print(str + " "));

        sentence = "Java Spring Boot Microservices Docker Kubernetes";
        System.out.print("\n9. Words having Length greater than 5 :: ");
        getStream(sentence).get()
                .filter(str -> str.length() > 5)
                .forEach(str -> System.out.print(str + " "));

        sentence = "Java is a powerful programming language";
        System.out.print("\n10. Words count by length :: ");
        getStream(sentence).get()
                .collect(Collectors.groupingBy(str -> str.length(), Collectors.counting()))
                .entrySet()
                .stream()
                .forEach(e -> System.out.print(e + " "));

    }

    static Supplier<Stream<String>> getStream(String sentence) {
        return () -> Arrays.stream(sentence.split("\\s+"));
    }
}
