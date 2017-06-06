package com.java.sample.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by rsi on 16/08/23.
 */
public class SampleStream02 {

    public static void main(String[] args) {
        System.out.println("************************");
        streamFilter();
        System.out.println("************************");
        steamMap();
        System.out.println("************************");
        streamFilterMap();
        System.out.println("************************");
        streamFlatMapOne();
        System.out.println("************************");
        streamFlatMapTwo();
        System.out.println("************************");
        steamFlatMapThree();
        System.out.println("************************");
        steamFlatMapThreeOld();
        System.out.println("************************");
    }

    private static void streamFilter() {
        /*
        filter は Predicate<T> （Streamの要素Tを引数に取り、booleanを返す関数）を引数に取るメソッドである。StreamからPredicateがtrueを返す要素のみを抽出する。
        （従来のコレクション走査なら、for文中のif文に相当する。）
        */

        // 偶数の抽出
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        list.stream() // Streamの生成
                .filter(n -> n % 2 == 0)  // n % 2 == 0となる要素のみ抽出
                .forEach(System.out::println); // 各要素を出力。 結果は2,4
    }

    private static void steamMap() {
        /*
        map は Function<T,R>（Streamの要素Tを引数に取り、R型の戻り値を返す関数。TとRは同じ型でも良い）を引数に取り、Streamの要素を1対1で変換するメソッドである。要素の計算や型変換などに利用できる。
        （従来のコレクション走査なら、for文の内部に書くロジックに該当する。）
         */

        // 要素の2乗
        List<Integer> list=Arrays.asList(1,2,3,4,5);
        list.stream() // Streamの生成
                .map(n -> n * n) // 要素の2乗(IntegerからIntegerへの変換)
                .map(n -> "answer = " + n) // (IntegerからStringへの変換)
                .forEach(System.out::println); // answer = xx の形式で出力
    }

    private static  void streamFilterMap() {
        // 奇数要素の2倍
        List<Integer> list=Arrays.asList(1,2,3,4,5);
        list.stream()
                .filter(n -> n % 2 == 1) // 奇数の要素のみを抽出し、
                .map(n -> n * 2) // 2倍にすると
                .forEach(System.out::println); // (2,6,10)が出力される

        // 2倍にしてから奇数要素の抽出（filterとmapを入れ替える）
        list.stream()
                .map(n -> n * 2) // 最初に2倍にすると
                .filter(n -> n % 2 == 1) // 奇数の要素は抽出されず
                .forEach(System.out::println); // 出力なし
    }

    private static void streamFlatMapOne() {
        /*
        flatMap はmapと似ているが、引数にFunction<T, Stream<R>> という1つの要素TからStream<R>型を生成する関数を取る。
        また、flatMap自体の戻り値もStream<R>で、元のStream<T>の各要素から生成したStream<R>を1つのStream<R>としてまとめる。
        （データの1対多への増幅・あるいは多重ループみたいなもの）。
         */

        //Function<Integer, List<Integer>> repeat = n -> Stream.generate(() -> n).limit(n);
        Function<Integer, Stream<Integer>> repeat = n -> Stream.generate(() -> n).limit(n);
        repeat.apply(1).forEach(System.out::print); // 1
        System.out.println("");
        repeat.apply(2).forEach(System.out::print); // 22
        System.out.println("");
        repeat.apply(3).forEach(System.out::print); // 333
        System.out.println("");
        repeat.apply(9).forEach(System.out::print);
        System.out.println("");
    }

    private static void streamFlatMapTwo() {
        //Function<Integer, List<Integer>> repeat = n -> Stream.generate(() -> n).limit(n);
        Function<Integer, Stream<Integer>> repeat = n -> Stream.generate(() -> n).limit(n);
        /*
        これを整数リストのStreamに適用してみる。
         */
        List<Integer> list = Arrays.asList(0,1,2,4);
        list.stream()
                .flatMap(repeat) // 1224444 へ増幅。(0は増幅されない)
                .flatMap(repeat) // 増幅された各要素にさらにrepeatを適用して増幅
                .forEach(System.out::print); //122224444444444444444
        System.out.println("");
    }

    private static void steamFlatMapThree() {
        /*
        このように、flatMapを実行するたびにデータが増幅・展開される。
        このflatMapを使うことで、従来の多重ループに該当する処理が記述できる。
         */
        IntStream.range(0, 4).boxed() // boxedはIntStreamからStreamへの変換。
                .flatMap(i -> IntStream.range(0, 4).boxed() // 最初に生成された各数値ごとにさらに増幅。
                        //.flatMap(i -> IntStream.range(0, 3).boxed() // 最初に生成された各数値ごとにさらに増幅。
                        .map(j -> i + ":" + j)) // 生成された数字を2つとも使うためには、このようにmapをネストさせる。
                .forEach(System.out::println); // 0:0,0:1,0:2,0:3,1:0,,,と16行生成
    }

    private static void steamFlatMapThreeOld() {
        /*
        これは以下のコードと同義である。
         */
        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.println(i + ":" + j);
            }
        }
    }
}
