package org.example;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K,V> {
    private final int capacity;
    private int evictionCount = 0;

    public LRUCache(int capacity){
        this.capacity = capacity;
    }
    private final LinkedHashMap<K,V> map = new LinkedHashMap<>(16, 0.75f, true){
        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            boolean evict = size()>capacity;
            if(evict){
                evictionCount++;
            }
            return evict;
        }
    };
    public V get(K key){
        return map.get(key);
    }

    public void put(K key,V value){
        map.put(key,value);

    }
    public boolean containsKey(K key){
        return map.containsKey(key);
    }

    public int size(){
        return map.size();
    }

    public void clear(){
        map.clear();
    }

    public int getEvictionCount(){
        return evictionCount;
    }
    public void printCache(){
        System.out.println(map);
    }


}
