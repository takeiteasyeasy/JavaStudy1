package com.java.sample.nio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Java SE 8
 * NIO.2
 * ファイル入出力
 */
public class ExecFileInOut {

	public static void main(String[] args) {
		
		/*
		 * 読み込み
		 */
		
		// シンプルな行単位の読み込み
		Path readPath1 = Paths.get("tmp/sample1.txt");
		try (BufferedReader reader1 = Files.newBufferedReader(readPath1)) {
			List<String> contents1 = new ArrayList<>();
			for (;;) {
				String line1 = reader1.readLine();
				if (line1 != null) {
					contents1.add(line1);
				} else {
					break;
				}
			}
			for (String str1 : contents1) {
				System.out.println("read1 = " + str1);
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		// 簡易入出力による読み込み
		Path readPath2 = Paths.get("tmp/sample1.txt");
		try {
			List<String> contents2 = Files.readAllLines(readPath2);
			for (String str2 : contents2) {
				System.out.println("read2 = " + str2);
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		
		/*
		 * 書き込み
		 */
		
		// 簡易入出力による書き込み
		Path writePath1 = Paths.get("tmp/sample1.txt");
		try {
			List<String> contents2 = new ArrayList<>();
			contents2.add("writeOne");
			contents2.add("writeTwo");
			
			//Files.write(writePath1, contents2);
			Files.write(writePath1, contents2, StandardOpenOption.APPEND);//追記
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		
		
	}

}
