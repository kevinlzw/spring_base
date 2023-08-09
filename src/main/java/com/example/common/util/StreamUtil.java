package com.example.common.util;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamUtil {

  public static <T, R> List<R> processList(List<T> list, Function<T, R> function) {
    return list.stream().map(function).collect(Collectors.toList());
  }

}
