package com.java.sample.projectCoin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Future;

/**
 * Java SE 8
 * Project Coin
 * ジェネリクス型パラメータの省略
 */
public class ExecDiamondExcludable {

	public static void main(String[] args) {
		
		List<String> msgListOne = new ArrayList<String>();
		List<String> msgListTwo = new ArrayList<>();
		
		Set<Double> numSetOne = new HashSet<Double>();
		Set<Double> numSetTwo = new HashSet<>();
		
		Queue<Future<String>> futuresQueueOne = new LinkedList<Future<String>>();
		Queue<Future<String>> futuresQueueTwo = new LinkedList<>();
		
	}
	
	/*
	 * 仮想パラメータを使用した変数への代入
	 */
	public class  Container<T> {
		private List<T> children;
		public Container() {
			children = new ArrayList<>();
		}
	}
}
