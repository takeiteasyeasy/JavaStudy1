package com.java.sample.basic;

/*
 * ラッパクラスの＝＝比較
 * 
 * javaのオブジェクト型(Integer)を==比較すると危険！
 * http://javatechnology.net/java/integer-equal/
 */
public class ExecComparisonInteger {

	public static void main(String[] args) {
		test1();
		test2();
		test3();
	}
	
	/*
	 * 典型的なダメパターン
	 */
	private static void test1() {
		System.out.println("*** test1 ***");
		Integer int1 = new Integer("1");
		Integer int2 = new Integer("1");
		if ( int1 == int2) {
		    System.out.println("等しい");
		} else {
		    System.out.println("等しくない");
		}
	}
	
	/*
	 * ==比較がOKなパターン
	 */
	private static void test2() {
		System.out.println("*** test2 ***");
		Integer int3 = 1;
		Integer int4 = 1;
		if ( int3 == int4) {
		    System.out.println("等しい");
		} else {
		    System.out.println("等しくない");
		}
	}

	/*
	 * ==比較がNGなパターン
	 */
	private static void test3() {
		System.out.println("*** test3 ***");
		Integer int5 = 128;
		Integer int6 = 128;
		if ( int5 == int6) {
		    System.out.println("等しい");
		} else {
		    System.out.println("等しくない");
		}
		
		System.out.println("*** test3.1 *** 127, 127");
		Integer int7 = 127;
		Integer int8 = 127;
		if ( int7 == int8 ) {
		    System.out.println("等しい");
		} else {
		    System.out.println("等しくない");
		}
		
		System.out.println("*** test3.2 *** 129, 129");
		Integer int9 = 129;
		Integer int10 = 129;
		if ( int9 == int10 ) {
		    System.out.println("等しい");
		} else {
		    System.out.println("等しくない");
		}
		
		System.out.println("*** test3.3 *** 127, 129");
		Integer int11 = 127;
		Integer int12 = 129;
		if ( int11 == int12 ) {
		    System.out.println("等しい");
		} else {
		    System.out.println("等しくない");
		}

	}
	
}
