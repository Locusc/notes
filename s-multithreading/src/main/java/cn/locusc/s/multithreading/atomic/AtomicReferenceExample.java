package cn.locusc.s.multithreading.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author jayChan
 * AtomicReference示例
 * 2021/5/28
 **/
public class AtomicReferenceExample {

    public static void main(String[] args) {
        AtomicReferenceExample.ABA();
    }

    /**
     * ABA问题演示
     * Thread-0---- expect: A
     * Thread-1---- change: A -- > B -- > A
     * Thread-0---- actual: A
     * Thread-0---- result: true ==》 final reference = X
     **/
    public static void ABA() {
        //ABA问题
        System.out.println("==========ABA问题：");
        AtomicReference<String> reference = new AtomicReference<>("A");
        new Thread(() -> {
            //获取期望值
            String expect = reference.get();
            //打印期望值
            System.out.println(Thread.currentThread().getName() + "---- expect: " + expect);
            try {
                //干点别的事情
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //打印实际值
            System.out.println(Thread.currentThread().getName() + "---- actual: " + reference.get());
            //进行CAS操作
            boolean result = reference.compareAndSet("A", "X");
            //打印操作结果
            System.out.println(Thread.currentThread().getName() + "---- result: " + result + " ==》 final reference = " + reference.get());
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //进行ABA操作
            System.out.print(Thread.currentThread().getName() + "---- change: " + reference.get());
            reference.compareAndSet("A", "B");
            System.out.print(" -- > B");
            reference.compareAndSet("B", "A");
            System.out.println(" -- > A");
        }).start();
    }

}
