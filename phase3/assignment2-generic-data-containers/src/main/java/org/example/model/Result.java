package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Function;


public class Result<T> {
    private final T value;
    private final String error;
    private final boolean success;

    private Result(T value, String error, boolean success){
        this.value = value;
        this.error = error;
        this.success = success;
    }
    public static <T> Result<T> success(T value){
        return new Result<>(value,null,true);
    }

    public static <T> Result<T> failure(String error) {
        return new Result<>(null, error, false);
    }
    public boolean isSuccess() { return success; }

    public T getValue() {
        if (!success) throw new IllegalStateException("Result is a failure: " + error);
        return value;
    }

    public String getError() {
        if (success) throw new IllegalStateException("Result is a success — no error");
        return error;
    }
    public <R> Result<R> map(Function<T, R> mapper){
        if(!success){
            return Result.failure(error);
        }
        return Result.success(mapper.apply(value));
    }

}
