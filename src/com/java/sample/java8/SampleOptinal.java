package com.java.sample.java8;

import java.util.Optional;

/**
 * Created by rsi on 16/11/22.
 */
public class SampleOptinal {

    public static void main(String[] args) {
        SampleOptinal exec = new SampleOptinal();
        exec.beforeMethod();
        exec.afterMethod();
        exec.returnValue();
        exec.filter();
        exec.getValue();
    }

    private String getHoge() {
        return "abcdefg";
        // NULL?
    }

    private void beforeMethod() {
        String hoge = getHoge(); // hogeはnullかも
        if (hoge != null) { // nullチェック
            System.out.println(hoge.length()); // hogeがnullじゃないのでlengthメソッドを呼ぶ
        }
    }

    private void afterMethod() {
        Optional<String> hogeOpt = Optional.ofNullable(getHoge()); // 値をラップする
        hogeOpt.ifPresent(hoge -> System.out.println(hoge.length())); // 値が存在する場合のみ実行
    }

    /*
    private String getHogeOpt() {
        return "abcdefg";
        // NULL?
    }
    */
    private Optional<String> getHogeOpt() {
        return Optional.of("abcdefg");
        // NULL?
    }

    private void returnValue() {
        /*
        処理して結果を返す
            ifPresentメソッドは値を返しません。
            Optionalの値を使った処理の結果、値を返したい場合はmapメソッドを使用します。
         */
        Optional<String> hogeOpt = Optional.ofNullable(getHoge());
        Optional<Integer> lengthOpt = hogeOpt.map(hoge -> hoge.length());

        /*
        mapメソッドの戻り値は、ラムダ式の戻り値をラップしたOptionalになります。
        hogeOptの値が存在しない場合はラムダは実行されず、値を持たないOptionalオブジェクトを返します。
         */

        /*
        また少々複雑ですが、2つのOptinalオブジェクトがともに値を持つ場合のみ処理結果を返すといった場合は、flatMapメソッドを使うべきです。
        flatMapがmapと異なるのは、戻り値を自分でOptionalオブジェクトにする必要がある点です。
         */

        // opt1とopt2に値が存在する場合のみ、処理結果を返す
        Optional<String> opt1 = getHogeOpt();
        Optional<String> opt2 = getHogeOpt();

        Optional<String> opt3 = opt1.flatMap(str1 ->
                opt2.flatMap(str2 -> {
                    return Optional.of(str1 + str2);
                }));
        System.out.println("opt3=" + opt3.toString());
        /*
        同じことをmapでやろうとすると、戻り値が Optional<Optional<String>> になってしまいます。
         */
    }

    private void filter() {
        /**
         * filter
         */
        /*
        上記のmapおよびflatMapメソッドは、同名のメソッドがStreamインターフェースでも定義されており同じような使い方をします。
        OptionalのメソッドでStreamのメソッドと同名のものには、他にfilterメソッドがあります。
         */
        Optional<String> hogeOpt = Optional.ofNullable(getHoge());
        Optional<String> filteredOpt = hogeOpt.filter(hoge -> hoge.length() >= 2);
        //Optional<String> filteredOpt = hogeOpt.filter(hoge -> hoge.length() >= 10);
        System.out.println("hogeOpt=" + filteredOpt.toString());
        /*
        filterの条件に合致する場合は自身（この場合はhogeOpt）を返し、条件に合わない場合は値を持たないOptionalオブジェクトを返します。
         */
    }

    private void getValue() {
        /**
         * Optionalから値を取り出す
         */
        /*
        Optionalから値を取り出すときは、主にorElseまたはorElseGetメソッドを使用することになると思います。
        orElseの引数には、Optionalが値を持っていないときのデフォルト値を指定します。
        orElseGetの引数も値がないときのデフォルト値を指定しますが、Supplier型のラムダ式で渡します。
         */
        Optional<String> hogeOpt = Optional.ofNullable(getHoge());
        // orElse
        String hoge = hogeOpt.orElse("デフォルト値");
        // orElseGet
        String hoge2 = hogeOpt.orElseGet(() -> "生成コストの高いデフォルト値");
        System.out.println("hoge=" + hoge);
        System.out.println("hoge2=" + hoge2);
        /*
        orElseGetメソッドのデフォルト値はラムダ式を使って指定しているため、実際に必要になるときまで生成されません。
         */
    }
}
