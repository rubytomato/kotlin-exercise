package com.example.exercise;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionExercise {

    public static void main(String[] args) {
        new FunctionExercise().tryAll();
    }

    public void tryAll() {
        demoFunc();
    }

    private Function<Integer, String> func1 = i -> {
        Integer temp = i * i;
        return temp.toString();
    };

    private Consumer<Integer> cons1 = i -> System.out.println(i * i);

    private Predicate<Integer> pred1 = i -> i >= 0;

    private void demoFunc() {
        System.out.println(func1.apply(11));
        cons1.accept(22);
        System.out.println(pred1.test(0));
        System.out.println(pred1.test(-1));
    }

}
