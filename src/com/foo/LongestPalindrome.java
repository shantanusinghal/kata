package com.foo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ssinghal
 * Created on 06-Feb-2016
 */
public class LongestPalindrome {

    public static void main(String[] args) throws IOException {
        System.out.println("Enter input: ");
        String input = new BufferedReader(new InputStreamReader(System.in)).readLine();
        if(input == null) return;
        System.out.println("Largest palindrome is: " + getLargestPalindrome(input));
    }

    public static String getLargestPalindrome(String input) {
        char[] leftChars, rightChars;
        String largestPalindrome = "";
        ArrayList<char[]> stickyList = getStickyList(input);

        for (int i = 0, j = 1; i < stickyList.size(); i++, j = 1) {
            // iterate outwards and compare boundary groups
            while(i-j >= 0 && i+j < stickyList.size() && (leftChars = stickyList.get(i-j))[0] == (rightChars = stickyList.get(i+j))[0]) {
                j++;
                if (leftChars.length != rightChars.length) break;
            }
            String potentialPalindrome = stitchStickyListIntoPalindrome(i, j - 1, stickyList);
            if(potentialPalindrome.length() > largestPalindrome.length()) largestPalindrome = potentialPalindrome;
        }

        return largestPalindrome;
    }

    // builds palindrome from sticky list
    private static String stitchStickyListIntoPalindrome(int middle, int range, ArrayList<char[]> list) {
        int endIndex = middle + range;
        int startIndex = middle - range;
        StringBuilder builder = new StringBuilder();
        int commonMinLength = Math.min(list.get(startIndex).length, list.get(endIndex).length);

        for(int i = startIndex; i <= endIndex; i++) {
            char[] original = list.get(i);
            char[] actual = i == startIndex || i == endIndex ? Arrays.copyOf(original, commonMinLength) : original;
            builder.append(actual);
        }

        return builder.toString();
    }

    // stick consecutive similar characters together
    private static ArrayList<char[]> getStickyList(String input) {
        ArrayList<char[]> stickyList = new ArrayList<>();
        char[] chars = input.toCharArray();
        for (int i = 0, len = 1; i < chars.length; len = 1) {
            char aChar = chars[i];
            while(++i < chars.length && chars[i] == aChar) len++;
            char[] aCharArray = new char[len];
            Arrays.fill(aCharArray, aChar);
            stickyList.add(aCharArray);
        }
        return stickyList;
    }


}
