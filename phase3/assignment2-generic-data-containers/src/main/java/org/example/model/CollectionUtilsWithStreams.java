package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class CollectionUtilsWithStreams {
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate){
        return list.stream()
                   .filter(predicate)
                   .toList();
    }

    public static <T,R> List<R> transform(List<T> list, Function<T,R> function){
        return list.stream()
                   .map(function)
                   .toList();
    }
    public static <T>Pair<List<T>, List<T>> partition(List<T> list, Predicate<T> predicate){
        List<T> partition1 = list.stream()
                .filter(predicate)
                .toList();
        List<T> partition2 = list.stream()
                .filter(predicate.negate())
                .toList();

        return Pair.of(partition1,partition2);
    }

    public static<A,B> List<Pair<A,B>> zip(List<A> aList, List<B>bList){
        int size = Math.min(aList.size(), bList.size());
        return IntStream.range(0,size)
                .mapToObj(i->Pair.of(aList.get(i),bList.get(i)))
                .toList();
    }
}
