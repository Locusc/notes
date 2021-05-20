package cn.locusc.s.jvm.OOMerror.directmemory;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author jayChan
 * 直接内存 OOM
 * VM Args：-Xmx20M -XX:MaxDirectMemorySize=10M
 * 2021/5/20
 **/
public class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    /**
     * Exception in thread "main" java.lang.OutOfMemoryError
     * 	at sun.misc.Unsafe.allocateMemory(Native Method)
     * 	at cn.locusc.s.jvm.OOMerror.directmemory.DirectMemoryOOM.main(DirectMemoryOOM.java:27)
     **/
    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }

}
