package com.eugene;

/**
 * Created by eugene on 16/1/27.
 */
public class Test {
    public static void main(String[] args){

        showNumberInRange(1,14);
        System.out.println();


    }

    private static void showNumberInRange(int i, int j){
        System.out.print(i + " ");
        if (i<j) {
            showNumberInRange(i+1, j);
        }
    }
}
