package com.java.sample.basic;

/**
 * アサーション
 * 
 */
public class ExecAssert {
	
	/*
	 * 実行時にVMに-eaのパラメータを入れないとダメ！
	 */

	public static void main(String[] args) {
		
		int i = 10;
		System.out.println("test1. i = " + i);
		// assert
		assert i == 10;
		
		System.out.println("test2. i = " + i);
		// assert
		assert i == 999;
		
		System.out.println("test3. i = " + i);

	}

}
