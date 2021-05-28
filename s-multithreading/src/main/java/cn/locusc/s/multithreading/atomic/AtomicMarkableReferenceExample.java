package cn.locusc.s.multithreading.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @author jayChan
 * AtomicMarkableReference类型
 * ABA测试
 * 2021/5/28
 **/
public class AtomicMarkableReferenceExample {

    public static void main(String[] args) {
        AtomicMarkableReferenceExample.ToSolveABA();
    }

    public static void ToSolveABA() {
        AtomicMarkableReference<Integer> atomicMarkableReference = new AtomicMarkableReference<>(
                100,
                false
        );
        Thread refT1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("isMarked1" + atomicMarkableReference.isMarked());
            atomicMarkableReference.compareAndSet(100, 101, atomicMarkableReference.isMarked(), !atomicMarkableReference.isMarked());
            System.out.println("isMarked2" + atomicMarkableReference.isMarked());
            atomicMarkableReference.compareAndSet(101, 100, atomicMarkableReference.isMarked(), !atomicMarkableReference.isMarked());
        });

        Thread refT2 = new Thread(() -> {
            boolean marked = atomicMarkableReference.isMarked();
            Integer reference = atomicMarkableReference.getReference();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("isMarked3" + marked);
            System.out.println("reference1" + reference);
            boolean c3 = atomicMarkableReference.compareAndSet(100, 101, marked, !marked);
            System.out.println(c3); // 返回true,实际应该返回false
        });

        refT1.start();
        refT2.start();
    }

}
