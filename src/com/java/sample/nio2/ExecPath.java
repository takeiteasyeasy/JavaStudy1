package com.java.sample.nio2;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Java SE 8
 * NIO.2
 * パス
 */
public class ExecPath {

	public static void main(String[] args) {
		
		/*
		 * オブジェクト生成
		 */
		// sample1.txtを表すFILEオブジェクトの生成
		File file = new File("tmp/sample1.txt");
		System.out.println("file = " + file.getName());
		System.out.println("file exists = " + file.exists());
		
		// sample1.txtを表すPATHオブジェクトの生成
		FileSystem fileSystem = FileSystems.getDefault();
		Path path = fileSystem.getPath("tmp/sample1.txt");
		System.out.println("path = " + path.toString());
		
		// PATHSクラスを使用したsample1.txtを表すPATHオブジェクトの生成
		Path path2 = Paths.get("tmp/sample1.txt");
		System.out.println("path2 = " + path2.toString());
		
		/*
		 * ファイル操作
		 */
		// ファイルコピー
		Path src1 = Paths.get("tmp/sample1.txt");// 元
		Path dest1 = Paths.get("tmp/copy/sample1.txt");// 先
		try {
			//Files.copy(src1, dest1);// 実行
			Files.copy(src1, dest1, StandardCopyOption.REPLACE_EXISTING);// 実行。上書きOK
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// ファイルの移動／名前の変更
		Path src2 = Paths.get("tmp/copy/sample1.txt");// 元
		Path dest2 = Paths.get("tmp/copyMove/sample1.txt");// 先
		try {
			//Files.move(src2, dest2);// 実行
			Files.move(src2, dest2, StandardCopyOption.REPLACE_EXISTING);// 実行。上書きOK
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// ファイル削除
		Path src3= Paths.get("tmp/copyMove/sample1.txt");// 元
//		try {
//			Files.delete(src3);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		// ディレクトリ作成
		Path src4 = Paths.get("tmp/createTmp");
		try {
			Files.createDirectory(src4);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// さらに深いディレクトリ
		Path src5 = Paths.get("tmp/createTmp/createTmp2");
		try {
			Files.createDirectories(src5);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// ファイル作成
		Path src6 = Paths.get("tmp/createTmp/bar.txt");
		try {
			Files.createFile(src6);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// ハードリンク作成
		Path link1 = Paths.get("tmp/createTmp/createTmp2/hlink.txt");
		Path target1 = Paths.get("tmp/createTmp/bar.txt");
		try {
			Files.createLink(link1, target1);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// シンボリックリンク作成
		Path link2 = Paths.get("tmp/createTmp/createTmp2/slink.txt");
		Path target2 = Paths.get("tmp/createTmp/bar.txt");
		try {
			Files.createSymbolicLink(link2, target2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// シンボリックリンクのチェック（ハードリンクはチェック出来無い）
		if (Files.isSymbolicLink(link2)) {
			System.out.println(link2.toString() + "は、シンボリックリンクです。");
		} else {
			System.out.println(link2.toString() + "は、シンボリックリンクではない！");
		}
		
		// シンボリックリンクをたどる
		try {
			Path target3 = Files.readSymbolicLink(link2);
			System.out.println("link2 = " + link2.toString());
			System.out.println("target3 = " + target3.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
