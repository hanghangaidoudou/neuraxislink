package com.etong.pms.test.Thread;

/**
 * @ClassName MyRunnable
 * @Author wch
 * @DATE 2019/10/22 15:38
 * @return
 */
public class MyRunnable implements Runnable {

    private int ticket = 100;

    private int completeCount = 0 ;
    /**
     * When an object implementing interface <code>Runnable</code> is used to create a thread,
     * starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may take any action
     * whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        while(this.getTicket() > 0){
            this.sellTicket();
        }
//        this.addCount();
//        System.out.println(Thread.currentThread().getName() + "结束，" + this.completeCount);
    }

    /**
     * 售票
     */
    private synchronized void sellTicket() {
//        if (this.ticket > 0) {
            this.ticket--;
            System.out.println(Thread.currentThread().getName() + "卖票， 剩余票数：" + this.ticket);
//        }
    }

    /**
     * 获取数量
     * @return
     */
    private synchronized int getTicket(){
        return this.ticket;
    }

    /**
     * 完成线程的数量增加
     * @return
     */
    private synchronized void addCount(){
        this.completeCount++;
    }
}
