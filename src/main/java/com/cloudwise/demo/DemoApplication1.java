package com.cloudwise.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.function.Function;

public class DemoApplication1 {

    public static void main(String[] args) {
        Function<String,Integer> function=x->x.length();
        System.out.println(function.apply("123123"));
    }

}
