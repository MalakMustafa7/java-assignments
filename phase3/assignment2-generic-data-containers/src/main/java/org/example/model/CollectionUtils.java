package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class CollectionUtils {
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate){
        List<T> result = new ArrayList<>();
        for(T element:list){
            if(predicate.test(element)){
                result.add(element);
            }
        }
        return result;
    }

    public static <T,R> List<R> transform(List<T> list, Function<T,R> function){
        List<R> result = new ArrayList<>();
        for(T element:list){
            R mappedElement = function.apply(element);
            result.add(mappedElement);
        }
        return result;
    }
    public static <T>Pair<List<T>, List<T>> partition(List<T> list, Predicate<T> predicate){
        List<T> partition1 = new ArrayList<>();
        List<T> partition2 = new ArrayList<>();
        for(T element:list){
            if(predicate.test(element)){
                partition1.add(element);
            }else{
                partition2.add(element);
            }
        }
        return Pair.of(partition1,partition2);
    }

    public static<A,B> List<Pair<A,B>> zip(List<A> aList, List<B>bList){
        int size = Math.min(aList.size(), bList.size());
        List<Pair<A,B>> result = new ArrayList<>();
        for(int i=0;i<size;i++){
            result.add(Pair.of(aList.get(i), bList.get(i)));
        }
        return result;
    }

}
