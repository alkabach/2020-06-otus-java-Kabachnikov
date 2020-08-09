package ru.otus;

import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

public class HelloOtus {
    public static void main(String[] args) {
        String t1 = "TEst";
        String t2 = "TEst2";

        Ordering<String> byLengthOrdering = new Ordering<>() {
            public int compare(String left, String right) {
                return Ints.compare(left.length(), right.length());
            }
        };

        if(byLengthOrdering.compare(t1,t2) == 1){
            System.out.println("t1 is longer!");
        } else System.out.println("t2 is longer!");
    }

}
