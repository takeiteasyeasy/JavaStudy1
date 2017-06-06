package com.java.sample.basic;

class SuperExecConcealmentMethod {
    //(1)隠蔽されるメソッド
    static void methodA(int i) {
	//void methodA(int i) {
        System.out.println("super：" + (i * 1000));
    }
}
class ExecConcealmentMethod extends SuperExecConcealmentMethod {
    //(2)隠蔽するメソッド
    static void methodA(int i) {
    //void methodA(int i) {
        System.out.println("sub：" + (i * 10));
    }
    public static void main(String[] args) {
        //(3)オブジェクト変数の型がSuperExecConcealmentMethod
        //   オブジェクトがExecConcealmentMethod
    	SuperExecConcealmentMethod objectSuper = new ExecConcealmentMethod();
       objectSuper.methodA(10);
    	//SuperExecTest.methodA(10);
       
       try {
    	   Thread.sleep(5 * 1000);
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }
}