package com.java.sample.projectCoin;

import java.util.Arrays;
import java.util.List;

/**
 * Java SE 8
 * Project Coin
 * 安全な可変長引数メソッド アノテーション
 */
public class ExecSafeVrargs {

	//public static void main(String[] args) {
	public static void main(String... args) {
		
		String[] arr = {"a", "b", "c"};
		
		//testOne(arr);
		//testTwo(arr);
		testThree(arr);
	}
	
	public static void testOne(String[] strArr) {
		List<List<String>> list = Arrays.asList(Arrays.asList(strArr));
		System.out.println("One = " + list);
	}

	public static void testTwo(String... strArr) {
		List<List<String>> list = Arrays.asList(Arrays.asList(strArr));
		System.out.println("Two = " + list);
	}

	@SafeVarargs
	public static void testThree(String... strArr) {
		List<List<String>> list = Arrays.asList(Arrays.asList(strArr));
		System.out.println("Three = " + list);
	}

}
