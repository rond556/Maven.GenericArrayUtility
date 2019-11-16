package com.zipcodewilmington.arrayutility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by leon on 3/6/18.
 */
public class ArrayUtility<T> {
    T[] inputArray;

    public ArrayUtility(T[] inputArray){
        this.inputArray = inputArray;
    }

    public Integer countDuplicatesInMerge(T[] mergeArray,T targetValue) {
        Long resultAsLong = Stream.concat(Arrays.stream(inputArray), Arrays.stream(mergeArray)).filter(instance -> instance == targetValue).count();
        Integer result = resultAsLong.intValue();
        return result;
    }

    public T getMostCommonFromMerge(T[] mergeArray){
        ArrayList<T> toMerge = new ArrayList<T>(Arrays.asList(mergeArray));
        ArrayList<T> input = new ArrayList<T>(Arrays.asList(inputArray));
        toMerge.addAll(input);
        return toMerge.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey).orElse(null);
    }

    public Integer getNumberOfOccurrences(T targetValue){
        Long resultAsLong = Arrays.stream(inputArray).filter(instance -> instance.equals(targetValue)).count();
        Integer result = resultAsLong.intValue();
        return result;
    }

    public T[] removeValue(T targetValue){
        return Arrays.stream(inputArray).filter(instance -> !instance.equals(targetValue)).toArray(newSize -> Arrays.copyOf(inputArray, newSize));
    }
}
