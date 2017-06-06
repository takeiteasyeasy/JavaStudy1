package com.java.sample.projectCoin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Java SE 8
 * Project Coin
 * Multi Catch
 */
public class ExecMultiCatch {

	public static void main(String[] args) {
		
		String className = "";
		
		try {
			Class cls = Class.forName(className);
			cls.newInstance();
		} catch (ClassNotFoundException ex) {
			System.out.println("インスタンスの生成に失敗");
		} catch (InstantiationException ex) {
			System.out.println("インスタンスの生成に失敗");
		} catch (IllegalAccessException ex) {
			System.out.println("インスタンスの生成に失敗");
		}
		
		try {
			Class cls = Class.forName(className);
			cls.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
			System.out.println("インスタンスの生成に失敗");
		}
		
		/*
		 * マルチキャッチが行えない例
		 */
		String fileName = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line = reader.readLine();
			
		//} catch (FileNotFoundException | IOException ex) {
			// FileNotFoundExceptionとIOExceptionnoの例外は継承関係にあるためコンパイルエラーになる
			//System.out.println("Exception!");
		} catch (FileNotFoundException ex) {
			System.out.println("Exception!");
		} catch (IOException ex) {
			System.out.println("Exception!");
		}
	}

}
