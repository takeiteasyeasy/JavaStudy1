package com.java.sample.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rsi on 16/08/23.
 */
public class SampleStream01 {

    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>();
        Person p1 = new Person("one", false, 18);
        personList.add(p1);
        Person p2 = new Person("two", true, 25);
        personList.add(p2);
        Person p3 = new Person("three", true, 30);
        personList.add(p3);
        Person p4 = new Person("four", false, 8);
        personList.add(p4);

        System.out.println("************************");
        System.out.println("existsInfant=" + existsInfant(personList));
        System.out.println("==========================");
        System.out.println("existsInfantForStream=" + existsInfantForStream(personList));
        System.out.println("************************");

        System.out.println("************************");
        System.out.println("countBabyGirls=" + countBabyGirls(personList));
        System.out.println("==========================");
        System.out.println("countBabyGirlsForStream=" + countBabyGirlsForStream(personList));
        System.out.println("************************");

        System.out.println("************************");
        System.out.println("collectNamesStartsWith=");
        List<String> names1 = collectNamesStartsWith(personList, "t");
        for (String name : names1) System.out.println(name);
        System.out.println("==========================");
        System.out.println("collectNamesStartsWithForStream=");
        List<String> names2 = collectNamesStartsWithForStream(personList, "t");
        for (String name : names2) System.out.println(name);
        System.out.println("************************");

    }

    // 未成年が存在するかどうかを判定する
    public static boolean existsInfant(List<Person> persons) {
        for (Person person : persons) {
            if (person.getAge() < 20) {
                return true;
            }
        }
        return false;
    }

    public static boolean existsInfantForStream(List<Person> persons) {
        //コレクションのpersonsに対してstream()メソッドを呼び出すことで、Streamオブジェクトを生成している。
        return persons.stream()
                //取り出したStreamオブジェクトに対して、続けてanyMatchメソッドを呼び出すことで、Personデータ集合の中に該当する条件（＝ここでは未成年かどうか）の要素が存在するかどうかを判定している。
                .anyMatch(p -> p.getAge() < 20);
        /*
        [Detail]
        persons.stream().anyMatch((Person person) -> (person.getAge() < 20));
         */
    }

    // 20才以下の女児の人数を数える
    public static long countBabyGirls(List<Person> persons) {
        long count = 0;
        for (Person person : persons) {
            if (!person.isMaleFlag() && person.getAge() <= 20) {
                count++;
            }
        }
        return count;
    }

    public static long countBabyGirlsForStream(List<Person> persons) {
        return persons.stream()
                // Streamオブジェクトに対してfilterメソッドを呼び出して、条件に合致する要素だけを抽出している。このfilterメソッドも戻り値はStreamオブジェクトである
                .filter(p -> !p.isMaleFlag() && p.getAge() <= 20)
                // Streamオブジェクトに対してcountメソッドを呼び出して、要素の数を求めてリターンしている
                .count();
    }

    // 指定した文字列で始まる名前だけを取り出す
    public static List<String> collectNamesStartsWith(List<Person> persons, String prefix) {
        List<String> names = new ArrayList<>();
        for (Person person : persons) {
            String name = person.getName();
            if (name.startsWith(prefix)) {
                names.add(name);
            }
        }
        return names;
    }

    public static List<String> collectNamesStartsWithForStream(List<Person> persons, String prefix) {
        return persons.stream()
                //mapメソッドを呼び出している。ここで行っているのはPersonのStream内の各要素から名前を取り出して、文字列のStreamに変換する
                .map(p -> p.getName())
                //取り出した名前の中から、条件に合うものだけを抽出するために、filterメソッドを呼び出している
                .filter(s -> s.startsWith(prefix))
                //collectメソッドを呼び出して、StreamをListに変換
                .collect(Collectors.toList());
    }
}

class Person {

    public Person(String pname, boolean pmaleFlag, int page) {
        this.name = pname;
        this.maleFlag = pmaleFlag;
        this.age = page;
    }
    private String  name;
    private boolean maleFlag;
    private int     age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMaleFlag() {
        return maleFlag;
    }

    public void setMaleFlag(boolean maleFlag) {
        this.maleFlag = maleFlag;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

