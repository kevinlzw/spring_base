package com.example.common.util;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamUtil {

  public static <T, R> List<R> processList(Collection<T> collection, Function<T, R> function) {
    return collection.stream().map(function).collect(Collectors.toList());
  }

  public static <T> List<T> filterList(Collection<T> collection, Predicate<T> function) {
    return collection.stream().filter(function).collect(Collectors.toList());
  }

}
