package com.devmaster.lesson01.lambda_expression;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.List;

public class SortLambdaExample {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java SpringBoot","C# Netcore","PHP","Javascript");
        Collections.sort(list,(String str1, String str2) -> str1.compareTo(str2));
        for (String s: list){
            System.out.println(s);
        }
    }
}
