package com.foo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WildCards {

    public static void main(String[] args) throws IOException {
        System.out.println("Enter input: ");
        String input = new BufferedReader(new InputStreamReader(System.in)).readLine();
        if(input == null || !input.matches("[0|1|\\?]+")) return;
        replaceAndBranch(new ArrayList<>(capFor(input)), input).forEach(o -> System.out.println(o));
    }

    private static List<String> replaceAndBranch(List<String> agg, String input) {
        if(input.indexOf('?') >= 0) {
            replaceAndBranch(agg, input.replaceFirst("\\?", "1"));
            replaceAndBranch(agg, input.replaceFirst("\\?", "0"));
        } else agg.add(input);
        return agg;
    }

    public static int capFor(String str) {
        int count = 0;
        for (char c : str.toCharArray()) if (c == '?') count++;
        return (int) Math.pow(2, count);
    }

}
