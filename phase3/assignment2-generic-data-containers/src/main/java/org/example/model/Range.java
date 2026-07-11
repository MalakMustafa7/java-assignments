package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Range<T extends Comparable<T>>{
    private T start;
    private T end;

    public boolean contains(T value){
       return (value.compareTo(start)>=0) && (value.compareTo(end)<=0);
    }

    public boolean overlaps(Range<T> other){
        return this.start.compareTo(other.getEnd()) <= 0 &&
                other.getStart().compareTo(this.end) <= 0;
    }
    public Range<T>merge(Range<T> other){
       T newStart = (this.start.compareTo(other.getStart())<=0)? this.start : other.getStart();
       T newEnd = (this.end.compareTo(other.getEnd())>=0)? this.end : other.getEnd();
        return new Range<>(newStart, newEnd);

    }


}
