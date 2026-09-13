package learn.strings.set1;

import java.sql.SQLOutput;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Level1 {
    static void main(String[] args) {
        String str = "Hello";
        int len = str.length();

        String revStr = IntStream.range(0, len)
                .mapToObj(i -> String.valueOf(str.charAt(len - 1 - i)))
                .collect(Collectors.joining());
        System.out.printf("Reverse String of String %s is :: %s", str, revStr);

        String uppercase = str.chars()
                .mapToObj(c -> String.valueOf((char) c).toUpperCase())
                .collect(Collectors.joining());
        System.out.printf("%n Uppercase String of String %s is :: %s", str, uppercase);

        String lowercase = str.chars()
                .mapToObj(c -> String.valueOf((char) c).toLowerCase())
                .collect(Collectors.joining());
        System.out.printf("%n Lowercase String of String %s is :: %s", str, lowercase);

        long count = str.chars().count();
        System.out.printf("%n Count of characters in String %s is :: %d", str, count);

        String str2 = "Hello World";
        long numberOfVowels = str2.chars()
                .mapToObj(ch -> String.valueOf((char)ch).toLowerCase())
                .filter(ch -> "aeiou".indexOf(ch) >= 0)
                .count();
        System.out.printf("%n Number of Vowels in string %s is :: %d", str2, numberOfVowels);

        long numberOfConsonants = str2.chars()
                .filter(ch -> Character.isLetter(ch))
                .mapToObj(ch -> String.valueOf((char)ch).toLowerCase())
                .filter(ch -> "aeiou".indexOf(ch) < 0)
                .count();
        System.out.printf("%n Number of Consonants in string %s is :: %d", str2, numberOfConsonants);

        System.out.printf("%n All characters in string %s is :: ", str2);
        str2.chars()
                .mapToObj(ch -> (char)ch)
                .forEach(ch -> System.out.printf("%c ", ch));

        System.out.printf("%n Distinct characters in string %s is :: ", str2);
        str2.chars()
                .mapToObj(ch -> String.valueOf((char)ch).toLowerCase())
                .distinct()
                .forEach(ch -> System.out.printf("%s ", ch));

        String distinctCharacters = str2.chars()
                .mapToObj(ch -> String.valueOf((char)ch).toLowerCase())
                .distinct()
                .collect(Collectors.joining());
        System.out.printf("%n Distinct characters in string %s is :: %s", str2, distinctCharacters);

        System.out.printf("%n Even indexed characters in string %s is :: ", str2);
        IntStream.range(0, str2.length())
                .filter(i -> i % 2 == 0)
                .mapToObj(i -> str2.charAt(i))
                .forEach(ch -> System.out.printf("%c ", ch));



    }
}
