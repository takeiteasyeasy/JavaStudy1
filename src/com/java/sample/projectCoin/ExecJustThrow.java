package com.java.sample.projectCoin;

/**
 * Java SE 8
 * Project Coin
 * 
 */
public class ExecJustThrow {

	public static void main(String[] args) {
		
		ExecJustThrow exec = new ExecJustThrow();
		try {
			//exec.createInstance("dummy1");
			exec.createInstanceAdjust("dummy2");
			
		} catch (InstantiationException | IllegalAccessException e) {
			System.out.println("キャッチメイン");
		}

	}
	
	private <T> T createInstance(String className) 
			throws InstantiationException, IllegalAccessException{
		try {
			Class cls = Class.forName(className);
			return (T)cls.newInstance();
		} catch (ClassNotFoundException e) {
			System.out.println("クラスが見つかりません");
			e.printStackTrace();
			
		} catch (InstantiationException | IllegalAccessException ex) {
			System.out.println("インスタンス生成失敗");
			ex.printStackTrace();
			throw ex;
		}
		return null;
	}
	
	private <T> T createInstanceAdjust(String className) 
			throws InstantiationException, IllegalAccessException{
		try {
			Class cls = Class.forName(className);
			return (T)cls.newInstance();
		} catch (ClassNotFoundException e) {
			System.out.println("クラスが見つかりません");
			e.printStackTrace();
			
		} catch (Exception ex) {
			System.out.println("インスタンス生成失敗");
			ex.printStackTrace();
			throw ex;
		}
		return null;
	}

}
