package org.example;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheManual<K,V>{
    private final   int capacity;
    private Map<K,Node<K,V>> map;
    private Node<K,V> head,tail;

    private int evictionCount = 0;

    public LRUCacheManual(int capacity){
       this.capacity = capacity;
        map=new HashMap<>();
        head = new Node<>(null,null);
        tail = new Node<>(null,null);

        head.next = tail;
        tail.prev = head;
    }

    public void put(K key,V value){
        Node<K,V> node = map.get(key);
        if(node != null){
            node.value = value;
            moveToHead(node);
        }else{
            node = new Node<>(key,value);
            map.put(key,node);
            addToHead(node);
            if(map.size()>capacity){
               Node<K,V> tailNode= removeTail();
               map.remove(tailNode.key);
                evictionCount++;
            }

        }
    }
    public V get(K key){
        Node<K,V> node = map.get(key);
        V value = null;
        if(node != null){
           value = node.value;
           moveToHead(node);
        }
        return value;
    }

    public boolean containsKey(K key){
        return map.containsKey(key);
    }

    public int size(){
        return map.size();
    }

    public void clear(){
        map.clear();
        head.next = tail;
        tail.prev = head;
    }

    public int getEvictionCount(){
        return evictionCount;
    }

    private void moveToHead(Node<K,V> node){
        removeNode(node);
        addToHead(node);
    }

    private void addToHead(Node<K,V> node){
       node.prev = head;
       node.next = head.next;
       head.next.prev = node;
       head.next = node;
    }
    private void removeNode(Node<K,V> node){
        node.prev.next = node.next;
        node.next.prev = node.prev;

    }
    private Node<K,V> removeTail(){
        Node<K,V> node = tail.prev;
        removeNode(node);
        return node;
    }
   private static class Node<K,V>{
        K key;
        V value;
        Node<K,V> prev;
        Node<K,V> next;
        Node(K k,V v){
            this.key = k;
            this.value = v;
        }
    }
    public static void printCache(LRUCacheManual<Integer, String> cache) {
        System.out.print("Cache: ");
        LRUCacheManual.Node<Integer, String> current = cache.head.next;
        while (current != cache.tail) {
            System.out.print(current.key + "(" + current.value + ") ");
            current = current.next;
        }
        System.out.println();
    }

}
