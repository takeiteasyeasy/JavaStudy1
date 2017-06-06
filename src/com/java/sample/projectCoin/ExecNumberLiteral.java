package com.java.sample.projectCoin;

/**
 * Java SE 8
 * Project Coin
 * Number Literal
 */
public class ExecNumberLiteral {

	public static void main(String[] args) {
		
		/*
		 * 2進数リテラル
		 */
		int x1 = 1234567;
		System.out.println("X1 = " + x1);
		// xの下3ビットだけを取り出す
		x1 = x1 & 7;
		System.out.println("X1 = " + x1);
		
		int x2 = 1234567;
		System.out.println("X2 = " + x1);
		// xの下3ビットだけを取り出す
		x2 = x2 & 0b111;
		System.out.println("X2 = " + x2);
		
		/*
		 * 数値リテラル
		 */
		int n1 = 100000000;
		int n2 = 100_000_000;
		int n3 = 1_0000_0000;
		System.out.println("N1 = " + n1);
		System.out.println("N2 = " + n2);
		System.out.println("N3 = " + n3);
	}

}
