package net.dubbo.common.utils;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author lishulong
 * @version 1.0
 * @email lishulong.never@gmail.com
 * @date 2018/1/2
 */
public class MapUtil {


    public static void main(String[] args) {
//
//        Map<String, String> map = new HashMap<String, String>();
//
//        for (int i = 0; i < 10; i++)
//            map.put(String.valueOf(i), String.valueOf(i));
//
//        map.forEach((k, v) -> System.out.println(k + v));



        // 1. Individual values
//        Stream stream = Stream.of("a", "b", "c");
//
//        System.out.println("========");
//        System.out.println( stream.count());
//        System.out.println("========");
//// 2. Arrays
//        String [] strArray = new String[] {"a", "b", "c"};
//        stream = Stream.of(strArray);
//        stream = Arrays.stream(strArray);
//// 3. Collections
//        List<String> list = Arrays.asList(strArray);
//        stream = list.stream();


        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
        IntStream.range(1, 3).forEach(System.out::println);
        IntStream.rangeClosed(1, 3).forEach(System.out::println);


        // 1. Array
//        String[] strArray1 = stream.toArray(String[]::new);
//// 2. Collection
//        List<String> list1 = stream.collect(Collectors.toList());
//        List<String> list2 = stream.collect(Collectors.toCollection(ArrayList::new));
//        Set set1 = stream.collect(Collectors.toSet());
//        Stack stack1 = stream.collect(Collectors.toCollection(Stack::new));
//// 3. String
//        String str = stream.collect(Collectors.joining()).toString();


    }
}
