package com.example.common.util;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamUtil {

  public static <T, R> List<R> processList(Collection<T> collection, Function<T, R> function) {
    return collection.stream().map(function).collect(Collectors.toList());
  }

}
