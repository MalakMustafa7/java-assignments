package org.example;

import javax.sound.sampled.Line;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.cert.CertPathParameters;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextAnalytics {
    public static Map<String, Long> wordFrequency(Path path) throws IOException{
        return Files.lines(path)
                .flatMap(line-> Arrays.stream(line.split("\\s+")))
                .map(word->word.toLowerCase().replaceAll("[^a-z]",""))
                .filter(word->!word.isBlank())
                .collect(Collectors.groupingBy(word->word,
                        Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String,Long>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a,b)->a,
                        LinkedHashMap::new
                ));
    }
    public static List<String> top10WordFrequency(Path path) throws IOException{
        Set<String> stopWords = Set.of(
                "the", "a", "an", "in", "on", "at",
                "is", "it", "of", "to", "and", "for"
        );
        return Files.lines(path)
                .flatMap(line->Arrays.stream(line.split("\\s+")))
                .map(word->word.toLowerCase().replaceAll("[^a-z]",""))
                .filter(word->!word.isBlank()&&!stopWords.contains(word))
                .collect(Collectors.groupingBy(word->word,
                        Collectors.counting()))
                .entrySet().stream().sorted(Map.Entry.<String,Long>comparingByValue().reversed())
                .limit(10)
                .map(Map.Entry::getKey)
                .toList();


    }
    public static double getAverageWordLength(Path path) throws IOException{
        return Files.lines(path)
                .flatMap(line->Arrays.stream(line.split("\\s+")))
                .mapToDouble(word->word.replaceAll("[^a-z]","").length())
                .average().getAsDouble();
    }
    public static String getLongestSentence (Path path) throws IOException{
        return Files.lines(path)
                .flatMap(line->Arrays.stream(line.split("[.!?]")))
                .map(String::trim)
                .filter(s->!s.isEmpty())
                .max(Comparator.comparingInt(String::length))
                .orElseThrow();
    }

    public static List<String> getLines(Path path,String word) throws IOException{
        AtomicInteger counter = new AtomicInteger(0);
        return Files.lines(path)
                .filter(line->line.contains(word.toLowerCase()))
                .map(line->counter.incrementAndGet()+": "+line)
                .toList();
    }
    public static List<String> getPalindromeLines(Path path,String word) throws IOException{
        return Files.lines(path)
                .map(line->line.toLowerCase().replaceAll("\\s+",""))
                .filter(line->{
                    String reversed = new StringBuilder(line).reverse().toString();
                    return line.equals(reversed);
                })
                .toList();
    }
}
