package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class SearchIndex {
    Map<String , Set<String>> wordIndex = new HashMap<>();
    Map<String, Document> docStore = new HashMap<>();

    public void addDocument(Document document){
        docStore.put(document.getDocId(),document);
        for (String word : tokenize(document.getContent())) {
            wordIndex
                    .computeIfAbsent(word, k -> new HashSet<>())
                    .add(document.getDocId());
        }
    }

    public List<Document> search(String query){
        String[] words = query.toLowerCase().split("\\s+");
        if (Arrays.stream(words).anyMatch(w -> !wordIndex.containsKey(w))) {
            return new ArrayList<>();
        }

        Set<String> result = Arrays.stream(words)
                .map(wordIndex::get)
                .map(HashSet::new)
                .reduce((s1,s2)->{
                    s1.retainAll(s2);
                    return s1;
                }).orElse(new HashSet<>());

        return result.stream()
                .filter(docStore::containsKey)
                .map(docStore::get)
                .toList();

    }
    public List<Document> searchAny(String query){
        String[] words = query.toLowerCase().split("\\s+");

        Set<String> result = Arrays.stream(words)
                .filter(wordIndex::containsKey)
                .map(wordIndex::get)
                .map(HashSet::new)
                .reduce((s1,s2)->{
                    s1.addAll(s2);
                    return s1;
                }).orElse(new HashSet<>());

        return result.stream()
                .filter(docStore::containsKey)
                .map(docStore::get)
                .toList();

    }

    public List<Document> searchByTag(String tag){
        String t = tag.toLowerCase();

        return docStore.values().stream()
                .filter(d -> d.getTags().stream()
                        .map(String::toLowerCase)
                        .anyMatch(t::equals))
                .toList();
    }

    public List<String> getMostIndexedWords(int n){
       return wordIndex.entrySet().stream()
               .sorted((a, b) -> Integer.compare(b.getValue().size(), a.getValue().size()))
               .limit(n)
               .map(Map.Entry::getKey)
               .toList();
    }

    public void removeDocument(String docId){
        docStore.remove(docId);
        Iterator<Map.Entry<String, Set<String>>> it = wordIndex.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<String, Set<String>> entry = it.next();
            Set<String> docs = entry.getValue();

            docs.remove(docId);

            if (docs.isEmpty()) {
                it.remove();
            }
        }
    }

    private List<String> tokenize(String text) {
        if (text == null || text.isBlank()) return Collections.emptyList();
        return Arrays.stream(
                        text.toLowerCase()
                                .replaceAll("[^a-z0-9\\s]", "")
                                .split("\\s+"))
                .filter(w -> !w.isBlank())
                .collect(Collectors.toList());
    }

}
