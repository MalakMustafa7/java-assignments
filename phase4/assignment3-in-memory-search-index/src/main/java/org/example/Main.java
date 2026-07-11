package org.example;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SearchIndex index = new SearchIndex();

        // إنشاء Documents
        Document d1 = new Document(
                "1",
                "Java Streams Guide",
                "Learn java streams and collections",
                List.of("java", "programming"),
                LocalDateTime.now()
        );

        Document d2 = new Document(
                "2",
                "Spring Boot API",
                "Build REST API using spring boot",
                List.of("java", "backend"),
                LocalDateTime.now()
        );

        Document d3 = new Document(
                "3",
                "Data Structures",
                "Learn trees graphs and algorithms",
                List.of("cs", "algorithms"),
                LocalDateTime.now()
        );

        // إضافة documents
        index.addDocument(d1);
        index.addDocument(d2);
        index.addDocument(d3);

        // ========================
        //  AND Search
        System.out.println("=== AND Search (java api) ===");
        List<Document> andResult = index.search("java api");
        andResult.forEach(d -> System.out.println(d.getTitle()));

        // ========================
        //  OR Search
        System.out.println("\n=== OR Search (java api) ===");
        List<Document> orResult = index.searchAny("java api");
        orResult.forEach(d -> System.out.println(d.getTitle()));

        // ========================
        // Search by Tag
        System.out.println("\n=== Search by Tag (java) ===");
        List<Document> tagResult = index.searchByTag("java");
        tagResult.forEach(d -> System.out.println(d.getTitle()));

        // ========================
        //  Top words
        System.out.println("\n=== Most Indexed Words ===");
        List<String> topWords = index.getMostIndexedWords(5);
        topWords.forEach(System.out::println);

        // ========================
        //  Remove document
        System.out.println("\n=== After Removing Doc 1 ===");
        index.removeDocument("1");

        List<Document> afterRemove = index.searchAny("java api");
        afterRemove.forEach(d -> System.out.println(d.getTitle()));

    }
}