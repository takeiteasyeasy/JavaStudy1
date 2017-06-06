package com.java.sample.java8;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rsi on 16/11/16.
 */
public class SampleNio2 {

    public static void main(String[] args) {
        SampleNio2 exec = new SampleNio2();
        exec.createFile();
        exec.writeFile();
        exec.createPathObj();
        exec.fileAttribute();
        exec.createDir();
        exec.copyFile();
        exec.readFile();
        exec.deleteFile();
    }

    private void createFile() {
        // tmp/sample1.txtを表すFILEオブジェクトの生成
        File file = new File("tmp/sample1.txt");
        System.out.println("file = " + file.getName());
        // ファイル作成
        try {
            Files.createFile(Paths.get(file.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createPathObj() {
        // tmp/sample1.txtを表すPATHオブジェクトの生成
        FileSystem fileSystem = FileSystems.getDefault();
        Path path = fileSystem.getPath("tmp/sample1.txt");
        System.out.println("path = " + path.toString());

        // PATHSクラスを使用したtmp/sample1.txtを表すPATHオブジェクトの生成
        Path path2 = Paths.get("tmp/sample1.txt");
        System.out.println("path2 = " + path2.toString());
    }

    private void createDir() {
        Path src = Paths.get("tmp/createTmp1/createTmp2");
        try {
            Files.createDirectories(src);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyFile() {
        Path src1 = Paths.get("tmp/sample1.txt");// 元
        Path dest1 = Paths.get("tmp/createTmp1/createTmp2/sample2.txt");// 先
        try {
            //Files.copy(src1, dest1);// 実行
            Files.copy(src1, dest1, StandardCopyOption.REPLACE_EXISTING);// 実行。上書きOK
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteFile() {
        Path src1 = Paths.get("tmp/sample1.txt");
        try {
            Files.delete(src1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFile() {
        /**
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

    private void readFile() {
        /**
         * 読み込み
         */
        String filePath = "tmp/createTmp1/createTmp2/sample2.txt";
        // シンプルな行単位の読み込み
        Path readPath1 = Paths.get(filePath);
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
        Path readPath2 = Paths.get(filePath);
        try {
            List<String> contents2 = Files.readAllLines(readPath2);
            for (String str2 : contents2) {
                System.out.println("read2 = " + str2);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void fileAttribute() {
        Path src = Paths.get("tmp/sample1.txt");
        //これはwindowsでしか動かない…と思いきやUbuntuでも動いた。
        //windowsではsun.nio.fs.WindowsFileAttributeViews、Ubuntuではsun.nio.fs.LinuxDosFileAttributeViewが返ってきた。
        //各環境に対応する実装クラスが動作時に存在すれば動く、ってことなんでしょうね。
        DosFileAttributeView dosFileAttributeView = Files.getFileAttributeView(src, DosFileAttributeView.class);
        DosFileAttributes dosFile = null;
        try {
            dosFile = dosFileAttributeView.readAttributes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("attributeView1 = " + dosFile.isReadOnly() + ":" + dosFile.isHidden());

        //こちらはUbuntuでしか動かない。windowsではposixFileAttributeViewがnullになる。
        PosixFileAttributeView posixFileAttributeView = Files.getFileAttributeView(src, PosixFileAttributeView.class);
        PosixFileAttributes posixFile = null;
        try {
            posixFile = posixFileAttributeView.readAttributes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("attributeView2 = " + posixFile.permissions());

    }

}
