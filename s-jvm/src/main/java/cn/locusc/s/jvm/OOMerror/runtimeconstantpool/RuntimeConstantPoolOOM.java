package cn.locusc.s.jvm.OOMerror.runtimeconstantpool;

import java.util.HashSet;
import java.util.Set;

/**
 * @author jayChan
 * 运行时的常量池 OOM错误
 * VM Args：-XX:PermSize=6M -XX:MaxPermSize=6M
 * 2021/5/20
 **/
public class RuntimeConstantPoolOOM {


    /**
     * JKD1.6执行 方法区使用永久代实现
     * Exception in thread "main" java.lang.OutOfMemoryError: PermGen space
     *  at java.lang.String.intern(Native Method)
     *  at org.fenixsoft.oom.RuntimeConstantPoolOOM.main(RuntimeConstantPoolOOM.java: 18)
     *
     * JDK6以上执行 方法区使用元空间 常量池位于堆内存
     * -Xmx6m
     * Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
     * 	at java.lang.Integer.toString(Integer.java:401)
     * 	at java.lang.String.valueOf(String.java:3099)
     * 	at cn.locusc.s.jvm.OOMerror.runtimeconstantpool.RuntimeConstantPoolOOM.main(RuntimeConstantPoolOOM.java:31)
     *
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     *  at java.base/java.lang.Integer.toString(Integer.java:440)
     *  at java.base/java.lang.String.valueOf(String.java:3058)
     *  at RuntimeConstantPoolOOM.main(RuntimeConstantPoolOOM.java:12)
     **/

    public static void main(String[] args) {
        // 使用Set保持着常量池引用，避免Full GC回收常量池行为
//        Set<String> set = new HashSet<>();
//        // 在short范围内足以让6MB的PermSize产生OOM了
//        short i = 0;
//        while (true) {
//            set.add(String.valueOf(i++).intern());
//        }

        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }

}
