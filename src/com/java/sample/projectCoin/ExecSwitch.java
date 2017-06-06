package com.java.sample.projectCoin;

/**
 * Java SE 8
 * Project Coin
 * Switch 
 */
public class ExecSwitch {

	public static void main(String[] args) {
		
		//String text = "Java EE";
		//String text = "";
		String text = null;
		
		switch (text) {
		case "Java SE":
			System.out.println("Standard Java!");
			break;

		case "Java EE":
			System.out.println("Enterprise Java!");
			break;
			
		default:
			System.out.println("Nothing!");
			break;
		}

	}

}
