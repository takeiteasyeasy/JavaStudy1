package com.java.sample.projectCoin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Java SE 8
 * Project Coin
 * リソースの自動クローズ
 */
public class ExecResourceClose {

	public static void main(String[] args) {
		
		

	}
	
	/*
	 * 新
	 */
	public List<String> readFileNew(String filename) {
		List<String> contents = new ArrayList<>();
		
		// TRYに2つのリソースも記載出来る
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			// 1行読み込んでcontentsに追加する
			for (;;) {
				String line = reader.readLine();
				if (line == null) {
					break;
				} else {
					contents.add(line);
				}
			}
		} catch (IOException ex) {
			// 例外
			ex.printStackTrace();
			
		}
		return contents;
	}
	
	/*
	 * 旧対応
	 */
	public List<String> readFile(String filename) {
		BufferedReader reader = null;
		List<String> contents = new ArrayList<>();
		try {
			reader = new BufferedReader(new FileReader(filename));
			// 1行読み込んでcontentsに追加する
			for (;;) {
				String line = reader.readLine();
				if (line == null) {
					break;
				} else {
					contents.add(line);
				}
			}
		} catch (IOException ex) {
			// 例外
			ex.printStackTrace();
			
		} finally {
			// クローズ
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return contents;
	}

}
