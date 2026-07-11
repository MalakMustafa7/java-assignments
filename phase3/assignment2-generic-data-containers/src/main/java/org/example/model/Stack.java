package org.example.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class Stack<T> implements Iterable<T> {
 private final LinkedList<T> list = new LinkedList<>();

 public void push(T element){
   list.addFirst(element);
 }
 public T pop(){
   return list.removeFirst();
 }
 public T peek(){
   return list.getFirst();
 }
 public boolean isEmpty(){
   return list.isEmpty();
 }
 public int size(){
   return list.size();
 }
 public List<T> toList(){
   return new ArrayList<>(list);
 }


    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
