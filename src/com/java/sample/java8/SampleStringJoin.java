package com.java.sample.java8;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Created by rsi on 16/11/22.
 */
public class SampleStringJoin {

    public static void main(String[] args) {
        SampleStringJoin exec = new SampleStringJoin();
        exec.beforeTpye();
        exec.joinType();
        exec.joinerType();
        exec.joinSb();
        exec.joinStreamApi();
        exec.joinStreamApiCollect();
        exec.join();
    }

    private void beforeTpye() {
        String[] strArray = { "abc", "def", "123", "456", "xyz" };
        String separator = ",";
        StringBuilder sb = new StringBuilder();
        for (String str : strArray) {
            if (sb.length() > 0) {
                sb.append(separator);
            }
            sb.append(str);
        }

        System.out.println(sb.toString());
        // → abc,def,123,456,xyz という文字列が表示される
    }

    private void joinType() {
        String[] strArray = { "abc", "def", "123", "456", "xyz" };
        String separator = ",";
        System.out.println(String.join(separator, strArray));
        // → abc,def,123,456,xyz という文字列が表示される
        /**
         * String#joinを使うパターン(joinJoin)とStringJoinerを使うパターン(joinerJoin)は、StringBuilderを使うパターン(builderJoin)とほぼ同等の速度が出せる
         */
    }

    private void joinerType() {
        String[] strArray = { "abc", "def", "123", "456", "xyz" };
        String separator = ",";
        StringJoiner sj = new StringJoiner(separator);
        for (String str : strArray) {
            sj.add(str);
        }
        System.out.println(sj.toString());
        // → abc,def,123,456,xyz という文字列が表示される
        /**
         * String#joinを使うパターン(joinJoin)とStringJoinerを使うパターン(joinerJoin)は、StringBuilderを使うパターン(builderJoin)とほぼ同等の速度が出せる
         */
    }

    static final String[] alpha = new String[]{
            "0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9",
            "a", "b", "c", "d", "e",
            "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o",
            "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y",
            "z"};
    //static long loop = 5000000;
    static long loop = 500;

    private void joinSb() {
        long span;
        /* 1: よくある文字列連結 ****/
        span = System.currentTimeMillis();
        for(long i=0; i<loop; i++){
            StringBuilder buf = new StringBuilder();
            for(String str : alpha){
                buf.append(str);
            }
            buf.toString();
        }
        span = System.currentTimeMillis()-span;
        System.out.println(span); //4552
    }

    private void joinStreamApi() {
        long span;
        /* 2: 1のStream API版? ****/
        span = System.currentTimeMillis();
        for(long i=0; i<loop; i++){
            String str = Arrays.stream(alpha)
                    .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                    .toString();
        }
        span = System.currentTimeMillis()-span;
        System.out.println(span); //4620
    }

    private void joinStreamApiCollect() {
        long span;
        /* 3: collect使ってみる ****/
        span = System.currentTimeMillis();
        for(long i=0; i<loop; i++){
            String str = Arrays.stream(alpha).collect(Collectors.joining());
        }
        span = System.currentTimeMillis()-span;
        System.out.println(span); //5267
    }

    private void join() {
        long span;
        /* 4: そもそも文字列joinだけならjava8からメソッド追加されてる ****/
        span = System.currentTimeMillis();
        for(long i=0; i<loop; i++){
            String str = String.join("", alpha);
        }
        span = System.currentTimeMillis()-span;
        System.out.println(span); //6490
    }


}
