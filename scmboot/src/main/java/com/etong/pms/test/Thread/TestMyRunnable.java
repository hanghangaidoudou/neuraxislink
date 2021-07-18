package com.etong.pms.test.Thread;

/**
 * @ClassName TestMyRunnable
 * @Author wch
 * @DATE 2019/10/22 15:44
 * @return
 */
public class TestMyRunnable {

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread t1 = new Thread(myRunnable);
        Thread t2 = new Thread(myRunnable);
        Thread t3 = new Thread(myRunnable);
        Thread t4 = new Thread(myRunnable);
        Thread t5 = new Thread(myRunnable);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

    }

}
