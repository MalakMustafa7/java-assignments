package org.example;

import static org.example.LRUCacheManual.printCache;

public class Main {
    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(5);
        LRUCacheManual<Integer, String> manual = new LRUCacheManual<>(5);

        for(int i = 1; i <= 10; i++){
            cache.put(i, "Item" + i);
            manual.put(i, "Item" + i);
        }

        System.out.println("After adding 10 items:");
        cache.printCache();
        System.out.println("After adding 10 items in manual:");
        LRUCacheManual.printCache(manual);



        cache.get(7);
        cache.get(9);
        System.out.println("After accessing 7 and 9:");
        cache.printCache();

        manual.get(7);
        manual.get(9);
        System.out.println("After accessing 7 and 9 in manual:");
        LRUCacheManual.printCache(manual);



        cache.put(11, "Item11");

        System.out.println("After adding 11 (eviction expected):");
        cache.printCache();

        manual.put(11, "Item11");

        System.out.println("After adding 11 (eviction expected) in manual:");
        LRUCacheManual.printCache(manual);

        System.out.println("Evictions: " + cache.getEvictionCount());
        System.out.println("Evictions in manual: " + cache.getEvictionCount());


        System.out.println("Contains 6? " + cache.containsKey(6));
        System.out.println("Contains 7? " + cache.containsKey(7));
        System.out.println("Contains 11? " + cache.containsKey(11));
        System.out.println("Contains 6? in manual " + cache.containsKey(6));
        System.out.println("Contains 7? " + cache.containsKey(7));
        System.out.println("Contains 11? " + cache.containsKey(11));
    }


}