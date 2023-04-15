package com.tech.forkjoinpool.recursiveaction;

import java.util.Arrays;

/**
 * @author lw
 * @since 2023-04-15
 */
public class ArrayUtils {
    public static int[] merge(int[] left, int[] right) {
        int[] marr = new int[left.length + right.length];
        int lp = 0;
        int rp = 0;
        int mp = 0;
        while (lp < left.length && rp < right.length) {
            if (left[lp] < right[rp]){
                marr[mp]=left[lp];
                lp++;
            }else{
                marr[mp]=right[rp];
                rp++;
            }
            mp++;
        }
        while (lp<left.length){
            marr[mp++]=left[lp++];
        }
        while (rp<right.length){
            marr[mp++]=right[rp++];
        }
        return marr;
    }

    public static void main(String[] args) {

    }
}
